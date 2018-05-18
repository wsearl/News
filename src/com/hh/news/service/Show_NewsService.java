package com.hh.news.service;

import com.hh.news.entity.News;

import java.util.List;

public interface Show_NewsService {
    //获得一周热门新闻,在首页展示
    List<News> getNews();

    News getNewsById(Integer p_id);

    List<News> getNews(String c_id);
}
