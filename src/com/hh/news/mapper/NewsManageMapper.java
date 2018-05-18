package com.hh.news.mapper;

import com.hh.news.entity.Column;
import com.hh.news.entity.News;
import com.hh.news.entity.QueryVo;
import java.util.List;

//新闻管理接口
public interface NewsManageMapper {
    //获得下拉菜单的内容
    List<Column> findAllColumnName();

    //获得所有新闻
    List<News> getAllNews(QueryVo queryVo);

    //获得新闻的总记录数
    Integer getNewsTotal(QueryVo queryVo);

    //根据栏目id筛选对应的所属新闻
    List<News> getNewsByColumnId(Integer c_id);

    //保存新闻
    void saveNews(News news);

    //根据id获得新闻,回显数据
    News getNewsById(News news);

    //修改新闻
    void updateNews(News news);

    //逻辑删除新闻
    void deleteNewsById(QueryVo queryVo);
    //查询当前新闻是否可用
    Integer getNewsState(Integer p_id);
}
