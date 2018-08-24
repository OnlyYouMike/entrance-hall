package com.aifenxiang.entrancehall.controller.service.business;

import com.aifenxiang.entrancehall.controller.entity.request.RegisterAiUserModel;
import com.aifenxiang.entrancehall.controller.entity.verify.AiUser;
import com.aifenxiang.entrancehall.controller.model.AiUserModel;

import java.util.HashMap;

public interface AiUserService {
    int insertAiUser(RegisterAiUserModel userModel);

    AiUserModel selectUserByUsename(HashMap<String, Object> hashMap);
}
