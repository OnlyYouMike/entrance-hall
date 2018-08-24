package com.aifenxiang.entrancehall.controller.service.business;

import com.aifenxiang.entrancehall.controller.entity.verify.AiUser;

import java.util.HashMap;

public interface AiUserService {
    int insertAiUser(AiUser userModel);

    AiUser selectUserByUsename(HashMap<String, Object> hashMap);
}
