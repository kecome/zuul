package cn.lfungame.exception;

/**
 * @Auther: xuke
 * @Date: 2018/5/18 09:13
 * @Description:
 */
public class BusinessException extends RuntimeException{
    /**
     * 异常码
     */
    private int code;
    /**
     * 异常提示信息
     */
    private String msg;

    public BusinessException(){
        super();
    }

    public BusinessException(int code, String msg){
        super();
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
