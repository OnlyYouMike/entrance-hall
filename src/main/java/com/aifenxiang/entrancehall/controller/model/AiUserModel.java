package com.aifenxiang.entrancehall.controller.model;

import com.aifenxiang.entrancehall.controller.exception.AiUserException;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * @author: zj
 * @create: 2018-08-22 13:18
 **/
@Data
public class AiUserModel {

    private String username;

    private String password;

    private String email;

    private String iphone;

    private Integer sex;


    public void verifyParam(){
        if (StringUtils.isBlank(this.username)){
            throw new AiUserException("账号不能为空");
        }
        if (StringUtils.isBlank(this.password)){
            throw new AiUserException("密码不能为空");
        }
        if (StringUtils.isBlank(this.email)){
            throw new AiUserException("email不能为空");
        }
    }
}
