package com.atom.mybatis.config;

import com.atom.mybatis.interceptor.EncodeInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
        List<Interceptor> interceptors = sessionFactory.getConfiguration().getInterceptors();
        if (interceptors.stream().anyMatch(interceptor -> interceptor instanceof EncodeInterceptor)) {
            return;
        }
        EncodeInterceptor encodingInterceptor = new EncodeInterceptor(version, keys);
        sessionFactory.getConfiguration().addInterceptor(encodingInterceptor);
    }

}

