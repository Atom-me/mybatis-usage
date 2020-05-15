package com.atom.mybatis.bean;

import lombok.Data;

import java.util.List;

/**
 * 客户微信标签
 *
 * @author Atom
 * @
 */
@Data
public class CustomerTags {
    private String customerMobile;
    private List<String> tags;
}
