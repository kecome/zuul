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

    HTTP_CONNECTION_NULL(10002, "cbp请求返回null", ""),
    ClASSID_IS_NULL(10003, "班级id为空", ""),
    YUN_REP_ERROR(10005, "云基础平台数据返回异常", ""),
    HOMEWORK_PUBLIC(10004, "作业已发布", ""),
    HOMEWORK_NOT_PUBLIC(10009, "作业未发布", ""),
    HOMEWORK_IS_NULL(10007, "作业不存在", ""),
    STUDENTANSAWER_IS_NULL(10008, "学生作答不存在", ""),
    HOMEWORK_SUBMIT(10010, "作业已提交", ""),
    // PUBLICTIME_ERROR(10011, "作业预约发布时间不正确", ""),
    QUESTION_IS_NULL(10006, "作业题目不存在", "");

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
