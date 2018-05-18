package com.hh.news.service;

import com.hh.news.entity.Column;
import com.hh.news.utils.PageBean;

import java.util.List;

/**
 * 栏目
 */
public interface ColumnService {
    //查询栏目表
     List<Column> selectColumnAll(PageBean pb, Column column);
    //查询总记录数
     Integer countColumn();
    //添加栏目
    void addColumn(Column column);
    //回显 栏目名
    String getColumn(Integer c_id);
    //更新 栏目名
    void updataColumnName(Column column);
    //逻辑删除
    void deleteColumn(Integer c_id,Integer columnState);
    //获取栏目状态
    Integer getColumnState(Integer c_id);
}
