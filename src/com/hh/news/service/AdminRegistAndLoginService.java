package com.hh.news.service;

import com.hh.news.entity.Admin;


//管理员模块
public interface AdminRegistAndLoginService {

    //登录验证
    Admin  adminLogin(Admin admin);
    //管理注册
    void saveAdmin(Admin admin);
}
