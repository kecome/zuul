package cn.lfungame.controller;

import cn.lfungame.model.User;
import cn.lfungame.util.ResponseMsg;
import cn.lfungame.model.Token;
import cn.lfungame.util.TokenGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: xuke
 * @Date: 2018/5/15 18:23
 * @Description: 登录
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/")
    Object login(@RequestBody User user) {
        ResponseMsg msg = new ResponseMsg<>();
        if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            msg.setCode(-1);
            msg.setMessage("用户名或密码不能为空dfd");
            return msg;
        }
        Token token = TokenGen.genToken(30);
        stringRedisTemplate.opsForValue().set(token.getToken(), token.getToken(), token.getPeriod(), TimeUnit.MINUTES);
        msg.setCode(0);
        msg.setData(token);
        msg.setMessage("login success");
        return msg;
    }

    public static void main(String[] args) {
        User u = new User();
        u.setUsername("用户名");
        u.setPassword("密码");

    }
}
