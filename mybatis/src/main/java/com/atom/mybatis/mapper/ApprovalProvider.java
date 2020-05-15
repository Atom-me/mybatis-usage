package com.atom.mybatis.mapper;

import com.atom.mybatis.bean.ApproveDTO;
import com.atom.mybatis.bean.SaleOrderApproval;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @author atom
 */
public class ApprovalProvider {

    private static final String TABLE_NAME = "sale_order_approval";

    /**
     * 更新审批状态
     *
     * @param approveDTO
     * @return
     */
    public String updateApprovalStatusAndRemarkById(final ApproveDTO approveDTO) {
        SQL sql = new SQL();
        sql.UPDATE(TABLE_NAME)
                .SET("approval_status = #{approvalStatus}")
                .SET("approval_remark = #{approvalRemark}")
                .SET("update_time = now() ")
                .SET("approval_time = now() ")
                .WHERE("id = #{id}");
        return sql.toString();
    }


    public String selectBySubmitUserId(final String submitUserId) {
        return new SQL() {
            {
                SELECT("approval_status as approvalStatus,approval_remark as approvalRemark,update_time as updateTime");
                FROM(TABLE_NAME);
                WHERE("submit_user_id=#{submitUserId} ");
            }
        }.toString();
    }
}


