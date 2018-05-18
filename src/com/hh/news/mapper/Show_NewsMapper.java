package com.hh.news.mapper;

import com.hh.news.entity.News;

import java.util.List;

public interface Show_NewsMapper {

    //get all news
    List<News> getNews();
    //get news by id
    News getNewsById(Integer p_id);
    //by column id show news
    List<News> getNewsByColumnId(String c_id);
}
