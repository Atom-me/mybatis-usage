package com.atom.mybatis.mapper;

import com.atom.mybatis.bean.ApproveDTO;
import com.atom.mybatis.bean.SaleOrderApproval;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @author Atom
 */
@Mapper
public interface SaleOrderApprovalMapper {

    /**
     * 使用provider的方式，可以根据实体中有值的属性，进行动态的生成插入SQL语句
     *
     * @Insert 等注解 是直接配置SQL语句，而@InsertProvider等注解则是通过SQL工厂类及对应的方法生产SQL语句，
     * 这种方法的好处在于，我们可以根据不同的需求生产出不同的SQL，适用性更好。
     */
    @UpdateProvider(type = ApprovalProvider.class, method = "updateApprovalStatusAndRemarkById")
    int updateApprovalStatusAndRemarkById(ApproveDTO approveDTO);


    @SelectProvider(type = ApprovalProvider.class, method = "selectBySubmitUserId")
    List<SaleOrderApproval> selectBySubmitUserId(String submitUserId);


}
