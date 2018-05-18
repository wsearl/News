package com.hh.news.utils;

import com.hh.news.entity.Column;
import com.hh.news.entity.News;

import java.util.List;

//分页
public class PageBean {

    private Integer currentPage;//当前页
    private Integer total;          //总记录数
    private Integer pageSize;     //每页显示的条数
    private List<Column> ColumnList; //存放栏目每页显示的数据
    private List<News> newsList;//存放新闻每页显示的数据
    private Integer totalPage; //总页数
    private List toDate; //时间转换


    public List getToDate() {
        return toDate;
    }

    public void setToDate(List toDate) {
        this.toDate = toDate;
    }
    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Column> getColumnList() {
        return ColumnList;
    }

    public void setColumnList(List<Column> columnList) {
        ColumnList = columnList;
    }
}
