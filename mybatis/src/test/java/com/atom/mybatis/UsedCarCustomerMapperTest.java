package com.atom.mybatis;

import com.atom.mybatis.bean.CustomerQuery;
import com.atom.mybatis.bean.CustomerTags;
import com.atom.mybatis.bean.UsedCarCustomerInfo;
import com.atom.mybatis.mapper.UsedCarCustomerMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.BadSqlGrammarException;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Atom
 */
@SpringBootTest
public class UsedCarCustomerMapperTest {

    @Resource
    private UsedCarCustomerMapper mapper;

    @Test
    public void testListTypeHandlerSelect() {
        UsedCarCustomerInfo customerInfoByMobile = mapper.getCustomerInfoByMobile("18718181818");
        System.out.println(customerInfoByMobile);
    }

    @Test
    public void testListTypeHandlerUpdate() {
        CustomerTags tags = new CustomerTags();
        tags.setCustomerMobile("18718181818");
        List<String> strings = Arrays.asList("java", ".net", "php", "go");
        tags.setTags(strings);
        int i = mapper.updateTagsByCustomerMobile(tags);
        System.err.println(i);
    }

    /**
     * 条件判断 使用 <where>标签 或者<trim>标签
     *
     * @throws BadSqlGrammarException
     */
    @Test
    public void testSelectByConditionWrongWrite() {
        CustomerQuery query = new CustomerQuery();
        query.setAge(88);
        Assertions.assertThrows(BadSqlGrammarException.class, () -> mapper.selectByConditionWrongWrite(query));
    }

    @Test
    public void testSelectByConditionUseWhereTag() {
        CustomerQuery query = new CustomerQuery();
        query.setAge(88);
        List<UsedCarCustomerInfo> usedCarCustomerInfoList = mapper.selectByConditionUseWhereTag(query);
        usedCarCustomerInfoList.forEach(System.out::println);
    }
}
