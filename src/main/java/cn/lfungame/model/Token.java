package cn.lfungame.model;

import java.io.Serializable;

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
     * 有效时间(分m)
     */
    private Integer period;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
