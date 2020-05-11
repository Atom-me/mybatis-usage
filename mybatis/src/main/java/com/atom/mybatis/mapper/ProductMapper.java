package com.atom.mybatis.mapper;

import com.atom.mybatis.bean.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Atom
 */
@Mapper
public interface ProductMapper {

    @Insert("insert into products (pname,type,price) values(#{pName},#{type},#{price})")
    Integer addProduct(Product product);

    @Delete("delete from products where pid = #{arg1}")
    Integer deleteById(Integer id);

    @Update("update products set pname=#{pName},type=#{type},price=#{price} where pid=#{pId}")
    Integer update(Product product);

    @Select("select * from products where pid = #{arg1}")
    Product getById(Integer id);

    @Select("select * from products order by pid desc")
    List<Product> queryByLists();

}
