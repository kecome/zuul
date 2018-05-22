package cn.lfungame.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: xuke
 * @Date: 2018/5/15 19:38
 * @Description: 用户登录实体
 */
public class User implements Serializable {
    /**
     * id号
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 最近修改时间
     */
    private Date updated;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
