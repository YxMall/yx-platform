package com.yxmall.platform;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.yxmall.platform.common.utils.RedisUtils;
import com.yxmall.platform.common.utils.TimeUtils;
import com.yxmall.platform.modules.system.entity.SysRole;
import com.yxmall.platform.modules.system.entity.SysUser;
import com.yxmall.platform.modules.system.mapper.SysUserMapper;
import com.yxmall.platform.modules.system.service.SysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YxmallPlatformApplicationTests {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private RedisUtils redisService;


    @Autowired
    private RedisConnectionFactory factory;


    @Test
    public void contextLoads() {
//        MockConfig mockConfig = new MockConfig()
//                .byteRange((byte) 0, Byte.MAX_VALUE)
//                .shortRange((short) 0, Short.MAX_VALUE)
//                .intRange(0, 1)
//                .charSeed((char) 97, (char) 98);
//        for (int i = 0; i < 1000; i++) {
//            SysUser user = JMockData.mock(SysUser.class,mockConfig);
//            sysUserMapper.insert(user);
//        }
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(0L);
        sysRole.setRoleName("测试");
        boolean save = sysRoleService.save(sysRole);
        System.out.println("roleID" + sysRole.getRoleId());
    }

    @Test
    public void redisTest() {
//        redisService.set("aaa",178,(long)10);
//        System.out.print(redisService.get("wangyajun")+"======================");
//    }
        String s = TimeUtils.parseTime(LocalDateTime.now());
        System.out.println(s);
    }
}
