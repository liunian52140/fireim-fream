package cn.fireim.base.framework.comm.constant;


import lombok.Data;

/**
 * 基础响应类
 * @param <T>
 */
@Data
public class Result<T> {

    private Integer code;
    private boolean state;
    private String msg;
    private T date;

    public void setResult(BaseEnum baseEnum){
        this.code = baseEnum.getCode();
        this.state = baseEnum.isState();
        this.msg = baseEnum.getMsg();
    }
    public void setResult(T date,BaseEnum baseEnum){
        this.date =date;
        this.code = baseEnum.getCode();
        this.state = baseEnum.isState();
        this.msg = baseEnum.getMsg();
    }
}
