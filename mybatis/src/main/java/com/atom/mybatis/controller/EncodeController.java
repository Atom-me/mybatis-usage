package com.atom.mybatis.controller;

import com.atom.mybatis.aop.SecurityUtilForAspect;
import com.atom.mybatis.config.MybatisConfiguration;
import com.atom.mybatis.interceptor.EncodeInterceptor;
import com.atom.mybatis.mapper.UserMemberInfoMapper;
import com.atom.mybatis.utils.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Atom
 */
@RestController
@RequestMapping(value = "encode")
@ConfigurationProperties(prefix = "encode")
@Slf4j
public class EncodeController {

    private Map<Integer, String> keys;

    @Value("${encode.version}")
    private Integer version;

    public Map<Integer, String> getKeys() {
        return keys;
    }

    public void setKeys(Map<Integer, String> keys) {
        this.keys = keys;
    }

    @Resource
    private UserMemberInfoMapper userMemberInfoMapper;
    @Resource
    private SecurityUtilForAspect securityUtilForAspect;
    @Resource
    private SqlSessionFactory sessionFactory;
    @Resource
    private MybatisConfiguration mybatisConfiguration;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    /**
     * 进行敏感信息加密
     *
     * @return
     */
    @GetMapping("/doEncode")
    public String doEncode() {
        executorService.submit(() -> {
            log.info("initializing start............");
            try {
                // 采用新的密钥
                version = securityUtilForAspect.getVersion();
                // 修改拦截器中version版本
                mybatisConfiguration.setVersion(version);
                List<Interceptor> interceptors = sessionFactory.getConfiguration().getInterceptors();
                for (Interceptor interceptor : interceptors) {
                    if (interceptor instanceof EncodeInterceptor) {
                        ((EncodeInterceptor) interceptor).setCurrentVersion(version);
                    }
                }
                // 使用新的密钥进行初始化
                securityUtilForAspect.doEncode(userMemberInfoMapper);
                log.info("initializing success !");
            } catch (Throwable e) {
                log.error("initializing error !");
                log.error(e.getMessage(), e);
            }
            log.info("execute success ! >>>>");

        });
        return "success";
    }


    /**
     * 获取加密后字段样式
     *
     * @param text
     * @return
     */
    @GetMapping("/getEncryptedStr")
    public String getEncryptedStr(String text) {
        StringBuilder sb = new StringBuilder();
        String encode = null;
        String decode = null;
        try {
            encode = AesUtil.encryptBase64(text, keys.get(version));
        } catch (Exception ignored) {
        } finally {
            sb.append("加密后效果为：").append(encode).append("\r\n");
        }

        try {
            decode = AesUtil.decryptBase64(text, keys.get(version));
        } catch (Exception ignored) {
        } finally {
            sb.append("解密后效果为：").append(decode);
        }

        return sb.toString();
    }


}
