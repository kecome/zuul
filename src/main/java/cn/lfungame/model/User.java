package cn.lfungame.model;

import java.io.Serializable;

/**
 * @Auther: xuke
 * @Date: 2018/5/15 19:38
 * @Description: 用户登录实体
 */
public class User implements Serializable {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
