package com.atom.mybatis.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @author Atom
 * @RequestBody时第二个字母大写,映射不到 解决办法:
 * 1,不去使用首字目大写或第二个字母大写的参数
 * 2,自己生成get和set方法
 * 3,使用@JsonProperty注解
 */
@Data
public class Product {
    @JsonProperty(value = "pId")
    private Integer pId;
    @JsonProperty(value = "pName")
    private String pName;
    private String type;
    private Double price;
    private LocalDateTime createTime;
}
