package com.hh.news.service;

import com.hh.news.entity.User;
import com.hh.news.mapper.Show_UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Show_UserServiceImpl implements Show_UserService {

     @Autowired
     Show_UserMapper show_userMapper;


     public User getUser(User user) {

         User user1 =  show_userMapper.getUser(user);

         if (user1==null){
            throw  new RuntimeException("用户名不存在或者密码错误:(");
         }

         return user1;

    }
}
