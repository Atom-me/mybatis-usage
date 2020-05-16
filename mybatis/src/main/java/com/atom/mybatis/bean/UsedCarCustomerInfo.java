package com.atom.mybatis.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author atom
 */
@Data
public class UsedCarCustomerInfo {
    private String id;
    private String customerName;//客户姓名
    private Integer age;//年龄
    private Integer gender;//性别
    private BigDecimal budgetLowValue;//预算低值
    private BigDecimal budgetHighValue;//预算高值
    private Integer purchaseMode;//分期，全款
    private Integer starLevel;//客户星级
    private String alternatePhone;
    private String remark;
    private String address;
    private String customerPhone;
    private String customerType;//客户类型(1,个人；2，公司)
    private Integer odographNum; // 表显里程
    private List<String> weChatTags;// 微信标签

    private Long clueId;// 线索ID

}
