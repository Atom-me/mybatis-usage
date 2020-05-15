package com.atom.mybatis.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author Atom
 */
@Data
public class SaleOrderApproval {

    /**
     *
     */
    private Integer id;

    /**
     * 申请单号
     */
    private String approvalNo;

    /**
     * 销售工单号
     */
    private String orderNo;

    /**
     * 提交人ID
     */
    private String submitUserId;

    /**
     * 提交人姓名
     */
    private String submitUserName;

    /**
     * 客户手机号
     */
    private String mobile;

    /**
     * 验证方式：1：验证码 2：无验证码
     */
    private Byte validateType;

    /**
     * 审批人id
     */
    private String approverId;

    /**
     * 审批人姓名
     */
    private String approverName;

    /**
     * 到店时间
     */
    private Date arriveShopTime;

    /**
     * 申请单状态 0："待处理", 2："不通过",1： "通过"
     */
    private Byte approvalStatus;

    /**
     * 审批备注
     */
    private String approvalRemark;

    /**
     * 审批时间
     */
    private Date approvalTime;

    /**
     * 接待凭证，图片URL
     */
    private String followRecordPhotos;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     * 接待方式（1：上门；2：到店）
     */
    private Byte receptionType;

    /**
     * 审批单类型(1.接待审批单;2.战败审核单)
     */
    private Integer approvalSheetType;

    /**
     *
     */
    private String remark;
}
