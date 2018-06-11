package cn.lfungame.controller;

import cn.lfungame.exception.BusinessException;
import cn.lfungame.exception.ErrorInfo;
import cn.lfungame.interceptor.LoginIgnore;
import cn.lfungame.service.SmsService;
import cn.lfungame.util.JsonUtil;
import cn.lfungame.util.ResponseMsg;
import cn.lfungame.util.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Auther: xuke
 * @Date: 2018/5/30 16:35
 * @Description:
 */
@Api(tags = "短信发送api")
@RestController
@RequestMapping(value = "/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @LoginIgnore
    @PostMapping(value = "/send")
    @ApiOperation(value="发送短信", notes="必填参数手机号phoneNumber \n  设备deviceId")
    ResponseMsg send(@RequestBody Map<String, String>param) throws Exception {
        ResponseMsg msg = new ResponseMsg<>();
        String phoneNumber = String.valueOf(param.get("phoneNumber"));
        if(!ValidatorUtil.isMobile(phoneNumber)) {
            throw new BusinessException(ErrorInfo.PHONENUMBER_IS_ERROR.code, ErrorInfo.PHONENUMBER_IS_ERROR.desc);
        }
        smsService.sendSms(phoneNumber);
        System.out.println(JsonUtil.beanToJson(param));
        return msg;
    }


}
