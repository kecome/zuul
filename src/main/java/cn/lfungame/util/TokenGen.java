package cn.lfungame.util;

import cn.lfungame.model.Token;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @Auther: xuke
 * @Date: 2018/5/15 17:51
 * @Description: token生成工具类
 */
public class TokenGen {

    public static Token genToken() {
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
        return token;
    }

    public static Token genToken(Integer period) {
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
        token.setPeriod(period);
        return token;
    }

}
