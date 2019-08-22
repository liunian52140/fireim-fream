package cn.fireim.base.framework.comm.conf;

import cn.fireim.base.framework.comm.interceptor.NoAuthorizationInterceptor;
import cn.fireim.base.framework.comm.interceptor.PrintLogInterceptor;
import cn.fireim.base.framework.comm.interceptor.SaveLogInterceptor;
import cn.fireim.base.framework.comm.properties.FrameBaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import redis.clients.jedis.JedisPool;

@Configuration
public class WebMvcConf implements WebMvcConfigurer {

    @Autowired
    private FrameBaseProperties frameBaseProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        if (frameBaseProperties.isPrintLog()){
            registry.addInterceptor(printLogInterceptor()).addPathPatterns("/api/**");
        }
        if (frameBaseProperties.isSaveLog()){
            registry.addInterceptor(saveLogInterceptor()).addPathPatterns("/api/**");
        }
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/api/**");

    }
    @Bean
    public NoAuthorizationInterceptor authenticationInterceptor() {
        return new NoAuthorizationInterceptor();
    }

    @Bean
    public FrameBaseProperties getFrameBaseProperties(){
        return new FrameBaseProperties();
    }

    @Bean
    public PrintLogInterceptor printLogInterceptor() {
        return new PrintLogInterceptor();
    }

    @Bean
    public SaveLogInterceptor saveLogInterceptor(){
        return new SaveLogInterceptor();
    }
}
