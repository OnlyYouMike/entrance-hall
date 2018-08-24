package com.aifenxiang.entrancehall.controller.handler.impl;

import com.aifenxiang.entrancehall.controller.entity.key.AiUserSessionKey;
import com.aifenxiang.entrancehall.controller.entity.request.RegisterAiUserModel;
import com.aifenxiang.entrancehall.controller.entity.response.ResponseVo;
import com.aifenxiang.entrancehall.controller.entity.response.ResultCode;
import com.aifenxiang.entrancehall.controller.entity.verify.AiUser;
import com.aifenxiang.entrancehall.controller.exception.AiUserException;
import com.aifenxiang.entrancehall.controller.handler.AiUserHandler;
import com.aifenxiang.entrancehall.controller.model.AiUserModel;
import com.aifenxiang.entrancehall.controller.service.business.AiUserService;
import com.aifenxiang.foundation.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author: zj
 * @create: 2018-08-21 23:44
 **/
@Component
public class AiUserHandlerImpl implements AiUserHandler {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AiUserService aiUserService;

    @Override
    public UserDetails verifyAiUserSignIn(String userName, String password) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        String md5Passwd = userDetails.getPassword();
        if (md5Passwd.equals(Md5Util.getMd5(password,"utf-8"))){
            return userDetails;
        }else {
            throw new AiUserException("密码有误");
        }
    }

    @Override
    public ResponseVo registerAiFenXiang(RegisterAiUserModel userModel, HttpServletRequest request) {

        //等邮箱可以发送时再解开注释
        /*if (request.getSession().getAttribute(AiUserSessionKey.AI_USER_REGISTER_CODE) == null){
            throw new AiUserException("验证码失效, 请重新获取验证码");
        }

        if (! userModel.getRegisterCode().equals(request.getSession().getAttribute(AiUserSessionKey.AI_USER_REGISTER_CODE))){
            throw new AiUserException("验证码错误, 请重新输入");
        }*/

        AiUserModel aiUser = aiUserService.selectUserByUsename(new HashMap<String, Object>(){
            {
                put("username", userModel.getUsername());
            }
        });

        if (aiUser != null){
            throw new AiUserException("用户名已存在");
        }

        int row = aiUserService.insertAiUser(userModel);

        if (row != 1){
            throw new AiUserException("注册失败, 请重试");
        }

        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResultCode.RETURN_CODE_SUCCESS);
        responseVo.setMessage("注册成功");

        return responseVo;
    }
}
