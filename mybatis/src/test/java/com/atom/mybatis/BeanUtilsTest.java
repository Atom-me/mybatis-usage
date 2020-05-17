package com.atom.mybatis;

import com.atom.mybatis.bean.UserMemberInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

/**
 * @author Atom
 */

public class BeanUtilsTest {

    @Test
    public void testBeanUtils() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        UserMemberInfo info = new UserMemberInfo();
        info.setIdNo("yuio");
        info.setCreateTime(LocalDateTime.now());

        String createTime = BeanUtils.getProperty(info, "createTime");
        BeanUtils.setProperty(info,"updateTime",LocalDateTime.now());
        System.err.println(createTime.getClass().getCanonicalName());
        System.err.println(createTime);

        Object createTime1 = PropertyUtils.getProperty(info, "createTime");

        PropertyUtils.setProperty(info,"updateTime",LocalDateTime.now());

        System.err.println(createTime1.getClass().getCanonicalName());
        System.err.println(createTime1);
    }
}
