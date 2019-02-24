package com.yxmall.platform.encrypt;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @description: 配置文件加密
 * @author: qing.wang.o
 * @create: 2019-01-23 17:37
 **/
public class JasyptTest {
    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("G0CvDz7oJn6");
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("root");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
    }
}
