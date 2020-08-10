package com.atom.mybatis.mapper;

import com.atom.mybatis.bean.WechatUserInfoDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * 微信用户信息
 *
 * @author atom
 */
@Mapper
public interface WeChatUserInfoMapper {

    /**
     * 查询微信用户信息
     *
     * @param id
     * @return
     */
    @Results(id = "WeChatUserInfoBaseMap", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "version", property = "version", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "openid", property = "openid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "unionid", property = "unionid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "nickname", property = "nickname", jdbcType = JdbcType.VARCHAR, typeHandler = com.atom.mybatis.typehandler.EmojiTypeHandler.class),
            @Result(column = "head_img_url", property = "headImgUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "appid", property = "appid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "follow_flag", property = "followFlag", jdbcType = JdbcType.INTEGER)
    })
    @Select({
            "select id, create_time, update_time, version, status, ",
            "   openid, unionid, appid, follow_flag, nickname, head_img_url",
            " from emoji_utf8.ua_wechat_userinfo ",
            " where id = #{id} "
    })
    WechatUserInfoDO findById(@Param("id") Long id);


    @Insert("INSERT INTO emoji_utf8.ua_wechat_userinfo " +
            " ( create_time, " +
            " update_time, version, status,  openid,  unionid, appid, follow_flag ,nickname,head_img_url) " +
            " VALUES " +
            "    (" +
            " now()," +
            " now()," +
            " #{version}," +
            " #{status}," +
            " #{openid}," +
            " #{unionid}," +
            " #{appid}," +
            " #{followFlag}," +
            " #{nickname , typeHandler=com.atom.mybatis.typehandler.EmojiTypeHandler}," +
            " #{headImgUrl}" +
            ")  ")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(WechatUserInfoDO wechatUserInfoDO);

    @Delete("delete from emoji_utf8.ua_wechat_userinfo where id = #{id}")
    int deleteById(@Param("id") Long id);
}
