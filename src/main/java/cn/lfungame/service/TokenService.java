package cn.lfungame.service;

import cn.lfungame.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: xuke
 * @Date: 2018/6/1 10:57
 * @Description:
 */
@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     *生成token并缓存redis   规则token: id
     * @param id 玩家id
     * @param time 有效时间
     * @param unit 时间单位
     * @return
     */
    public Token genToken(Long id, Integer time, TimeUnit unit) {
        Token token = new Token();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,time);
        token.setExpiration(calendar.getTime());
        token.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
        stringRedisTemplate.opsForValue().set(token.getToken(), id+"", time, unit);
        return token;
    }

    /**
     * 根据用户id  取出缓存内的token
     * @param token
     * @return
     */
    public Long getToken(String token) {
        String id = stringRedisTemplate.opsForValue().get(token);
        if(id == null) return null;
        return Long.valueOf(id);
    }

}
