package com.atom.mybatis.mapper;

import com.atom.mybatis.bean.UserMemberInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Atom
 */
@Mapper
public interface UserMemberInfoMapper {

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
}
