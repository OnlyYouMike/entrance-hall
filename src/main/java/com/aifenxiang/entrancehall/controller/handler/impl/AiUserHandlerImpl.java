package com.aifenxiang.entrancehall.controller.handler.impl;

import com.aifenxiang.entrancehall.controller.entity.verify.AiUser;
import com.aifenxiang.entrancehall.controller.exception.AiUserException;
import com.aifenxiang.entrancehall.controller.handler.AiUserHandler;
import com.aifenxiang.foundation.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author: zj
 * @create: 2018-08-21 23:44
 **/
@Component
public class AiUserHandlerImpl implements AiUserHandler {

    @Autowired
    private UserDetailsService userDetailsService;

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
}
