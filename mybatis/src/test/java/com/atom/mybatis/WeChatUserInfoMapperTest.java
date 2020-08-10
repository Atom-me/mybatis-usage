package com.atom.mybatis;

import com.atom.mybatis.bean.WechatUserInfoDO;
import com.atom.mybatis.mapper.WeChatUserInfoMapper;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * @author Atom
 */
@SpringBootTest
public class WeChatUserInfoMapperTest {

    @Resource
    private WeChatUserInfoMapper weChatUserInfoMapper;

    @Test
    public void findByOpenId() {
        WechatUserInfoDO wechatUserInfoDO = WechatUserInfoDO.builder()
                .appid("456789")
                .unionid("567890poijkh"+ RandomUtils.nextInt())
                .followFlag(1)
                .nickname("Atom🌹🌂😂")
                .headImgUrl("http://cn.bing.com")
                .openid("y8iyouipp'kj")
                .version(1)
                .status(1)
                .build();
        wechatUserInfoDO.setCreateTime(new Date());
        wechatUserInfoDO.setUpdateTime(new Date());
        weChatUserInfoMapper.insert(wechatUserInfoDO);
        WechatUserInfoDO weChatUserInfo = weChatUserInfoMapper.findById(wechatUserInfoDO.getId());
        System.err.println(weChatUserInfo);
        int i = weChatUserInfoMapper.deleteById(wechatUserInfoDO.getId());
        System.err.println(i);
    }
}
