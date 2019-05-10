package com.aifenxiang.entrancehall.controller.utils;

import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zph
 * @Date: 19-5-10 11:48
 * @Description:
 */
public class RequestUtil {

    /**
     * 获取请求ip
     * 如果有多层代理,可能获取的IP不是真实IP
     *
     * @param request
     * @return
     */
    public static String getRequestIp(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        final String[] arr = ip.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ip = str;
                break;
            }
        }

        return ip;
    }

    /**
     * 获取请求方式,GET/POST/等
     *
     * @param request
     * @return
     */
    public static String getMethod(HttpServletRequest request) {
        return request.getMethod();
    }

    /**
     * 获取切面的详细信息,包括类路径/方法名称/传入方法的参数
     * @param joinPoint
     * @return
     */
    public static Map<String, Object> getJoinPointlnfoMap(JoinPoint joinPoint) {
        Map<String, Object> joinPointInfo = new HashMap<>();
        String classPath = joinPoint.getTarget().getClass().getName();//切面类的全命名
        String methodName = joinPoint.getSignature().getName();//获取当前切面方法的名称
        joinPointInfo.put("classPath", classPath);
        joinPointInfo.put("methodName", methodName);
        Class<?> clazz = null;
        CtMethod ctMethod = null;
        LocalVariableAttribute attr = null;
        int length = 0;
        int pos = 0;
        try {//下方使用Javassist来操作类,可能会有内存占用问题,后期优化
            clazz = Class.forName(classPath);//获取控制器的类对象
            String clazzName = clazz.getName();
            //ClassPool是CtClass 对象的容器,getDefault获取的 ClassPool 使用 JVM 的类搜索路径。如果程序运行在 JBoss 或者 Tomcat 等 Web 服务器上，
            // ClassPool 可能无法找到用户的类，因为 Web 服务器使用多个类加载器作为系统类加载器。在这种情况下，ClassPool 必须添加额外的类搜索路径
            ClassPool pool = ClassPool.getDefault();//CtClass 对象的容器,getDefault使用默认系统的类搜索路径,ClassPool 是一个存储 CtClass 的 Hash 表，类的名称作为 Hash 表的 key
            ClassClassPath classClassPath = new ClassClassPath(clazz);//通过类对象获取类路径
            pool.insertClassPath(classClassPath);//将需要的类添加到pool的类搜索路径中,防止pool获取不到自己创建的类
            CtClass ctClass = pool.get(clazzName);//get() 函数用于从 Hash 表中查找 key 对应的 CtClass 对象。如果没有找到，get() 函数会创建并返回一个新的 CtClass 对象，这个新对象会保存在 Hash 表中
            ctMethod = ctClass.getDeclaredMethod(methodName);//获取切面方法
            MethodInfo methodInfo = ctMethod.getMethodInfo();//获取该方法的详细信息
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();//获取方法的代码属性
            attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);//在代码属性信息中返回指定名称的属性,LocalVariableAttribute局部变量
            if (attr == null) {
                return joinPointInfo;//如果切面方法没有参数,那也不记录请求中的参数,直接返回
            }
            length = ctMethod.getParameterTypes().length;//获取该方法所有参数的类型,返回一个数组,长度是参数个数
            pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;//判断该方法是不是静态的,实例方法变量表的第一个参数是this,所以实例方法的参数下标应该+1
        } catch (ClassNotFoundException e) {
//            throw new ClassException("");
        } catch (NotFoundException e) {
//            throw new NotFindException("未找到参数类切");
        }
        Map<String, Object> paramMap = new HashMap<>();
        Object[] paramsArgsValues = joinPoint.getArgs();//获取切面方法的请求参数值的集合(就是spring从请求中转化过来的参数值)
        String[] paramsArgsNames = new String[length];
        for (int i = 0; i < length; i++) {
//            paramsArgsNames[i] = attr.variableName(i + pos);
            String paramsArgsName = attr.variableName(i + pos);//获取局部变量表中的参数,如果是实例方法,就从第二个下标开始获取
            if (paramsArgsName.equalsIgnoreCase("request") ||
                    paramsArgsName.equalsIgnoreCase("response") ||
                    paramsArgsName.equalsIgnoreCase("session")) {
                break;//排除控制器接口从Spring容器中获取的请求对象或返回值对象或会话对象
            }
            Object paramsArgsValue = paramsArgsValues[i];
            paramMap.put(paramsArgsName, paramsArgsValue);//把参数名称和参数值放入map中
        }

        joinPointInfo.put("paramMap", paramMap);//真正传入到接口中的参数
        return joinPointInfo;
    }

    public static Map<String, String[]> getRequestParams(HttpServletRequest request){
        return request.getParameterMap();
    }

}
