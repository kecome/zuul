package cn.lfungame.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: xuke
 * @Date: 2018/5/15 18:40
 * @Description: 请求返回封装类
 */
@ApiModel(value="responseMsg", description = "响应前端的统一数据格式")
public class ResponseMsg<T> {
    /**
     * 请求响应码
     */
    @ApiModelProperty(value="请求响应码")
    private int code = 0;
    /**
     * 响应码描述
     */
    @ApiModelProperty(value="响应码描述")
    private String message = "成功";
    /**
     * 响应数据
     */
    @ApiModelProperty(value="响应数据")
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
