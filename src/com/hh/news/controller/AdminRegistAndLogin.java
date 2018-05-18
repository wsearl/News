package com.hh.news.controller;

import com.hh.news.entity.Admin;
import com.hh.news.service.AdminRegistAndLoginService;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminRegistAndLogin{
    @Autowired
    private AdminRegistAndLoginService adminRegistAndLoginService;
    //跳转到登录页面
    @RequestMapping(value = "/login")
    public String goToLogin(){
     return "login";
    }

    //跳转到注册页面
    @RequestMapping(value = "/regist")
    public String goToRegist(){
     return "regist";
    }
    //管理员登录
    @RequestMapping(value = "loginAdmin")
    public String loginAdmin(Admin admin, Model model, HttpSession httpSession){
        try {
            Admin adminLogin = adminRegistAndLoginService.adminLogin(admin);
            httpSession.setAttribute("admin",adminLogin);

        }catch (RuntimeException e){
            String message = e.getMessage();
             model.addAttribute("message",message);
            return "forward:/login";
        }
        return "forward: /index";
    }
    //管理员注销
    @RequestMapping(value = "/logout")
    public String logoutAdmin(HttpSession httpSession){
        httpSession.removeAttribute("admin");
        return "forward:/index";
    }
    //管理员注册
    @RequestMapping(value = "/userRegist")
    public String userRegist(Model model,Admin admin){
    try {
        adminRegistAndLoginService.saveAdmin(admin);
        }catch (Exception e){
            String message = e.getMessage();
            model.addAttribute("message",message);
            return "forward:/regist";
        }

        return "redirect:/login";
    }
}
