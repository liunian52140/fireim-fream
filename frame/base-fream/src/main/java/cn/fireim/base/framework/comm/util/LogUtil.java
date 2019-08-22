package cn.fireim.base.framework.comm.util;

import cn.fireim.base.framework.api.enitity.log.Log;
import cn.fireim.base.framework.comm.annotation.LogSave;
import cn.fireim.base.framework.comm.caches.RedisHandle;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class LogUtil {

    public static Log setLog(HttpServletRequest request, LogSave logSave){
        Log log = new Log();
        log.setHandleIp(RequestUtil.getRequestIpAddress(request));//ip
        log.setHandleDateTime(new Date());
        log.setHandleMoudle(logSave.moudleName());
        log.setHandleType(logSave.handleType());
        log.setHandleName(request.getParameter(logSave.key()));
        log.setHandleValue(logSave.value());
        log.setResourcePath(request.getRequestURI());
        //操作用户名 用户id  后面加上去
        return log;
    }

    public static void main(String[] args) {
        System.out.println(
                RandomStringUtils.randomAlphabetic(100));
    }
}
