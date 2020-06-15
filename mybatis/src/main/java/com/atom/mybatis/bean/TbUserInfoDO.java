package com.atom.mybatis.bean;

import com.atom.mybatis.doenums.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Atom
 */

@Data
@Accessors(chain = true, fluent = true)
public class TbUserInfoDO {

    private Long id;

    private String name;

    private String email;

    /**
     * 实现了 IEnumeration 接口 的枚举
     * 数据库存入对应指定的code值 1，2， 3 。。。
     */
    private AgeEnum age;

    /**
     * 原生枚举： 默认使用 EnumTypeHandler
     * 数据库存入 枚举值name： MALE， FEMALE
     */
    private GenderEnum gender;

    /**
     * 原生枚举:
     * 数据库的值对应该注解对应的属性
     */
    private GradeEnum grade;

    private UserState userState;
    private StrEnum strEnum;


}