package com.atom.mybatis.mapper;

import com.atom.mybatis.bean.CustomerQuery;
import com.atom.mybatis.bean.CustomerTags;
import com.atom.mybatis.bean.UsedCarCustomer;
import com.atom.mybatis.bean.UsedCarCustomerInfo;
import com.atom.mybatis.typehandler.ListTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author atom
 */
@Mapper
public interface UsedCarCustomerMapper {


    /**
     * 根据手机号查询客户信息
     *
     * @param customerMobile
     * @return
     */
    @Select("SELECT  t.id," +
            " t.telephone as customerPhone, " +
            " t.`name` as customerName,  " +
            " t.age, " +
            " t.gender," +
            " t.type as customerType ," +
            " t.remark, " +
            " t.alternate_phone as alternatePhone," +
            " t.address ," +
            " t.tags" +
            " FROM  used_car_customer t WHERE t.telephone = #{mobile}")
    @Results({
            @Result(property = "weChatTags", column = "tags", typeHandler = ListTypeHandler.class)
    })
    UsedCarCustomerInfo getCustomerInfoByMobile(@Param("mobile") String customerMobile);


    /**
     * 根据手机号更新客户微信标签
     *
     * @param customerTags
     * @return
     */
    int updateTagsByCustomerMobile(@Param("customer") CustomerTags customerTags);

    List<UsedCarCustomerInfo> selectByConditionWrongWrite(CustomerQuery query);

    List<UsedCarCustomerInfo> selectByConditionUseWhereTag(CustomerQuery query);


}