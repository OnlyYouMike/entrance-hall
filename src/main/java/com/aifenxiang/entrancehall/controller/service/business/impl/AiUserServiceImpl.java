package com.aifenxiang.entrancehall.controller.service.business.impl;

import com.aifenxiang.entrancehall.controller.entity.request.RegisterAiUserModel;
import com.aifenxiang.entrancehall.controller.entity.verify.AiUser;
import com.aifenxiang.entrancehall.controller.model.AiUserModel;
import com.aifenxiang.entrancehall.controller.service.business.AiUserService;
import com.aifenxiang.foundation.configclass.BaseMybatisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AiUserServiceImpl implements AiUserService {

    @Autowired
    private BaseMybatisDao baseMybatisDao;

    @Override
    public int insertAiUser(RegisterAiUserModel userModel) {
        return baseMybatisDao.insert("AiUserMapper.insertAiUser", userModel);
    }

    @Override
    public AiUserModel selectUserByUsename(HashMap<String, Object> hashMap) {
        return baseMybatisDao.selectOne("AiUserMapper.selectUserByUsename", hashMap);
    }
}
