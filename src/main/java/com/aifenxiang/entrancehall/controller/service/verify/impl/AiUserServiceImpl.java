package com.aifenxiang.entrancehall.controller.service.verify.impl;

import com.aifenxiang.entrancehall.controller.entity.verify.AiUser;
import com.aifenxiang.foundation.configclass.BaseMybatisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author: zj
 * @create: 2018-08-21 22:46
 **/
public class AiUserServiceImpl implements UserDetailsService {

    @Autowired
    private BaseMybatisDao baseMybatisDao;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDetails userDetails = baseMybatisDao.selectOne("AiUserMapper.loadUserByUsername", new HashMap<String, Object>() {
            {
                put("username", name);
            }
        });
        if (null == userDetails){
            throw new UsernameNotFoundException("该用户["+name+"]没有权限");
        }
        return userDetails;
    }
}
