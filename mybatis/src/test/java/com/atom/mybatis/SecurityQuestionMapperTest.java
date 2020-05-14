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
        System.out.println(securityQuestionDO);
    }

    @Test
    void testSelectById2() {
        SecurityQuestionDO securityQuestionDO = mapper.selectById2(61);
        System.out.println(securityQuestionDO);
    }

    @Test
    void testSelectByIdAndAda() {
        List<SecurityQuestionDO> securityQuestionDOS = mapper.selectByQuestionIdAndAda(1, "593");
        System.out.println();
        securityQuestionDOS.forEach(System.out::println);
    }

}
