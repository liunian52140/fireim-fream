package cn.fireim.base.framework.comm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 基础配置
 */
@ConfigurationProperties(
        prefix = "frame.base"
)
@Data
public class FrameBaseProperties {
    private boolean saveLog;  //数据库操作日志
    private boolean printLog;  //系统打印日志
    private int cachesTimeOut; //缓存超时时间
    private int tokenLength;   //token 长度
}
