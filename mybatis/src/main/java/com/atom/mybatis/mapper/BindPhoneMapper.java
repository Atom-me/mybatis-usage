package com.atom.mybatis.mapper;

import com.atom.mybatis.bean.BindPhoneDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * 绑定手机信息表
 *
 * @author atom
 */
@Mapper
public interface BindPhoneMapper {


    /**
     * 根据amwayId查询用户手机绑定信息
     *
     * @param amwayId
     * @return
     */
    @Results(id = "baseMap", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifier", property = "modifier", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "version", property = "version", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "account_id", property = "accountId", jdbcType = JdbcType.BIGINT),
            @Result(column = "ada", property = "ada", jdbcType = JdbcType.VARCHAR),
            @Result(column = "amway_id", property = "amwayId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mobile_phone", property = "mobilePhone"),
            @Result(column = "country_code", property = "countryCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "master_flag", property = "masterFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "bind_date", property = "bindDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "channel", property = "channel", jdbcType = JdbcType.VARCHAR)
    })
    @Select({
            "select id,creator,create_time,modifier,update_time,version,status,account_id,ada,amway_id,mobile_phone,country_code,master_flag,bind_date,channel",
            " from ua_bind_phone t",
            "where t.amway_id = #{amwayId} and t.status = 1 ",
            "limit 1 "
    })
    BindPhoneDO findByAmwayId(String amwayId);


    /**
     * insert
     *
     * @param bindPhoneDO
     * @return
     */
    @Insert("INSERT INTO bind_phone " +
            " (creator, create_time, " +
            " update_time, version, status,  mobile_phone,  bind_date) " +
            " VALUES " +
            "    (" +
            " #{creator}," +
            " now()," +
            " now()," +
            " #{version}," +
            " #{status}," +
            " #{mobilePhone}," +
            " now()" +
            ")  ")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int saveUseOptionsAnnotation(BindPhoneDO bindPhoneDO);


    /**
     * 这里的before指的是select语句是否在insert之前执行，显然我们这里需要先写后读，所以是false。
     *
     * @param bindPhoneDO
     * @return
     */
    @Insert("INSERT INTO bind_phone " +
            " (creator, create_time, " +
            " update_time, version, status,  mobile_phone,  bind_date) " +
            " VALUES " +
            "    (" +
            " #{creator}," +
            " now()," +
            " now()," +
            " #{version}," +
            " #{status}," +
            " #{mobilePhone}," +
            " now()" +
            ")  ")
    @SelectKey(statement = "SELECT LAST_INSERT_ID() AS ID", keyProperty = "id", keyColumn = "id", before = false, resultType = Long.class)
    int saveUseSelectKeyAnnotationUseSelectLastInsertId(BindPhoneDO bindPhoneDO);


    /**
     * SELECT LAST_INSERT_ID() 不用别名as 也可以
     * 这里的before指的是select语句是否在insert之前执行，显然我们这里需要先写后读，所以是false。
     *
     * @param bindPhoneDO
     * @return
     */
    @Insert("INSERT INTO bind_phone " +
            " (creator, create_time, " +
            " update_time, version, status,  mobile_phone,  bind_date) " +
            " VALUES " +
            "    (" +
            " #{creator}," +
            " now()," +
            " now()," +
            " #{version}," +
            " #{status}," +
            " #{mobilePhone}," +
            " now()" +
            ")  ")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id", before = false, resultType = Long.class)
    int saveUseSelectKeyAnnotationUseSelectLastInsertId2(BindPhoneDO bindPhoneDO);


    /**
     * 这里的before指的是select语句是否在insert之前执行，显然我们这里需要先写后读，所以是false。
     *
     * @param bindPhoneDO
     * @return
     */
    @Insert("INSERT INTO bind_phone " +
            " (creator, create_time, " +
            " update_time, version, status,  mobile_phone,  bind_date) " +
            " VALUES " +
            "    (" +
            " #{creator}," +
            " now()," +
            " now()," +
            " #{version}," +
            " #{status}," +
            " #{mobilePhone}," +
            " now()" +
            ")  ")
    @SelectKey(statement = "SELECT @@identity", keyProperty = "id", keyColumn = "id", before = false, resultType = Long.class)
    int saveUseSelectKeyAnnotationUseIdentity(BindPhoneDO bindPhoneDO);


    @Delete("delete from bind_phone where id = #{id}")
    int deleteById(Long id);


}
