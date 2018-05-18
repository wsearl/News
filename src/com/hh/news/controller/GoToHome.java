package com.hh.news.controller;
import com.hh.news.entity.Column;
import com.hh.news.service.ColumnService;
import com.hh.news.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class GoToHome {
    @Autowired
    private ColumnService columnService;
     //栏目模块
    @RequestMapping(value = "index")
    public String tohome(Model model, Integer page, String c_name ){
        PageBean pb = new PageBean();
        Column column = new Column();
        column.setC_name(c_name);
        pb.setPageSize(4);
        if(page!=null) {
            pb.setCurrentPage(page);
        }else {
            pb.setCurrentPage(1);
        }
        int i = (pb.getCurrentPage() - 1 ) * pb.getPageSize();
        pb.setCurrentPage(i);
        //读取列表数据
        List<Column> list = columnService.selectColumnAll(pb,column);
        Integer count = columnService.countColumn();
        pb.setColumnList(list);
        pb.setTotal(count);
        Integer totalPage =(pb.getTotal()  +  pb.getPageSize()  - 1) / pb.getPageSize();
        pb.setTotalPage(totalPage);//设置总页数
        model.addAttribute("page",pb);
        return "index";
    }
    
    //添加栏目
    @RequestMapping(value ="addColumn")
    public String addColumn(Column column){
        columnService.addColumn(column);
        return "redirect:index";

    }
    
    //获得栏目名称  回显
    @RequestMapping(value ="getColumnName")
    public @ResponseBody
    String getColumnName(Integer c_id){
        return columnService.getColumn(c_id);

    }
    
    //修改栏目名
    @RequestMapping(value = "editColumnName")
    public String updataColumnName(Column column){
        columnService.updataColumnName(column);
        return  "forward: /index";
    }
    
    //删除栏目,逻辑删除!
    @RequestMapping(value = "deleteColumn")
    public @ResponseBody
    void deleteColumn(Integer c_id){
        //查询当前id栏目的状态
        Integer columnState = columnService.getColumnState(c_id);
        columnService.deleteColumn(c_id,columnState);
    }
}

