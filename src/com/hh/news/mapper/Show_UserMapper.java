package com.hh.news.mapper;

import com.hh.news.entity.User;

public interface Show_UserMapper {
    //查找是否存在user
    User getUser(User user);

}
