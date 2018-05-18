package com.hh.news.controller;

import com.hh.news.entity.News;
import com.hh.news.service.Show_NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class Show_News {

    @Autowired
    private Show_NewsService show_newsService;

    /**
     * 获得所有新闻
     * @return
     */
    @RequestMapping(value = "showNews")
    public @ResponseBody
    List<News> getNews(HttpServletRequest request){
        String c_id = request.getParameter("c_id");
        if (c_id.equals("")){
         return show_newsService.getNews();
        }
        return show_newsService.getNews(c_id);
    }



  /**
     * 根据id获得对应的新闻
     * PathVariable 获得请求路径的id
     * @return
     */
     @RequestMapping(value = "showNewsById/{p_id}")
    public @ResponseBody
     News getNewsById(@PathVariable Integer p_id){
        return show_newsService.getNewsById(p_id);
     }
}
