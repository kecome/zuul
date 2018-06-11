package cn.lfungame.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: xuke
 * @Date: 2018/5/15 18:42
 * @Description: 访问凭据token
 */
@ApiModel(value="token", description = "玩家对象")
public class Token implements Serializable {
    /**
     * token字符串
     */
    @ApiModelProperty(value="token字符串")
    private String token;
    /**
     * 有效时间
     */
    @ApiModelProperty(value="有效时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
