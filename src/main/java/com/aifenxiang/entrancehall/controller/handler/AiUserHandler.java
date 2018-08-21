package com.aifenxiang.entrancehall.controller.handler;

import com.aifenxiang.entrancehall.controller.entity.verify.AiUser;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author: zj
 * @create: 2018-08-21 23:42
 **/
public interface AiUserHandler {


    public UserDetails verifyAiUserSignIn(String userName, String password);
}
