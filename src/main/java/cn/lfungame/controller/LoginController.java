package cn.lfungame.controller;

import cn.lfungame.model.Gamer;
import cn.lfungame.service.GamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: xuke
 * @Date: 2018/5/15 18:23
 * @Description: 登录fdf
 */
@RestController
@RequestMapping("/liaowan")
public class LoginController {

    @Autowired
    private GamerService gamerService;

    @RequestMapping(value = "/login")
    Object login(@RequestBody Gamer gamer) throws Exception {
        //微信号登录
        if(!StringUtils.isEmpty(gamer.getWxId())) {

        }
        //手机号登录
        if(!StringUtils.isEmpty(gamer.getPhoneNumber())) {

        }
        //游客登录

        return null;
    }

}
