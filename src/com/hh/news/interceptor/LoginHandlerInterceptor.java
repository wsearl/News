package com.hh.news.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 从request中获取session
        HttpSession session = request.getSession();
        // 从session中获取username
        Object username = session.getAttribute("admin");
        // 判断username是否为null
        if (username != null) {
            // 如果不为空则放行
            return true;
        } else {
            // 如果为空则跳转到登录页面
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

    }



    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
