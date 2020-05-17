package com.atom.mybatis.interceptor;

import com.atom.mybatis.utils.AesUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @author Atom
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
@Slf4j
public class EncodeInterceptor implements Interceptor {

    private static final int ENCRYPT = 1;
    private static final int DECRYPT = 2;

    private Integer currentVersion;
    private final Map<Integer, String> keys;

    public EncodeInterceptor(Integer currentVersion, Map<Integer, String> keys) {
        this.currentVersion = currentVersion;
        this.keys = Collections.unmodifiableMap(keys);
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Object parameter = args[1];
        // 当查询参数为单纯的String，不作加密操作
        if (!(parameter instanceof String)) {
            parameter = convert(parameter, ENCRYPT);
            args[1] = parameter;
        }

        Object obj = invocation.proceed();

        if (!(parameter instanceof String)) {
            parameter = convert(parameter, DECRYPT);
            args[1] = parameter;
        }
        return convert(obj, DECRYPT);
    }

    /**
     * @param object
     * @param convertType
     * @return
     */
    private Object convert(Object object, int convertType, Integer version) throws JsonProcessingException {
        if (Objects.isNull(object)) {
            return null;
        }
        if (object instanceof Map) {
            return convertMap((Map) object, convertType);
        }
        if (object instanceof Collection) {
            return convertCollection((Collection) object, convertType);
        }
        if (object.getClass().isArray()) {
            return convertArray((Object[]) object, convertType);
        }
        if (object instanceof EncodeEnabled) {
            return convertOne((EncodeEnabled) object, convertType);
        }
        if (object instanceof String) {
            return convertField((String) object, convertType, version);
        }
        return object;
    }

    private Object convert(Object object, int convertType) throws JsonProcessingException {
        return convert(object, convertType, null);
    }

    private String convertField(String value, int type, Integer version) {
        if (version == null) {
            version = currentVersion;
        }
        String converted = value;
        try {
            if (type == ENCRYPT) {
                converted = encrypt(converted, version);
            } else {
                converted = decrypt(converted, version);
            }
        } catch (Exception e) {
            log.warn("convert error: {}, its dose not matter. value: {}", e.getMessage(), value);
            converted = value;
        }
        log.debug("field convert: before:{} ，after:{}", value, converted);
        return converted;
    }

    private Map convertMap(Map map, int convertType) throws JsonProcessingException {
        Object encodeVersion;
        try {
            encodeVersion = map.get("encodeVersion");
        } catch (Exception e) {
            encodeVersion = null;
        }
        for (Object key : map.keySet()) {
            Object element = map.get(key);
            if (convertType == ENCRYPT) {
                // 对非String元素进行加密
                if (!(element instanceof String)) {
                    element = convert(element, convertType);
                    map.put(key, element);
                }
            } else if (element instanceof EncodeEnabled) {// 解密可解密对象
                element = convert(element, convertType, (Integer) encodeVersion);
                map.put(key, element);
            }
        }
        return map;
    }

    private Collection convertCollection(Collection collection, int type) throws JsonProcessingException {
        Collection col;
        try {
            if ("java.util.Arrays.ArrayList".equals(collection.getClass().getCanonicalName())) {
                col = new ArrayList();
            } else {
                col = collection.getClass().newInstance();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        for (Object obj : collection) {
            obj = convert(obj, type);
            col.add(obj);
        }
        return col;
    }

    private Object[] convertArray(Object[] objects, int type) throws JsonProcessingException {
        if (ArrayUtils.isEmpty(objects)) {
            return objects;
        }
        Object[] arr = new Object[objects.length];
        for (int i = 0; i < objects.length; i++) {
            Object obj = objects[i];
            if (obj instanceof EncodeEnabled) {
                arr[i] = convertOne((EncodeEnabled) obj, type);
            } else {
                arr[i] = obj;
            }
        }
        return arr;
    }

    private Object convertOne(EncodeEnabled encodeEnabled, int type) throws JsonProcessingException {
        String[] fields = encodeEnabled.encodeFields();
        if (ArrayUtils.isEmpty(fields)) {
            return encodeEnabled;
        }

        Integer version = encodeEnabled.getEncodeVersion();

        //注掉该内容，在查询出的对象是可解密对象，却没有在sql语句中写encodeVersion字段时也可以尝试解密
//        // 第一次初始化，不进行解密
//        if (version == null && type == decrypt)
//            return encodeEnabled;

        if (version == null) {
            version = currentVersion;
        }
        if (type == ENCRYPT) {
            version = currentVersion;
            encodeEnabled.setEncodeVersion(version);
        }

        EncodeEnabled enabled;
        try {
            enabled = encodeEnabled.getClass().newInstance();
            BeanUtils.copyProperties(encodeEnabled, enabled);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        try {
            for (String field : fields) {
                Object value = PropertyUtils.getProperty(enabled, field);
                if (value instanceof String) {
                    String converted = convertField((String) value, type, version);
                    log.debug("encode for : {}, field: {},before: {}, after: {}", enabled, field, value, converted);
                    PropertyUtils.setProperty(enabled, field, converted);
                } else {
                    Object converted = convert(value, type);
                    PropertyUtils.setProperty(enabled, field, converted);
                }
            }
        } catch (Exception e) {
            log.error("error converting object : {}, type: {}, class: {}", new ObjectMapper().writeValueAsString(enabled), type, enabled);
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return enabled;
    }

    private String decrypt(String source, Integer version) {
        if (version == null) {
            version = currentVersion;
        }
        return AesUtil.decryptBase64(source, keys.get(version));
    }

    private String encrypt(String source, Integer version) {
        if (version == null) {
            version = currentVersion;
        }
        return AesUtil.encryptBase64(source, keys.get(version));
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public void setCurrentVersion(Integer currentVersion) {
        this.currentVersion = currentVersion;
    }
}
