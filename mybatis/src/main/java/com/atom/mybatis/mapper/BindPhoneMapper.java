package com.atom.mybatis.mapper;

import com.atom.mybatis.bean.BindPhoneDO;
import org.apache.ibatis.annotations.*;

/**
 * 绑定手机信息表
 *
 * @author atom
 */
@Mapper
public interface BindPhoneMapper {


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
