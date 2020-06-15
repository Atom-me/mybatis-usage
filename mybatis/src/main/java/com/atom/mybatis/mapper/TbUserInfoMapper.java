package com.atom.mybatis.mapper;

import com.atom.mybatis.bean.TbUserInfoDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author Atom
 */
@Mapper
public interface TbUserInfoMapper {

    int insert(TbUserInfoDO tbUserInfoDO);
}
