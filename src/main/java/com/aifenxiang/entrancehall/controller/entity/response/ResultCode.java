package com.aifenxiang.entrancehall.controller.entity.response;

public class ResultCode {

    /**
     * 2xx  表示成功
     * 3xx  表示参数异常  (调用后台数据时参数验证失败等)
     * 4xx  程序运行时异常 (没有捕获的异常，如空指针异常等)
     * 5xx  程序内部错误  (预知已经捕获的异常，如获取缓存异常)
     * 6xx  内部服务调用失败    (调用第三方服务出错)
     * 7xx  权限错误    (会话失效、无权限)
     * 8xx  程序挂了
     * 9xx  安全验证失败
     */
    public final static int CODE_SUCCESS = 200;
    public final static int CODE_PARAM_ERROR = 300;
    public final static int CODE_RUNTIME_ERROR = 400;
    public final static int CODE_INTERNAL_ERROR = 500;
    public final static int CODE_INTERNAL_RPC_ERROR = 600;
    public final static int CODE_AUTHORITY = 700;
    public final static int CODE_JVM_DOWN = 800;
    public final static int CODE_SECURITY_ERROR = 900;

    //业务
    public final static int CODE_NO_USER_ERROR = 401;   //session 获取用户失败
    public final static int CODE_DATA_BASE_ERROR = 402;   //数据库异常
    public final static int CODE_USER_UNIQUE_ERROR = 402;   //用户名唯一

    public final static int RETURN_CODE_SUCCESS = 1;  //返回成功
    public final static int RETURN_CODE_ERROR = -1;  //返回错误
    public final static int RETURN_CODE_NULL = 0;  //返回数据为空

}
