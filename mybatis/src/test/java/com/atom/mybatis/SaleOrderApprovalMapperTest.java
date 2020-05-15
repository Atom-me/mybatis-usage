package com.atom.mybatis;

import com.atom.mybatis.bean.ApproveDTO;
import com.atom.mybatis.mapper.SaleOrderApprovalMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Atom
 */
@SpringBootTest
public class SaleOrderApprovalMapperTest {

    @Resource
    private SaleOrderApprovalMapper mapper;

    @Test
    public void testUpdateProvider() {
        ApproveDTO dto = ApproveDTO.builder().approvalStatus(1)
                .approvalRemark("test remark")
                .id(1)
                .build();
        int i = mapper.updateApprovalStatusAndRemarkById(dto);
        System.out.println(i);
    }
}
