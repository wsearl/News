package com.hh.news.controller;

import com.hh.news.entity.User;
import com.hh.news.service.Show_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//用户模块
@Controller
public class Show_User {

    @Autowired
    Show_UserService show_userService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping(value = "userLogin")
    public void userLogin(User user, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
        User existUser = show_userService.getUser(user);

        String username = existUser.getUsername();

        response.sendRedirect("index.html?user="+username);

        }catch (Exception e){
            String message = e.getMessage();
            request.setAttribute("message",message);
            request.getRequestDispatcher("/login.html").forward(request,response);

        }

    }

}
