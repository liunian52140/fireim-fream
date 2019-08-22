package cn.fireim.base.framework.comm.constant;

/*
 * 消息响应基础枚举码
 */

public enum BaseEnum {
    SUCCESS(200, true,"success！"),
    ACCOUNTS_FROZEN(10001,false,"账户已经被冻结，请联系管理员解冻！"),
    ACCOUNTS_NOT_FOND(10002,false,"账户不存在！"),
    FAILD(500,false,"登陆超时，请重新登陆！"),
    HANDLE_FAILD(501,false,"操作失败");
    private int code;
    private boolean state;
    private String msg;

    BaseEnum(int code,boolean state, String msg) {
            this.code = code;
            this.state = state;
            this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
