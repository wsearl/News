package com.hh.news.mapper;

import com.hh.news.entity.Admin;
//管理员:登录and注册
public interface AdminRegistAndLoginMapper{
    //验证username
    String adminLoginByUsername(Admin admin);
    //验证password
    String adminLoginByPassword(Admin admin);
    //保存管理员信息
    void saveAdmin(Admin admin);
    //查询是否存在用户名
    String getAllUsername(String username);
}
