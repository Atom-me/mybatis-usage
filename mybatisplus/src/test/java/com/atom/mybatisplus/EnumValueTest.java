package com.atom.mybatisplus;

import com.atom.mybatisplus.doenums.AgeEnum;
import com.atom.mybatisplus.doenums.GenderEnum;
import com.atom.mybatisplus.doenums.GradeEnum;
import com.atom.mybatisplus.doenums.UserState;
import com.atom.mybatisplus.mapper.UserInfoMapper;
import com.atom.mybatisplus.model.UserInfoDO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Atom
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumValueTest {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Test
    public void testInsertEnumValue() {
        UserInfoDO userInfo = new UserInfoDO();
        userInfo.setAge(AgeEnum.ONE);
        userInfo.setName("Atom");
        userInfo.setEmail("atom@126.com");
        userInfo.setGender(GenderEnum.MALE);
        userInfo.setUserState(UserState.ACTIVE);
        userInfo.setGrade(GradeEnum.HIGH);
        Assert.assertTrue(userInfoMapper.insert(userInfo) > 0);

        List<UserInfoDO> userInfoDOList = userInfoMapper.selectList(null);
        userInfoDOList.forEach(System.err::println);
    }
}
