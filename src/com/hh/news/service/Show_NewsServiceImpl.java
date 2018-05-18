package com.hh.news.service;

import com.hh.news.entity.News;
import com.hh.news.mapper.Show_NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class Show_NewsServiceImpl implements Show_NewsService {

    @Autowired
    private Show_NewsMapper show_newsMapper;

    //get all news
    public List<News> getNews() {

        return show_newsMapper.getNews();
    }

    //get news by id
    public News getNewsById(Integer p_id) {
        return show_newsMapper.getNewsById(p_id);
    }
    //by column id show news
    public List<News> getNews(String c_id) {
        return show_newsMapper.getNewsByColumnId(c_id);
    }

}