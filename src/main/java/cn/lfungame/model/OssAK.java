package cn.lfungame.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: xuke
 * @Date: 2018/5/30 14:34
 * @Description: oss第三方访问授权AK
 */
@ApiModel(value="OssAK", description = "oss第三方访问授权AK")
public class OssAK {
    @ApiModelProperty(value="请求id")
    private String requestId;
    @ApiModelProperty(value="返回accessKeyId")
    private String accessKeyId;
    @ApiModelProperty(value="accessKeySecret")
    private String accessKeySecret;
    @ApiModelProperty(value="securityToken")
    private String securityToken;
    @ApiModelProperty(value="有效时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String expiration;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
