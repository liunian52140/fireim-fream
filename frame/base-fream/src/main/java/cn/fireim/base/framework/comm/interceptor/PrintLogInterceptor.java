package cn.fireim.base.framework.comm.interceptor;

import com.alibaba.fastjson.JSON;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * 系统日志打印
 */

public class PrintLogInterceptor extends HandlerInterceptorAdapter {

    private  Logger logger= Logger.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String uri = request.getRequestURI();
            String method = handlerMethod.getMethod().getName();
            //记录日志
            logger.info("请求资源路径-------" + uri); //请求路径
            logger.info("请求方法---------" + method); //请求方法
            logger.info("请求参数---------" + JSON.toJSONString(request.getParameterMap())); //参数组
            logger.info("请求类------------" + handlerMethod.getBeanType().getName()); //请求controller
            return true;
    }

}
