package com.hh.news.controller;

import com.hh.news.entity.User;
import com.hh.news.service.Show_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//用户模块
public class Show_User {

    @Autowired
    Show_UserService show_userService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "userLogin")
    public String userLogin(User user, HttpServletRequest request){

        User existUser = show_userService.getUser(user);
        HttpSession session = request.getSession();
        session.setAttribute("user",existUser);
        return "forward:/index.html";
    }

}
