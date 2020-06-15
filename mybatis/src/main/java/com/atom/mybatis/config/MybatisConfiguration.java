package com.atom.mybatis.config;

import com.atom.mybatis.interceptor.EncodeInterceptor;
import com.atom.mybatis.typehandler.EnumerationTypeHandler;
import com.atom.mybatis.typehandler.IEnumeration;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Atom
 */
@Configuration
@ConfigurationProperties(prefix = "encode")
public class MybatisConfiguration implements InitializingBean {

    @Value("${encode.version}")
    private Integer version;

    private Map<Integer, String> keys;

    @Resource
    private SqlSessionFactory sessionFactory;

    public Map<Integer, String> getKeys() {
        return keys;
    }

    public void setKeys(Map<Integer, String> keys) {
        this.keys = keys;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public void afterPropertiesSet() {
        //register interceptor
        List<Interceptor> interceptors = sessionFactory.getConfiguration().getInterceptors();
        if (interceptors.stream().anyMatch(interceptor -> interceptor instanceof EncodeInterceptor)) {
            return;
        }
        EncodeInterceptor encodingInterceptor = new EncodeInterceptor(version, keys);
        sessionFactory.getConfiguration().addInterceptor(encodingInterceptor);

    }

    /**
     * 自定义相关注册器
     *
     * @return
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        ConfigurationCustomizer config = configuration -> {
            // 类型处理器
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
//            Set<Class<?>> specialEnums = ClassUtil.scanClassesByPackage("com.atom.mybatis.doenums");
//            specialEnums.forEach(specialEnum -> {
//                if (specialEnum.isEnum() && IEnumeration.class.isAssignableFrom(specialEnum)) {
//                    typeHandlerRegistry.register(specialEnum, EnumerationTypeHandler.class);
//                }
//            });
            String enumBasePackages = "com.atom.mybatis.doenums";
            String[] enumPackages = parseEnumBasePackage(enumBasePackages);
            Set<Class<? extends IEnumeration>> specialEnums = doScanEnumClass(enumPackages);
            specialEnums.forEach(specialEnum -> {
                if (specialEnum.isEnum() && IEnumeration.class.isAssignableFrom(specialEnum)) {
                    typeHandlerRegistry.register(specialEnum, EnumerationTypeHandler.class);
                }
            });
        };

        return config;
    }

    /**
     * 搜索实现IEnum接口的枚举类
     *
     * @param enumBasePackages
     * @return
     */
    protected Set<Class<? extends IEnumeration>> doScanEnumClass(String... enumBasePackages) {

        Set<Class<? extends IEnumeration>> filteredClasses = new HashSet<>();

        ResolverUtil<IEnumeration> resolverUtil = new ResolverUtil<>();
        resolverUtil.findImplementations(IEnumeration.class, enumBasePackages);
        Set<Class<? extends IEnumeration>> handlerSet = resolverUtil.getClasses();
        for (Class<? extends IEnumeration> type : handlerSet) {
            if (type.isEnum()) {
                filteredClasses.add(type);
            }
        }

        return filteredClasses;
    }

    /**
     * 解析包路径
     *
     * @param enumBasePackages
     * @return
     */
    protected String[] parseEnumBasePackage(String enumBasePackages) {
        return StringUtils.tokenizeToStringArray(enumBasePackages, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
    }
}

