package cn.lfungame.service;

import cn.lfungame.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: xuke
 * @Date: 2018/6/1 10:57
 * @Description:
 */
@Service
@Transactional
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
        //清除之前登录生成的token
        Set<String> set = stringRedisTemplate.keys(id+"*");
        for(String key : set) {
            stringRedisTemplate.delete(key);
        }
        //生成新的token
        Token token = new Token();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,time);
        token.setExpiration(calendar.getTime());
        token.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
        stringRedisTemplate.opsForValue().set(id+token.getToken(), id+"", time, unit);
        return token;
    }

    /**
     * 根据token  取出缓存内的id
     * @param token
     * @return
     */
    public Long getToken(String token) {
        Set<String> set = stringRedisTemplate.keys("*"+token);
        if(set != null && set.size() == 1) {
            for(String key : set) {
                String id = key.substring(0, 18);
                return Long.valueOf(id);
            }
        }
        return null;
    }

    /**
     * 更新token有效时间
     * @param key
     * @param time
     * @param unit
     */
    public void updateExpire(String key, Integer time, TimeUnit unit) {
        stringRedisTemplate.expire(key, time, unit);
    }

}
