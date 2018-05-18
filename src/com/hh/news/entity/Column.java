package com.hh.news.entity;
//栏目
public class Column {
    public Integer c_id;    //主键id
    public Integer c_state = 1; //是否可用  1为可用
    public Integer c_p_id;  //外键
    public String c_name;   //栏目名称

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public Integer getC_state() {
        return c_state;
    }

    public void setC_state(Integer c_state) {
        this.c_state = c_state;
    }

    public Integer getC_p_id() {
        return c_p_id;
    }

    public void setC_p_id(Integer c_p_id) {
        this.c_p_id = c_p_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    @Override
    public String toString() {
        return "Column{" +
                "c_id=" + c_id +
                ", c_state=" + c_state +
                ", c_p_id=" + c_p_id +
                ", c_name='" + c_name + '\'' +
                '}';
    }
}
