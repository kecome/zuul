package cn.lfungame.controller;

import cn.lfungame.exception.BusinessException;
import cn.lfungame.exception.ErrorInfo;
import cn.lfungame.interceptor.LoginIgnore;
import cn.lfungame.service.SmsService;
import cn.lfungame.util.JsonUtil;
import cn.lfungame.util.ResponseMsg;
import cn.lfungame.util.ValidatorUtil;
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
@RestController
@RequestMapping(value = "/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @LoginIgnore
    @PostMapping(value = "/send")
    Object send(@RequestBody Map<String, Object>param) throws Exception {
        ResponseMsg msg = new ResponseMsg<>();
        String phoneNumber = String.valueOf(param.get("phoneNumber"));
        if(!ValidatorUtil.isMobile(phoneNumber)) {
            throw new BusinessException(ErrorInfo.PHONENUMBER_IS_ERROR.code, ErrorInfo.PHONENUMBER_IS_ERROR.desc);
        }
        smsService.sendSms(phoneNumber);
        System.out.println(JsonUtil.beanToJson(param));
        return msg;
    }


    @PostMapping(value = "/index")
    Object index(@RequestBody Map<String, Object>param) throws Exception {
        ResponseMsg msg = new ResponseMsg<>();
        return msg;
    }


    public static void main(String[] args) {
        Long id = 123L;
        System.out.println(StringUtils.isEmpty(id));
    }

}
