package com.atom.mybatis.mapper;

import com.atom.mybatis.bean.OrderDataDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author Atom
 */
@Mapper
public interface OrderDataMapper {

    /**
     * 普通查询，不做加密解密操作
     *
     * @param sellerMobile
     * @return
     */
    OrderDataDO normalSelectOrderBySellerMobile(String sellerMobile);

    /**
     * 使用加密字段查询，返回结果对应字段自动解密
     *
     * @param sellerMobile
     * @return
     */
    OrderDataDO encryptSelectOrderBySellerMobile(String sellerMobile);

    /**
     * 插入数据，对应字段自动加密入库
     *
     * @param orderDataDO
     * @return
     */
    int insert(OrderDataDO orderDataDO);
}
