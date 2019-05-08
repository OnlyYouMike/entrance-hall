package com.aifenxiang.entrancehall.controller;

import com.aifenxiang.entrancehall.controller.model.request.RegisterAiUserModel;
import com.aifenxiang.entrancehall.controller.model.response.ResponseVo;
import com.aifenxiang.entrancehall.controller.exception.AiUserException;
import com.aifenxiang.entrancehall.controller.handler.AiUserHandler;
import com.aifenxiang.entrancehall.controller.model.AiUserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.hswebframework.web.logging.AccessLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.aifenxiang.entrancehall.controller.apiconmon.Api.USER;
import static com.aifenxiang.entrancehall.controller.apiconmon.Api.USER_MAPPING.*;

/**
 * @author: zj
 * @create: 2018-08-21 22:51
 **/
@RestController
@RequestMapping(USER)
@Slf4j
public class AiUserController {

    @Autowired
    private AiUserHandler aiUserHandler;


    @AccessLogger("用户登录")
    @RequestMapping(value = SIGN_IN,method = RequestMethod.GET)
    public String signInAiFenXiang(String username,String password){
        if (StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            log.error("账号密码不能为空");
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

    @RequestMapping(value = REGISTER, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo registerAiFenXiang(RegisterAiUserModel userModel, HttpServletRequest request){
        userModel.verifyParam();
        ResponseVo responseVo = aiUserHandler.registerAiFenXiang(userModel, request);
        return responseVo;
    }

    @RequestMapping(value = GETREGISTERCODE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo getRegisterCode(RegisterAiUserModel userModel, HttpServletRequest request){

        ResponseVo responseVo = aiUserHandler.getRegisterCode(userModel, request);

        return responseVo;
    }

}
