package com.hh.news.entity;

    //sql查询对象

public class QueryVo {
    private String c_name;
    private String title;
    private Integer currentPage;
    private Integer pageSize;
    private  Integer newsState;
    private Integer c_id;
    private  Integer columnState;
    private Integer p_id;

    public Integer getNewsState() {
        return newsState;
    }

    public void setNewsState(Integer newsState) {
        this.newsState = newsState;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public Integer getColumnState() {
        return columnState;
    }

    public void setColumnState(Integer columnState) {
        this.columnState = columnState;
    }


    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
}
