package cn.lfungame.exception;

/**
 * @Auther: xuke
 * @Date: 2018/5/18 09:15
 * @Description:
 */
public enum ErrorInfo {
    ARGUMENT_NOT_VALID(10100, "参数不合法",""),
    RUNTIME_EXCEPTIOON(10101, "RuntimeException系统异常",""),
    EXCEPTION(10102, "Exception系统异常", ""),
    UNKNOW_EXCEPTION(10103, "未知系统异常", ""),
    ID_IS_NULL(10001,"id不能为空", "\\u5bf9\\u8c61\\u4e3a\\u7a7a"),

    USER_IS_NULL(703,"您尚未登陆，或者Session超时", "\\u60a8\\u5c1a\\u672a\\u767b\\u9646\\uff0c\\u6216\\u8005\\u0053\\u0065\\u0073\\u0073\\u0069\\u006f\\u006e\\u8d85\\u65f6"),

    /**
     * 撩玩登录参数较验
     */
    ARGUMENT_NULL_ALL(10002, "wxId, phoneNumber, deviceId不能都为空", ""),
    PHONENUMBER_IS_ERROR(10003, "不是合法的手机号码", ""),
    PHONECODE_IS_ERROR(10004, "验证码不正确", ""),
    DEVICE_ID_NULL(10005, "设备id不能为空", ""),
    SMS_ERROR(10006, "短信接口返回错误", ""),



    QUESTION_IS_NULL(10340, "作业题目不存在", "");

    /**
     * 异常码
     */
    public int code;
    /**
     * 异常描述
     */
    public String desc;
    /**
     * 异常描述  unicode编码
     */
    public String unicode;
    private ErrorInfo(int code, String desc, String unicode) {
        this.code = code;
        this.desc = desc;
        this.unicode = unicode;
    }
}
