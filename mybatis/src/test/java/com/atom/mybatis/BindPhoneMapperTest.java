package com.atom.mybatis;

import com.atom.mybatis.bean.BindPhoneDO;
import com.atom.mybatis.mapper.BindPhoneMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * BindPhoneMapperTest
 *
 * @author atom
 */
@SpringBootTest
public class BindPhoneMapperTest {
    @Resource
    private BindPhoneMapper bindPhoneMapper;

    @Test
    public void findByAmwayId() {
    }

    /**
     * 使用  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id") 返回主键ID
     */
    @Test
    public void saveUseOptionsAnnotation() {
        BindPhoneDO bindPhoneDO = new BindPhoneDO();
        bindPhoneDO.setMobilePhone("18500000000");
        bindPhoneDO.setStatus(0);
        bindPhoneDO.setBindDate(new Date());
        bindPhoneDO.setVersion(1);
        bindPhoneDO.setCreator("SYSTEM");
        int effectiveCounts = bindPhoneMapper.saveUseOptionsAnnotation(bindPhoneDO);
        System.err.println("effectiveCounts>>>" + effectiveCounts);
        System.err.println("ID>>>>" + bindPhoneDO.getId());
        int deletedCounts = bindPhoneMapper.deleteById(bindPhoneDO.getId());
        System.err.println("deletedCounts>>>>" + deletedCounts);
    }


    /**
     * 使用
     *
     * @SelectKey(statement = "SELECT LAST_INSERT_ID() AS ID", keyProperty = "id", keyColumn = "id", before = false, resultType = Long.class)
     * 返回主键ID
     * 这里的before指的是select语句是否在insert之前执行，显然我们这里需要先写后读，所以是false。
     * <p>
     * SELECT LAST_INSERT_ID() 可以使用别名as  SELECT LAST_INSERT_ID() as id
     */
    @Test
    public void save_use_SelectKey_Annotation_use_select_LAST_INSERT_ID() {
        BindPhoneDO bindPhoneDO = new BindPhoneDO();
        bindPhoneDO.setMobilePhone("18500000000");
        bindPhoneDO.setStatus(0);
        bindPhoneDO.setBindDate(new Date());
        bindPhoneDO.setVersion(1);
        bindPhoneDO.setCreator("SYSTEM");
        int effectiveCounts = bindPhoneMapper.saveUseSelectKeyAnnotationUseSelectLastInsertId(bindPhoneDO);
        System.err.println("effectiveCounts>>>" + effectiveCounts);
        System.err.println("ID>>>>" + bindPhoneDO.getId());
        int deletedCounts = bindPhoneMapper.deleteById(bindPhoneDO.getId());
        System.err.println("deletedCounts>>>>" + deletedCounts);
    }


    /**
     * 使用
     *
     * @SelectKey(statement = "SELECT LAST_INSERT_ID() AS ID", keyProperty = "id", keyColumn = "id", before = false, resultType = Long.class)
     * 返回主键ID
     * 这里的before指的是select语句是否在insert之前执行，显然我们这里需要先写后读，所以是false。
     * <p>
     * SELECT LAST_INSERT_ID() 不用别名as 也可以
     */
    @Test
    public void save_use_SelectKey_Annotation_use_select_LAST_INSERT_ID2() {
        BindPhoneDO bindPhoneDO = new BindPhoneDO();
        bindPhoneDO.setMobilePhone("18500000000");
        bindPhoneDO.setStatus(0);
        bindPhoneDO.setBindDate(new Date());
        bindPhoneDO.setVersion(1);
        bindPhoneDO.setCreator("SYSTEM");
        int effectiveCounts = bindPhoneMapper.saveUseSelectKeyAnnotationUseSelectLastInsertId2(bindPhoneDO);
        System.err.println("effectiveCounts>>>" + effectiveCounts);
        System.err.println("ID>>>>" + bindPhoneDO.getId());
        int deletedCounts = bindPhoneMapper.deleteById(bindPhoneDO.getId());
        System.err.println("deletedCounts>>>>" + deletedCounts);
    }


    /**
     * 使用
     *
     * @SelectKey(statement = "SELECT @@identity", keyProperty = "id", keyColumn = "id", before = false, resultType = Long.class)
     * 返回主键ID
     * 这里的before指的是select语句是否在insert之前执行，显然我们这里需要先写后读，所以是false。
     * <p>
     * SELECT LAST_INSERT_ID() 可以替换为 @@identity
     */
    @Test
    public void save_Use_selectKey_Annotation_and_identity() {
        BindPhoneDO bindPhoneDO = new BindPhoneDO();
        bindPhoneDO.setMobilePhone("18500000000");
        bindPhoneDO.setStatus(0);
        bindPhoneDO.setBindDate(new Date());
        bindPhoneDO.setVersion(1);
        bindPhoneDO.setCreator("SYSTEM");
        int effectiveCounts = bindPhoneMapper.saveUseSelectKeyAnnotationUseIdentity(bindPhoneDO);
        System.err.println("effectiveCounts>>>" + effectiveCounts);
        System.err.println("ID>>>>" + bindPhoneDO.getId());
        int deletedCounts = bindPhoneMapper.deleteById(bindPhoneDO.getId());
        System.err.println("deletedCounts>>>>" + deletedCounts);
    }

}

