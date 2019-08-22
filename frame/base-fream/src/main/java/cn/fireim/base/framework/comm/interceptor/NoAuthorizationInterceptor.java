package cn.fireim.base.framework.comm.interceptor;

import cn.fireim.base.framework.comm.annotation.NoAuthorization;
import cn.fireim.base.framework.comm.caches.CacheFinal;
import cn.fireim.base.framework.comm.caches.RedisHandle;
import cn.fireim.base.framework.comm.conf.WebMvcConf;
import cn.fireim.base.framework.comm.constant.BaseEnum;
import cn.fireim.base.framework.comm.constant.Result;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

public class NoAuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisHandle redisHandle;

    @Autowired
    private WebMvcConf webMvcConf;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        NoAuthorization annotation = handlerMethod.getMethod().getAnnotation(NoAuthorization.class);

        if(annotation != null || handlerMethod.getBeanType().getAnnotation(NoAuthorization.class)!= null){
            return true;
        }
        //如果token无效
        String key = CacheFinal.USER_AUTHORIZATION_TOKEN +request.getHeader(CacheFinal.TOKEN_HEADER);

        if(redisHandle.exists(key)){
            redisHandle.updateCachesTimeOut(key,webMvcConf.getFrameBaseProperties().getCachesTimeOut()*60);
            return true;
        }else{
            resWriter(response);
        }
        return false;
    }

    private boolean resWriter(HttpServletResponse response) throws Exception {
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.setContentType("application/json");
        Result result = new Result();
        result.setResult(BaseEnum.FAILD);
        response.getWriter().println(JSON.toJSONString(result));
        return false;
    }

}
