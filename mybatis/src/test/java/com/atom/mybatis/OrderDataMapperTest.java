package com.atom.mybatis;

import com.atom.mybatis.bean.OrderDataDO;
import com.atom.mybatis.mapper.OrderDataMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Atom
 */
@SpringBootTest
public class OrderDataMapperTest {

    @Resource
    private OrderDataMapper mapper;

    @Test
    public void testNormalSelect() {
        OrderDataDO orderData = mapper.normalSelectOrderBySellerMobile("18518179394");
        System.out.println(orderData);
    }


    @Test
    public void testInsert() {
        OrderDataDO orderDataDO = new OrderDataDO();
        orderDataDO.setOrderId("3456789ghjk");
        orderDataDO.setSellerName("sarming");
        orderDataDO.setSellerMobile("18518179394");
        orderDataDO.setCustomerIdentityNo("110999999988776655");
        int insert = mapper.insert(orderDataDO);
        System.out.println(insert);
    }

    @Test
    public void testDecryptSelect() {
        OrderDataDO orderData = mapper.encryptSelectOrderBySellerMobile("18518179394");
        System.out.println(orderData);
    }

}
