package com.atom.mybatis.mapper;

import com.atom.mybatis.bean.SecurityQuestionDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Atom
 */
@Mapper
public interface SecurityQuestionMapper {


    /**
     * 在未开启 驼峰命名自动转换的情况下，#mybatis.configuration.map-underscore-to-camel-case=true
     * 可以使用 sql as 别名 处理映射
     *
     * @param id
     * @return
     */
    @Select("select  t.id," +
            " t.creator," +
            " t.create_time as createTime," +
            " t.update_time as updateTime," +
            " t.account_id as accountId," +
            " t.question_id as questionId," +
            " t.question  from security_question t where t.id = #{id}")
    SecurityQuestionDO selectById(Integer id);


    /**
     * 在未开启 驼峰命名自动转换的情况下，#mybatis.configuration.map-underscore-to-camel-case=true
     * 也可以使用 @Result注解实现映射
     * 注意使用了@Results注解，就不能在使用 as 别名，
     * （如果你也使用了as 别名，那么同时需要修改 @Result注解中的column属性，因为他映射的就是sql 的column，
     * 起了列别名之后，这一列的列名就变了，所以要同步修改@Result注解中的column
     * ）
     *
     * @param id
     * @return
     * @Results注解相当于 mapper.xml文件中     resultMap标签
     */
    @Results(
            id = "securityQuestionMap",
            value = {
                    @Result(id = true, column = "id", property = "id"),
                    @Result(column = "creator", property = "creator"),
                    @Result(column = "create_time", property = "createTime"),
                    @Result(column = "update_time", property = "updateTime"),
                    @Result(column = "account_id", property = "accountId"),
                    @Result(column = "question_id", property = "questionId"),
                    @Result(column = "question", property = "question"),
            })
    @Select("select  t.id," +
            " t.creator," +
            " t.create_time ," +
            " t.update_time ," +
            " t.account_id ," +
            " t.question_id ," +
            " t.question  from security_question t where t.id = #{id}")
    SecurityQuestionDO selectById2(Integer id);


    /**
     * 同一个表的 @Result 可以共用，使用 @ResultMap 去引用即可，引用的值就是 @Results注解的id属性的值。
     * <p>
     *
     * @param id
     * @return
     * @ResultMap 这个注解给@Select或者@SelectProvider提供在XML映射中的<resultMap>的id。这使得注解的select可以复用那些定义在XML中的ResultMap。
     * <p>
     * 如果同一select注解中还存在@Results或者@ConstructorArgs，那么这两个注解将被此注解(@ResultMap )覆盖。
     */
    @ResultMap("securityQuestionMap")
    @Select("select  t.id," +
            " t.creator," +
            " t.create_time ," +
            " t.update_time ," +
            " t.account_id ," +
            " t.question_id ," +
            " t.question  from security_question t where t.question_id = #{id} ")
    List<SecurityQuestionDO> selectByQuestionId(Integer id);


}
