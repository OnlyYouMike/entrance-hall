package com.aifenxiang.entrancehall.aop.accesslog;

import com.aifenxiang.entrancehall.controller.utils.RequestUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: zph
 * @Date: 19-5-9 17:30
 * @Description: 访问日志切面类
 */
@Aspect
@Component
@Log4j2
public class AccessLogAspect {
    @Autowired
    private HttpServletRequest request;

    /**
     * 定义请求日志切入点
     * 该方法用于声明切入点表达式
     * 后面的其他通知可以直接使用该方法名来引用当前的切入点表达式
     * 注解@Pointcut定义请求日志切入点,value参数@annotation(accessLog)代表切入点为所有加了@AccessLog注解的方法
     * Pointcut注解的参数value需要一个表达式,表达式代表切入点的位置
     *         表达式@annotation()代表加了指定注解的方法,括号内为指定注解类的类名首字母转小写(只适用于没有重名注解类的情况下)或类的全命名
     *          如 : 我自定义的注解AccessLog可以写成@annotation(accessLog)或@annotation(com.aifenxiang.entrancehall.aop.accesslog.AccessLog)
     *          和@annotation()表达式类似的还有@within,他代表加了指定注解的所有类的任意方法(指定注解需要是对类的注解)
     *          区别: @annotation()是针对方法注解的,@within和是针对类注解的
     * Annotation
     * @param accessLog
     */
    @Pointcut(value = "@annotation(accessLog)")
    public void logAspect(AccessLog accessLog){
        //标记方法,方法体不执行
    }

    /**
     * 被@AccessLog注解的方法的前置通知
     * @param joinPoint 切面信息
     * @param accessLog 切面注解
     */
    @Before("logAspect(accessLog)")//如果上边没有定义标记方法,这里就应该这样写 @Before("@annotation(accessLog)")
    public void doBefore(JoinPoint joinPoint, AccessLog accessLog){
        requestLog(request, accessLog, joinPoint);
    }

    // TODO 方法执行完毕后也需要记录日志
    /**
     * 方法返回通知
     * @param accessLog
     * @param returnObj
     */
    @AfterReturning(value = "logAspect(accessLog)",returning = "returnObj")//同上
    public void doAfterReturning(AccessLog accessLog, Object returnObj){
        System.out.println(returnObj);
    }

    // TODO 方法异常之后记录异常信息和堆栈信息
    /**
     * 异常通知
     * @param accessLog
     * @param e
     */
    @AfterThrowing(value = "logAspect(accessLog)",throwing = "e")
    public void doAfterThrowing(AccessLog accessLog, Throwable e){
        e.printStackTrace();
    }

    // TODO 需要修改日志的格式以及日志中参数的tostring
    public void requestLog(HttpServletRequest request, AccessLog accessLog, JoinPoint joinPoint){
        try {
            String url = request.getRequestURI().toString();
            String methodDepict = accessLog.value();
            String ip = RequestUtil.getRequestIp(request);
            String type = RequestUtil.getMethod(request);
            Map<String, Object> aspectInfo = RequestUtil.getJoinPointlnfoMap(joinPoint);
            Map<String, String[]> requestParams = RequestUtil.getRequestParams(request);
            StringBuffer loginfo = new StringBuffer();
            loginfo.append("requestLog---").append("url=").append(url).append(",class=")
                    .append(aspectInfo.get("classPath")).append(",method=").append(aspectInfo.get("methodName"))
                    .append(",requestType=").append(type);
            Map<String, Object> aspParams = aspectInfo.get("paramMap") == null ? null : (Map<String, Object>) aspectInfo.get("paramMap");
            if (aspParams != null){
                Set<String> aspKeys = aspParams.keySet();
                if (aspKeys.size() != 0){
                    loginfo.append(",aspParams:{");
                    for (String key : aspKeys) {
                        loginfo.append(key).append("=").append(aspParams.get(key) == null ? "null" : aspParams.get(key).toString()).append(",");
                    }
                    loginfo.append("}");
                }
            }

            if (requestParams != null){
                Set<String> reqKeys = requestParams.keySet();
                if (reqKeys.size() != 0){
                    loginfo.append(",requestParams:{");
                    for (String key : reqKeys) {
                        loginfo.append(key).append("=").append(requestParams.get(key) == null ? "null" : requestParams.get(key).toString()).append(",");
                    }
                    loginfo.append("}");
                }
            }

            log.info(loginfo.toString());
        }catch (Exception e){
            e.printStackTrace();
            log.error("访问日志记录出错");//访问日志出错不影响正常业务逻辑
        }
    }
}
