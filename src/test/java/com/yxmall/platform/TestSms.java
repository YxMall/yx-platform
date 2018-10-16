package com.yxmall.platform;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.Charset;

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

//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost post = new HttpPost("https://vip.veesing.com/smsApi/verifyCode");
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("appId", "CCT1VKZB9U25"));
//        params.add(new BasicNameValuePair("appKey", "TK8HX220RJYAB70A"));
//        params.add(new BasicNameValuePair("phone", "17521698619"));
//        params.add(new BasicNameValuePair("templateId", "78"));
//        params.add(new BasicNameValuePair("variables", "1123;马马;你吃饭了吗"));
//        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params);
//        post.setEntity(urlEncodedFormEntity);
//        try {
//            CloseableHttpResponse execute = client.execute(post);
//            HttpEntity entity = execute.getEntity();
//            System.out.println(EntityUtils.toString(entity, "UTF-8"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        JSONObject smsJSon = SmsCode.sendSmsCode("CCT1VKZB9U25", "TK8HX220RJYAB70A", "17521698619", "78", "1123;马马;你吃饭了吗");
//        System.out.println(smsJSon.toJSONString());

        String jsonData = FileUtils.readFileToString(ResourceUtils.
                getFile("classpath:menu.json"), Charset.forName("UTF-8"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonData);
        System.out.println(jsonNode);
    }


}
