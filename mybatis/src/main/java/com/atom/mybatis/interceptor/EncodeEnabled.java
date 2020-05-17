package com.atom.mybatis.interceptor;

/**
 * @author Atom
 */
public interface EncodeEnabled {


    /**
     * 获得加密版本号
     *
     * @return 加密版本号， 如果为null， 则从系统获取当前加密版本号
     */
    default Integer getEncodeVersion() {
        return null;
    }

    /**
     * 设置加密版本号
     *
     * @param encodeVersion
     */
    default void setEncodeVersion(Integer encodeVersion) {

    }

    /**
     * 加密的字段
     *
     * @return
     */
    String[] encodeFields();

}
