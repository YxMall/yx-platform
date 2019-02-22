package com.yxmall.platform.modules.tool.msg.service.impl;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.modules.tool.msg.config.MobileConfig;
import com.yxmall.platform.modules.tool.msg.service.MobileSmsService;
import com.yxmall.platform.modules.tool.oss.storage.OssStorageConfig;

import java.io.IOException;

/**
 * @description: 腾讯云短信发送
 * @author: qing.wang.o
 * @create: 2019-01-26 22:37
 **/
public class QcloudMobileSmsServiceImpl extends MobileSmsService {


    public QcloudMobileSmsServiceImpl(MobileConfig config) {
        this.mobileConfig = config;
    }

    @Override
    public Result sendCodeMsg(String code, String phone) {
        return this.sendMsg(code, mobileConfig.getTemplateId(), phone);
    }

    @Override
    public Result sendMsg(String code, Integer templateId, String phone) {
        try {
            //数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            String[] params = {code};
            SmsSingleSender sSender = new SmsSingleSender(mobileConfig.getAppId(), mobileConfig.getAppKey());
            // 签名参数未提供或者为空时，会使用默认签名发送短信
            SmsSingleSenderResult result = sSender.sendWithParam("86", phone,
                    templateId, params, mobileConfig.getSign(), "", "");
            //{"result":0,"errmsg":"OK","ext":"","sid":"8:9r22ReiAyY8n3Sv5gjX20190127","fee":1} 返回值
            if (result.result == 0) {
                return Result.success();
            }
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (org.json.JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return Result.error("发送失败");
    }
}
