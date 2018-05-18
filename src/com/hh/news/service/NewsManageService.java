package com.hh.news.service;

import com.hh.news.entity.Column;
import com.hh.news.entity.News;
import com.hh.news.utils.PageBean;

import java.util.List;

public interface NewsManageService {
    //获得栏目名
     List<Column> getColumnName();

    //获得分页新闻
    PageBean getAllNews(PageBean pageBean, Integer page, String title);

    //根据栏目id查询所属新闻
    List<News> getNewsByCloumnId(Integer c_id);
    //添加一条新闻
    void saveNews(News news);
    //回数据
    News getNewsById(News news);
    //修改新闻
    void updataNews(News news);
    //逻辑删除 新闻
    void deleteNewsById(Integer[] p_id,Integer[] newsState);
    //获取当前新闻是否可用
    Integer[] getNewsState(Integer[] pid);
}
