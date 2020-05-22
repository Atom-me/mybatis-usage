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
     * 对查询结果的和bean属性的映射 这么写需要开启 ，驼峰命名自动映射 #mybatis.configuration.map-underscore-to-camel-case=true
     * <p>
     * 如果这里参数只有一个，并且参数是基本类型，在不加 @Params注解的情况下，#{} 里面你可以写任何东西都可以映射到
     * 即参数是基本类型 变量名可以随便写，#{id}，#{ids}，#{123}，#{xxgdsgdg},等都可以获得到参数。
     *
     * @param id
     * @return
     */
    @Select("select * from products where pid = #{xxx}")
    Product getById(Integer id);

    /**
     * useActualParamName 配置
     * 允许使用方法签名中的名称作为语句参数名称。 为了使用该特性，你的工程必须采用Java 8编译，并且加上-parameters选项。（从3.4.1开始）
     * 默认是true
     * <p>
     * 对查询结果的和bean属性的映射 这么写需要开启 ，驼峰命名自动映射 #mybatis.configuration.map-underscore-to-camel-case=true
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
     * useActualParamName 配置
     * 允许使用方法签名中的名称作为语句参数名称。 为了使用该特性，你的工程必须采用Java 8编译，并且加上-parameters选项。（从3.4.1开始）
     * 默认是true
     * <p>
     * #mybatis.configuration.use-actual-param-name=true
     * <p>
     * #Caused by: org.apache.ibatis.binding.BindingException:
     * Parameter 'arg0' not found. Available parameters are [name, type, param1, param2]
     *
     * @param type
     * @param name
     * @return
     */
    @Select("select * from products where type = #{type} and pname = #{name} ")
    List<Product> getByTypeAndPname(String type, String name);


    /**
     * mybatis.configuration.use-actual-param-name=false
     * <p>
     * #Caused by: org.apache.ibatis.binding.BindingException:
     * Parameter 'arg0' not found. Available parameters are [0, 1, param1, param2]
     *
     * @param type
     * @param name
     * @return
     */
    @Select("select * from products where type = #{0} and pname = #{1} ")
    List<Product> getByTypeAndPname2(String type, String name);


    /**
     * 对查询结果的和bean属性的映射 这么写需要开启 ，驼峰命名自动映射 #mybatis.configuration.map-underscore-to-camel-case=true
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
     * 对查询结果的和bean属性的映射 这么写需要开启 ，驼峰命名自动映射 #mybatis.configuration.map-underscore-to-camel-case=true
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
     * 对查询结果的和bean属性的映射 这么写需要开启 ，驼峰命名自动映射 #mybatis.configuration.map-underscore-to-camel-case=true
     * 如果这里参数多个，在加了 @Params注解的情况下，
     * #{} 里面你可以写 注解指定参数名称 或者 param1 param2 ...也可以映射到
     *
     * @param id
     * @param name
     * @return
     */
    @Select("select * from products where pid = #{param1} and pname = #{param2} ")
    Product getByIdAndPname4(@Param("first") Integer id, @Param("senond") String name);


    /**
     * 对查询结果的和bean属性的映射 这么写需要开启 ，驼峰命名自动映射 #mybatis.configuration.map-underscore-to-camel-case=true
     *
     * @return
     */
    @Select("select * from products order by pid desc")
    List<Product> queryByLists();

}
