package com.aifenxiang.entrancehall.controller.entity.request;

import com.aifenxiang.entrancehall.controller.exception.AiUserException;
import com.aifenxiang.entrancehall.controller.model.AiUserModel;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

@Data
public class RegisterAiUserModel extends AiUserModel {

    private String registerCode;

    @Override
    public void verifyParam() {
        if (StringUtils.isBlank(super.getUsername())){
            throw new AiUserException("账号不能为空");
        }
        if (StringUtils.isBlank(super.getPassword())){
            throw new AiUserException("密码不能为空");
        }
        if (StringUtils.isBlank(super.getEmail())){
            throw new AiUserException("email不能为空");
        }
        if (StringUtils.isBlank(this.registerCode)){
            throw new AiUserException("验证码不能为空");
        }
    }
}
