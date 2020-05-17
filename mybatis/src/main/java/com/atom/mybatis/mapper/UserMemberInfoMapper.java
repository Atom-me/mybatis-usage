package com.atom.mybatis.mapper;

import com.atom.mybatis.bean.UserMemberInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Atom
 */
@Mapper
public interface UserMemberInfoMapper extends BaseMapper {

    int insert(UserMemberInfo record);

    UserMemberInfo selectById(Integer id);

    /**
     * 对加密字段的条件查询 如果直接使用对应字段查询，无法查出对应加密字段
     *
     * @param idNo
     * @return
     * @see UserMemberInfoMapper#selectByIdNoUseObject(UserMemberInfo)
     */
    UserMemberInfo selectByIdNo(String idNo);

    /**
     * 对加密字段的条件查询使用对象查询，可查出对应加密字段
     *
     * @param info
     * @return
     */
    UserMemberInfo selectByIdNoUseObject(UserMemberInfo info);

    @Select("select count(*) from user_member_info")
    int selectCount();

    @Select("select id, encode_version encodeVersion, mobile_phone mobilePhone,id_no idNo from user_member_info ORDER BY id LIMIT #{begin},#{num}")
    List<UserMemberInfo> selectAllForEncode(@Param("begin") Integer begin,
                                            @Param("num") Integer num);

    @Update("update user_member_info set mobile_phone = #{userMemberInfo.mobilePhone},id_no = #{userMemberInfo.idNo},encode_version = #{userMemberInfo.encodeVersion} where id = #{userMemberInfo.id}")
    void updateByPrimaryKeySelective(@Param("userMemberInfo") UserMemberInfo userMemberInfo);

}
