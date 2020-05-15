package com.atom.mybatis.bean;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Atom
 */
@Data
@Builder
public class ApproveDTO {
    private String token;
    @NotNull(message = "id 不能为空")
    private Integer id;
    private Integer approvalStatus;
    private String approvalRemark = "";
    /**
     * 销售ID
     */
    private String userId;
    /**
     * 销售姓名
     */
    private String userName;
    private String salerMobile;
}
