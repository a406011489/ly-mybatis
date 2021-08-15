package com.ly.mybatis.mapper;

import com.ly.mybatis.entity.User;

public interface UserMapper {
    User selectOne(User user);
}
