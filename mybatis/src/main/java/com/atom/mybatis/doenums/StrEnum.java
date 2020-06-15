package com.atom.mybatis.doenums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Atom
 */
@Getter
@AllArgsConstructor
public enum StrEnum {

    ONE("one"),
    TWO("two");

    private final String value;
}
