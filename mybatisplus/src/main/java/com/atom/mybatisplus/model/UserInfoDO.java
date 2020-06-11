package com.atom.mybatisplus.model;

import com.atom.mybatisplus.doenums.AgeEnum;
import com.atom.mybatisplus.doenums.GenderEnum;
import com.atom.mybatisplus.doenums.GradeEnum;
import com.atom.mybatisplus.doenums.UserState;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Atom
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("user_info")
public class UserInfoDO extends BaseEntity {

    private String name;

    private String email;

    /**
     * IEnum接口的枚举处理
     */
    private AgeEnum age;

    /**
     * 原生枚举： 默认使用枚举值顺序： 0：MALE， 1：FEMALE
     */
    private GenderEnum gender;

    /**
     * 原生枚举（带{@link com.baomidou.mybatisplus.annotation.EnumValue}):
     * 数据库的值对应该注解对应的属性
     */
    private GradeEnum grade;

    private UserState userState;

}