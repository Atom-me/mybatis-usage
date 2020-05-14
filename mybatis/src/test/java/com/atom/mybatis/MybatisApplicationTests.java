package com.atom.mybatis;

import com.atom.mybatis.bean.Product;
import com.atom.mybatis.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MybatisApplicationTests {
    @Resource
    private ProductMapper productMapper;

    @Test
    void contextLoads() {
    }


    @Test
    void testSelectById() {
        Product product = productMapper.getById(4);
        System.out.println(product);
    }


    @Test
    void testGetByIdAndPname() {
        Product product = productMapper.getByIdAndPname(4,"笔记本电脑");
        System.out.println(product);
    }

    @Test
    void testGetByIdAndPname2() {
        Product product = productMapper.getByIdAndPname2(4,"笔记本电脑");
        System.out.println(product);
    }


}
