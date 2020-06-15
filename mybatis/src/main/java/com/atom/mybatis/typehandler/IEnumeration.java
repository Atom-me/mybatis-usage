package com.atom.mybatis.typehandler;

/**
 * @author Atom
 */
public interface IEnumeration {
    /**
     * 枚举值
     *
     * @return
     */
    int value();

    /**
     * 枚举项名称
     *
     * @return
     */
    String name();

    /**
     * 枚举描述
     *
     * @return
     */
    String describe();
}
