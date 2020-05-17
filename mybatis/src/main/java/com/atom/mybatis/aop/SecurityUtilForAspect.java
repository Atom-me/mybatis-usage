package com.atom.mybatis.aop;

import com.atom.mybatis.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author Atom
 */
@Component
@ConfigurationProperties(prefix = "encode")
@Slf4j
public class SecurityUtilForAspect {

    private Map<Integer, String> keys;

    public Map<Integer, String> getKeys() {
        return keys;
    }

    public void setKeys(Map<Integer, String> keys) {
        this.keys = keys;
    }

    @Value("${encode.version}")
    private Integer version;

    private static final Integer SQL_NUM = 1000;

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }


    /**
     * 对表执行加密初始化
     *
     * @param mapper 执行加密初始化表的对应mapper对象
     */
    public void doEncode(BaseMapper mapper) {
        log.info("encoding for mapper : {} ,start.....", mapper);
        try {
            // 获取总条数
            Method countMethod = mapper.getClass().getMethod("selectCount", null);
            Integer count = (Integer) countMethod.invoke(mapper);
            log.info("total records : {}", count);

            // 每1000条初始化
            for (int i = 0; i < (count / SQL_NUM + 1); i++) {
                log.info("continue : {}", i);
                this.doEncode(mapper, i);
            }
            log.info("encoding for mapper : {} ,success !", mapper);
        } catch (Exception e) {
            log.error("encoding for mapper : {} ,ERROR !", mapper);
            log.error(e.getMessage(), e);
            throw new RuntimeException("执行加密初始化时出错：" + mapper.getClass().getName());
        }
        log.info("执行加密初始化成功：" + mapper.getClass().getName());
    }

    @Transactional(rollbackFor = Exception.class)
    void doEncode(BaseMapper mapper, int times) {
        try {
            // 获取查询第x页记录加密字段的方法对象，并执行
            Method selectMethod = mapper.getClass().getMethod("selectAllForEncode", Integer.class, Integer.class);
            List list = (List) selectMethod.invoke(mapper, times * SQL_NUM, SQL_NUM);
            if (CollectionUtils.isEmpty(list)) {
                return;
            }

            for (Object o : list) {
                Method updateMethod = mapper.getClass().getMethod("updateByPrimaryKeySelective", o.getClass());
                updateMethod.invoke(mapper, o);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("执行加密初始化时出错");
        }
    }
}
