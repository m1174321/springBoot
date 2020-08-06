package com.fh.service.impl;

import com.fh.entity.Vip;
import com.fh.mapper.UserMapper;
import com.fh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Vip findNamePhone(String phone) {
        return userMapper.findNamePhone(phone);
    }

    @Override
    public Integer addVip(Vip vip) {
        userMapper.addVip(vip);
        return vip.getId();
    }
}
