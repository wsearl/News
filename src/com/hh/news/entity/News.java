package com.hh.news.entity;

import java.util.Date;

//新闻模块
public class News {

    private Integer p_id;
    private String title;
    private Date date;
    private String writer;
    private String img;
    private Integer state=1;
    private String c_name; //所属栏目
    private String content;
    private Integer p_c_id;
    private String formatDate;

    private Integer[] pid;

    public Integer[] getPid() {
        return pid;
    }

    public void setPid(Integer[] pid) {
        this.pid = pid;
    }

    public String getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    public Integer getP_c_id() {
        return p_c_id;
    }

    public void setP_c_id(Integer p_c_id) {
        this.p_c_id = p_c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
