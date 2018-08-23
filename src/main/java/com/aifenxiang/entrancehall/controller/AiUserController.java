package com.aifenxiang.entrancehall.controller;

import com.aifenxiang.entrancehall.controller.exception.AiUserException;
import com.aifenxiang.entrancehall.controller.handler.AiUserHandler;
import com.aifenxiang.entrancehall.controller.model.AiUserModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.aifenxiang.entrancehall.controller.apiconmon.Api.USER;
import static com.aifenxiang.entrancehall.controller.apiconmon.Api.USER_MAPPING.*;

/**
 * @author: zj
 * @create: 2018-08-21 22:51
 **/
@RestController
@RequestMapping(USER)
public class AiUserController {

    @Autowired
    private AiUserHandler aiUserHandler;


    @RequestMapping(value = SIGN_IN,method = RequestMethod.GET)
    public String signInAiFenXiang(String username,String password){
        if (StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            throw new AiUserException("账号密码不能为空");
        }
        UserDetails userDetails = aiUserHandler.verifyAiUserSignIn(username, password);
        return "";
    }

    @RequestMapping(value = SIGN_UP,method = RequestMethod.POST)
    public String signUpAiFenXiang(@RequestBody AiUserModel userModel){
        userModel.verifyParam();

        return "";
    }

}
