package com.yxmall.platform;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxmall.platform.common.utils.TimeUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: qing.wang.o
 * @create: 2018-09-12 16:14
 **/
public class TestSms {
    public static void main(String[] args) throws IOException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "123456";
        String encodedPassword = encoder.encode(password);
        System.out.println(encodedPassword);
//        SysUser user= JMockData.mock(SysUser.class);
//        System.out.println(user);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://vip.veesing.com/smsApi/verifyCode");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("appId", "CCT1VKZB9U25"));
        params.add(new BasicNameValuePair("appKey", "TK8HX220RJYAB70A"));
        params.add(new BasicNameValuePair("phone", "17521698619"));
        params.add(new BasicNameValuePair("templateId", "78"));
        params.add(new BasicNameValuePair("variables", "1123;马马;你吃饭了吗"));
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params);
        post.setEntity(urlEncodedFormEntity);
        try {
            CloseableHttpResponse execute = client.execute(post);
            HttpEntity entity = execute.getEntity();
            System.out.println(EntityUtils.toString(entity, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        JSONObject smsJSon = SmsCode.sendSmsCode("CCT1VKZB9U25", "TK8HX220RJYAB70A", "17521698619", "78", "1123;马马;你吃饭了吗");
//        System.out.println(smsJSon.toJSONString());

//        String jsonData = FileUtils.readFileToString(ResourceUtils.
//                getFile("classpath:menu.json"), Charset.forName("UTF-8"));
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(jsonData);
//        System.out.println(jsonNode);
    }

    @Test
    public void redisTest() {
//        redisService.set("aaa",178,(long)10);
//        System.out.print(redisService.get("wangyajun")+"======================");
//    }
        String s = TimeUtils.parseTime(LocalDateTime.now(), TimeUtils.TimeFormat.SHORT_DATE_PATTERN_NONE);
        System.out.println(s);
    }

}
