package com.atom.mybatis.bean;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Atom
 */
@Data
public class OrderDataDO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 销售姓名
     */
    private String sellerName;
    /**
     * 销售电话
     */
    private String sellerMobile;
    /**
     * 身份证号
     */
    private String customerIdentityNo;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
