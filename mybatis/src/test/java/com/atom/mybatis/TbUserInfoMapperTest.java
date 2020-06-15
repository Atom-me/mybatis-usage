package com.atom.mybatis;

import com.atom.mybatis.bean.TbUserInfoDO;
import com.atom.mybatis.doenums.AgeEnum;
import com.atom.mybatis.doenums.GenderEnum;
import com.atom.mybatis.doenums.GradeEnum;
import com.atom.mybatis.mapper.TbUserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Atom
 */
@SpringBootTest
public class TbUserInfoMapperTest {

    @Resource
    private TbUserInfoMapper tbUserInfoMapper;

    @Test
    public void testInsert() {
        TbUserInfoDO tbUserInfoDO = new TbUserInfoDO();
        tbUserInfoDO
                .age(AgeEnum.THREE)
                .gender(GenderEnum.FEMALE)
                .grade(GradeEnum.SECONDORY)
                .email("sarming@126.com")
                .name("Atom");
        tbUserInfoMapper.insert(tbUserInfoDO);
    }
}
