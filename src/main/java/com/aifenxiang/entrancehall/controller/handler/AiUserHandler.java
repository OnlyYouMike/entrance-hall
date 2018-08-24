package com.aifenxiang.entrancehall.controller.handler;

import com.aifenxiang.entrancehall.controller.entity.request.RegisterAiUserModel;
import com.aifenxiang.entrancehall.controller.entity.response.ResponseVo;
import com.aifenxiang.entrancehall.controller.entity.verify.AiUser;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zj
 * @create: 2018-08-21 23:42
 **/
public interface AiUserHandler {


    public UserDetails verifyAiUserSignIn(String userName, String password);

    ResponseVo registerAiFenXiang(RegisterAiUserModel userModel, HttpServletRequest request);

    ResponseVo getRegisterCode(RegisterAiUserModel userModel, HttpServletRequest request);
}
