package com.smile.yx.platform;

import com.github.jsonzou.jmockdata.JMockData;
import com.smile.yx.platform.entity.SysUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description:
 * @author: qing.wang.o
 * @create: 2018-09-12 16:14
 **/
public class TestSms {
    public static void main(String[] args) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String password = "123456";
//        String encodedPassword = encoder.encode(password);
//        System.out.println(encodedPassword);
        SysUser user= JMockData.mock(SysUser.class);
        System.out.println(user);
    }
}
