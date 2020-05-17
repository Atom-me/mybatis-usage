package com.atom.mybatis;

import com.atom.mybatis.bean.UserMemberInfo;
import com.atom.mybatis.mapper.UserMemberInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Atom
 */
@SpringBootTest
public class UserMemberInfoMapperTest {

    @Resource
    private UserMemberInfoMapper mapper;

    @Test
    public void testInsert() {
        UserMemberInfo info = new UserMemberInfo();
        info.setEmail("abc@xxx.com");
        info.setIdNo("110999888877663333");
        info.setRealName("Atom");
        info.setMobilePhone("18999999999");
        info.setUserName("sarming");
        int insert = mapper.insert(info);
        System.err.println(insert);
    }

    @Test
    public void testSelect() {
        UserMemberInfo info = mapper.selectById(1);
        System.err.println(info);
    }

    /**
     * 对加密字段的条件查询 如果直接使用对应字段查询，无法查出对应加密字段
     */
    @Test
    public void testSelectByIdNo() {
        UserMemberInfo info = mapper.selectByIdNo("110999888877665543");
        System.err.println(info);
    }

    /**
     * 对加密字段的条件查询使用对象查询，可查出对应加密字段
     */
    @Test
    public void testSelectByIdNoUseObject() {
        UserMemberInfo infoRequest = new UserMemberInfo();
        infoRequest.setIdNo("110999888877665543");
        UserMemberInfo info = mapper.selectByIdNoUseObject(infoRequest);
        System.err.println(info);
    }
}
