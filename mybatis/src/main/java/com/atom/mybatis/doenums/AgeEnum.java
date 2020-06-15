package com.atom.mybatis.doenums;

import com.atom.mybatis.typehandler.IEnumeration;
import lombok.Getter;

/**
 * @author Atom
 */
@Getter
public enum AgeEnum implements IEnumeration {
    ONE(1, "一岁"),
    TWO(2, "二岁"),
    THREE(3, "三岁");

    private final int value;
    private final String desc;

    AgeEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public int value() {
        return this.getValue();
    }

    @Override
    public String describe() {
        return this.getDesc();
    }
}
