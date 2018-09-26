package com.smile.yx.platform;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.smile.yx.platform.entity.SysUser;
import com.smile.yx.platform.mapper.SysUserMapper;
import com.smile.yx.platform.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.smile.yx.platform.redis")
public class YxmallPlatformApplicationTests {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisService redisService;


    @Autowired
    private RedisConnectionFactory factory;



    @Test
    public void contextLoads() {
        MockConfig mockConfig = new MockConfig()
                .byteRange((byte) 0, Byte.MAX_VALUE)
                .shortRange((short) 0, Short.MAX_VALUE)
                .intRange(0, 1)
                .charSeed((char) 97, (char) 98);
        for (int i = 0; i < 1000; i++) {
            SysUser user = JMockData.mock(SysUser.class,mockConfig);
            sysUserMapper.insert(user);
        }
    }
    @Test
    public void redisTest(){
        redisService.set("wangyajun",178,(long)10);
        System.out.print(redisService.get("wangyajun")+"======================");
    }

}
