package com.fh.mapper;

import com.fh.entity.Vip;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    Vip findNamePhone(String phone);

    Integer addVip(Vip vip);
}
