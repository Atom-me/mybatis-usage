package com.atom.mybatisplus.doenums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 * @author Atom
 */
public interface IBaseEnum<T extends Serializable> extends IEnum<T> {

    String getDescription();
}