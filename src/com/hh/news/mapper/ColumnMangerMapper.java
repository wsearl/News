package com.hh.news.mapper;
//栏目管理 mapper

import com.hh.news.entity.Column;
import com.hh.news.entity.QueryVo;

import java.util.List;
public interface ColumnMangerMapper {
    //查询栏目表
    List<Column> selectColumnAll(QueryVo vo);

    //计算总记录数
    Integer countColumn(QueryVo vo);

    //添加栏目
    void addColumn(Column column);

    //回显,栏目名
    String getColumnName(Integer c_id);

    //更新栏目名称
    void updataColumnName(Column column);

    //删除栏目,逻辑删除 把 状态值改为 0
    void deleteColumn(QueryVo queryVo);
    //查询栏目状态
    Integer getColumnState(Integer c_id);
}


