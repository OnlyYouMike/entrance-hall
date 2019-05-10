package com.aifenxiang.entrancehall.aop.accesslog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: zph
 * @Date: 19-5-9 17:21
 * @Description: 描述要记录日志的请求方法
 */
@Target(ElementType.METHOD)//表示该注解可以在方法上使用
@Retention(RetentionPolicy.RUNTIME)//表示该注解会保留到运行的时候(注解的生命周期)
public @interface AccessLog {
    String value() default "";//自定义注解中,方法的名称就是注解的参数名,方法的返回值类型就是注解参数的类型,当注解时不设置参数使用默认值
}
