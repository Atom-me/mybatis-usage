package com.atom.mybatisplus;

import com.atom.mybatisplus.mapper.UserMapper;
import com.atom.mybatisplus.model.UserDO;
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
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<UserDO> userDOList = userMapper.selectList(null);
        Assert.assertEquals(5, userDOList.size());
        userDOList.forEach(System.err::println);
    }
}
