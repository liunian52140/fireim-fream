package cn.fireim.base.framework.comm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogSave {
    String key() default "";
    String value() default "";
    String moudleName() default  "";
    String handleType() default  "";
}
