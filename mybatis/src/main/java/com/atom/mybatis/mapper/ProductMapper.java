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

    /**
     * 如果这里参数只有一个，在不加 @Params注解的情况下，#{} 里面你可以写任何东西都可以映射到
     *
     * @param id
     * @return
     */
    @Select("select * from products where pid = #{xxx}")
    Product getById(Integer id);

    /**
     * 如果这里参数多个，在不加 @Params注解的情况下，
     * #{} 里面你可以写 形参名称 或者 param1 param2 ...也可以映射到
     *
     * @param id
     * @param name
     * @return
     */
    @Select("select * from products where pid = #{id} and pname = #{name} ")
    Product getByIdAndPname(Integer id, String name);

    /**
     * 如果这里参数多个，在不加 @Params注解的情况下，
     * #{} 里面你可以写 形参名称 或者 param1 param2 ...也可以映射到
     *
     * @param id
     * @param name
     * @return
     */
    @Select("select * from products where pid = #{param1} and pname = #{param2} ")
    Product getByIdAndPname2(Integer id, String name);

    /**
     * 如果这里参数多个，在加了 @Params注解的情况下，
     * #{} 里面你可以写 注解指定参数名称 或者 param1 param2 ...也可以映射到
     *
     * @param id
     * @param name
     * @return
     */
    @Select("select * from products where pid = #{first} and pname = #{senond} ")
    Product getByIdAndPname3(@Param("first") Integer id, @Param("senond") String name);


    /**
     * 如果这里参数多个，在加了 @Params注解的情况下，
     * #{} 里面你可以写 注解指定参数名称 或者 param1 param2 ...也可以映射到
     *
     * @param id
     * @param name
     * @return
     */
    @Select("select * from products where pid = #{param1} and pname = #{param2} ")
    Product getByIdAndPname4(@Param("first") Integer id, @Param("senond") String name);


    @Select("select * from products order by pid desc")
    List<Product> queryByLists();

}
