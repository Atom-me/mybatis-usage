package com.atom.mybatis.doenums;

import com.atom.mybatis.typehandler.IEnumeration;
import lombok.Getter;

/**
 * @author Atom
 */
@Getter
public enum GradeEnum implements IEnumeration {

    PRIMARY(1, "小学"),
    SECONDORY(2, "中学"),
    HIGH(3, "高中");

    GradeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * code 值
     */
    private final int code;
    /**
     * 描述
     */
    private final String desc;


    @Override
    public int value() {
        return this.getCode();
    }

    @Override
    public String describe() {
        return this.getDesc();
    }
}
