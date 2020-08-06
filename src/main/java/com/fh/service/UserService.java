package com.fh.service;

import com.fh.entity.Vip;

public interface UserService {
    Vip findNamePhone(String phone);

    Integer addVip(Vip vip);
}
