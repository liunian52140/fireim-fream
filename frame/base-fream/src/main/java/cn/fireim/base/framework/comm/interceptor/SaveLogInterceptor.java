package cn.fireim.base.framework.comm.interceptor;

import cn.fireim.base.framework.api.enitity.admin.AdminResVO;
import cn.fireim.base.framework.api.enitity.admin.AdminSession;
import cn.fireim.base.framework.api.enitity.log.Log;
import cn.fireim.base.framework.api.service.LogService;
import cn.fireim.base.framework.comm.annotation.LogSave;
import cn.fireim.base.framework.comm.caches.CacheFinal;
import cn.fireim.base.framework.comm.caches.RedisHandle;
import cn.fireim.base.framework.comm.util.LogUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 表日志记录
 */
public class SaveLogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LogService logServiceImpl;

    @Autowired
    private RedisHandle redisHandle;

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            try {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                LogSave annotation = handlerMethod.getMethod().getAnnotation(LogSave.class);
                if (annotation != null) {
                    LogSave logSave = handlerMethod.getMethod().getAnnotation(LogSave.class);
                    Log log = LogUtil.setLog(request, logSave);
                    String jsonUser = redisHandle.getKey(CacheFinal.USER_AUTHORIZATION_TOKEN+
                            request.getHeader(CacheFinal.TOKEN_HEADER));
                    AdminResVO adminResVO=JSON.parseObject(jsonUser,AdminResVO.class);
                    AdminSession adminSession = adminResVO.getAdminSession();
                    log.setHandleUserId(adminSession.getId());
                    log.setHandleUserName(adminSession.getUserName());
                    logServiceImpl.saveLog(log);
                    logger.info("保存日志成功！");
                }
            } catch (Exception e) {
                logger.info("保存日志失败！", e);
                return true;
            }
            return true;
    }

}
