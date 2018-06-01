package cn.lfungame.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: xuke
 * @Date: 2018/5/15 18:42
 * @Description: 访问凭据token
 */
public class Token implements Serializable {
    /**
     * token字符串
     */
    private String token;
    /**
     * 有效时间
     */
    private Date expiration;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
