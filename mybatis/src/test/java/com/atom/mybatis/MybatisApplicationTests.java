package com.atom.mybatis;

import com.atom.mybatis.bean.Product;
import com.atom.mybatis.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

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
        Product product = productMapper.getByIdAndPname(4, "笔记本电脑");
        System.out.println(product);
    }

    @Test
    void testGetByPtypeAndPname() {
        List<Product> products = productMapper.getByTypeAndPname("笔记本", "笔记本电脑");
        products.forEach(System.out::println);
    }

    @Test
    void testGetByPtypeAndPname2() {
        List<Product> products = productMapper.getByTypeAndPname2("笔记本", "笔记本电脑");
        products.forEach(System.out::println);
    }

    @Test
    void testGetByIdAndPname2() {
        Product product = productMapper.getByIdAndPname2(4, "笔记本电脑");
        System.out.println(product);
    }

    @Test
    void testGetByIdAndPname3() {
        Product product = productMapper.getByIdAndPname3(4, "笔记本电脑");
        System.out.println(product);
    }

    @Test
    void testGetByIdAndPname4() {
        Product product = productMapper.getByIdAndPname4(4, "笔记本电脑");
        System.out.println(product);
    }

}
