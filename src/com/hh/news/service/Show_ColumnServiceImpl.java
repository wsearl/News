package com.hh.news.service;

import com.hh.news.entity.Column;
import com.hh.news.mapper.Show_ColumnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Show_ColumnServiceImpl  implements Show_ColumnService{
    @Autowired
    private Show_ColumnMapper show_columnMapper;
    /**
     * 获得栏目
     * @return
     */
    public List<Column> getColumn() {
        return show_columnMapper.getColumn();
    }
}
