package com.hh.news.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hh.news.entity.Column;
import com.hh.news.service.Show_ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//前端页面,栏目的展示
@Controller
public class Show_Column {
    @Autowired
    private Show_ColumnService show_columnService;

    /**
     * 栏目展示
     *
     */
    @RequestMapping(value = "showColumn")
    public @ResponseBody
    List<Column> getColumn(){
     return show_columnService.getColumn();
    }


}
