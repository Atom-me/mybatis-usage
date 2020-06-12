package com.atom.mybatis.bean;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 客户微信标签
 *
 * @author Atom
 * @
 */
@Data
@Accessors(fluent = true)
public class CustomerTags {
    private String customerMobile;
    private List<String> tags;
}
