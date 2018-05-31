package cn.lfungame.controller;

import cn.lfungame.service.SmsService;
import cn.lfungame.util.JsonUtil;
import cn.lfungame.util.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/send")
    Object send(@RequestBody Map<String, Object>param) throws Exception {
        ResponseMsg msg = new ResponseMsg<>();
        //smsService.sendSms("18682339084");
        System.out.println(JsonUtil.beanToJson(param));
        return msg;
    }
}
