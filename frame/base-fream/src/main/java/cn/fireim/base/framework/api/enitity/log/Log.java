package cn.fireim.base.framework.api.enitity.log;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class Log {
    private BigInteger id; //日志主键
    private BigInteger handleUserId; //操作人id
    private String handleUserName; //操作人名字
    private String handleName;//操作名
    private String handleValue;//操作值
    private Date handleDateTime; //操作时间
    private String handleMoudle;//操作模块
    private String resourcePath; //资源路径
    private String handleIp;//ip
    private String handleType;//操作类型  删除 新增 修改

}
