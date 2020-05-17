package com.atom.mybatis.bean;

import com.atom.mybatis.interceptor.EncodeEnabled;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Atom
 */
@Data
public class UserMemberInfo implements EncodeEnabled {

    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 身份证号
     */
    private String idNo;
    /**
     * 用户姓名
     */
    private String realName;
    /**
     * 用户手机号
     */
    private String mobilePhone;
    /**
     * 邮箱
     */
    private String email;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private Integer encodeVersion;


    @Override
    public String[] encodeFields() {
        return new String[]{"mobilePhone","idNo"};
    }
}
