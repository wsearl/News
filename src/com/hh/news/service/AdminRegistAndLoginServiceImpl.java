package com.hh.news.service;

import com.hh.news.entity.Admin;
import com.hh.news.mapper.AdminRegistAndLoginMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminRegistAndLoginServiceImpl implements AdminRegistAndLoginService {
    @Autowired
    private AdminRegistAndLoginMapper adminRegistAndLoginMapper;
    
    /**
     * 登录验证
     * @return
     * @param admin
     */
    public Admin adminLogin(Admin admin) {
        Admin existAdmin = new Admin();
        //验证账号是否存在or错误
        String byUsername = adminRegistAndLoginMapper.adminLoginByUsername(admin);
        //验证密码是否错误or不错在
        String byPassword = adminRegistAndLoginMapper.adminLoginByPassword(admin);
        if (byUsername==null){
            throw  new RuntimeException("账号错误or不存在!");

        }else if (byPassword==null){
            throw  new RuntimeException("密码错误or不存在!");

        }
        //把查到的账号密码放入existAdmin中
        existAdmin.setUsername(byUsername);
        existAdmin.setPassword(byPassword);
        return existAdmin;

    }

    /**
     * 保存管理员信息
     * @param admin
     */
    public void saveAdmin(Admin admin) {
//        判断是存在用户名
       String existUsername =  adminRegistAndLoginMapper.getAllUsername(admin.getUsername());
       if (StringUtils.isBlank(existUsername)){
         adminRegistAndLoginMapper.saveAdmin(admin);
       }else {
           throw  new RuntimeException("账号已存在");
       }

    }
}
