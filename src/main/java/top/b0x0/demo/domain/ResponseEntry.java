package top.b0x0.demo.domain;

/**
 * @author TANG
 */
public class ResponseEntry {
    /**
     * 请求成功
     */
    public static final int SUCCESS = 0;
    /**
     * 服务器异常
     */
    public static final int SERIVE_EXCEPTION = 2;
    /**
     * 数据异常
     */
    public static final int DATA_EXCEPTION = 3;
    /**
     * 参数异常
     */
    public static final int PAREM_EXCEPTION = 4;

    /**
     * 状态码
     */
    private int code = 0;
    /**
     * 信息描述
     */
    private String message = "success";
    /**
     * 响应体
     */
    private Object body = null;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

}
