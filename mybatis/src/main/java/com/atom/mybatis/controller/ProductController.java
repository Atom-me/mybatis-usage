package com.atom.mybatis.controller;

import com.atom.mybatis.bean.Product;
import com.atom.mybatis.mapper.ProductMapper;
import com.atom.mybatis.web.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Atom
 */
@RestController
public class ProductController {

    @Resource
    private ProductMapper productMapper;

    /**
     * post as query params
     * http://localhost:8080/soa/product/add?pName=高音质耳机&type=耳机&price=9999
     *
     * @param product
     * @return
     */
    @PostMapping("/soa/product/add")
    public Response add(Product product) {
        Integer res = productMapper.addProduct(product);
        return res == 1 ? new Response("200", "OK") : new Response("500", "fail");
    }


    /**
     * post as  RequestBody
     * http://localhost:8080/soa/product/add?pName=高音质耳机&type=耳机&price=9999
     *
     * @param product
     * @return
     */
    @PostMapping("/soa/product/add2")
    public Response add2(@RequestBody Product product) {
        Integer res = productMapper.addProduct(product);
        return res == 1 ? new Response("200", "OK") : new Response("500", "fail");
    }


    @PutMapping("/soa/product/update")
    public Response update(Product product) {
        Integer res = productMapper.update(product);
        return res == 1 ? new Response("200", "OK") : new Response("500", "fail");
    }

    @GetMapping("/soa/product/{id}")
    public Response get(@PathVariable("id") Integer id) {
        final Product product = productMapper.getById(id);
        return new Response("200", "ok", product);
    }

    @DeleteMapping("/soa/product/{id}")
    public Response delete(@PathVariable("id") Integer id) {
        Integer res = productMapper.deleteById(id);
        return res == 1 ? new Response("200", "OK") : new Response("500", "fail");
    }

    @GetMapping("/soa/products")
    public Response list() {
        return new Response("200", "ok", productMapper.queryByLists());
    }
}
