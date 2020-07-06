package com.atom.mybatis;

import com.atom.mybatis.bean.SecurityQuestionDO;
import com.atom.mybatis.mapper.SecurityQuestionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Atom
 */
@SpringBootTest
public class SecurityQuestionMapperTest {
    @Resource
    private SecurityQuestionMapper mapper;

    @Test
    void testSelectById() {
        SecurityQuestionDO securityQuestionDO = mapper.selectById(61);
        System.err.println(securityQuestionDO);
    }

    @Test
    void testSelectById2() {
        SecurityQuestionDO securityQuestionDO = mapper.selectById2(61);
        System.err.println(securityQuestionDO);
    }

    @Test
    void testSelectById3() {
        List<SecurityQuestionDO> securityQuestionDOS = mapper.selectByQuestionId(1);
        System.err.println();
        securityQuestionDOS.forEach(System.err::println);
    }

}
