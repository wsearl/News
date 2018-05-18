package com.hh.news.controller;

import com.hh.news.entity.Column;
import com.hh.news.entity.News;
import com.hh.news.service.NewsManageService;
import com.hh.news.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiMenuItemUI;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//新闻模块
@Controller
public class NewManage {
    @Autowired
    private NewsManageService newsManageService;
    /**
     * 跳转新闻管理,查询所属栏目下拉菜单,以及分页数据展示
     */
    @RequestMapping(value = "index_2")
    public String goToNews(Model model,Integer page,String title){
        //加载所属栏目拉下菜单
        List<Column> columnList = newsManageService.getColumnName();
        model.addAttribute("cList",columnList);
        //加载所有新闻
        PageBean pageBean = new PageBean();
        pageBean = newsManageService.getAllNews(pageBean,page,title);

        model.addAttribute("pageBean",pageBean);

        return "index_2";
    }

    /**
     * 按所属栏目筛选新闻
     * @param model
     * @param value
     * @return
     */
    @RequestMapping(value = "getNewsByColumn")
    public String getNewsByColumn(Model model, Integer value){
        List<News> ByCloumnId = newsManageService.getNewsByCloumnId(value);
        PageBean pageBean = new PageBean();
        pageBean.setNewsList(ByCloumnId);
        //把查询到的数据,时间格式装换一下
        List list = new ArrayList();
        for(int a=0;a<ByCloumnId.size();a++){
            Date date = ByCloumnId.get(a).getDate();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
            String format = formatter.format(date);
            list.add(format);
            pageBean.setToDate(list);
        }
        model.addAttribute("pageBean",pageBean);
        //加载所属栏目拉下菜单
        List<Column> columnList = newsManageService.getColumnName();
        model.addAttribute("cList",columnList);
        return "index_2";
        //添加所属栏目列
    }
    /**
     * 添加新闻
     */
    @RequestMapping(value = "addNews")
    public String addNews(Model model,News news,MultipartFile pictureFile) throws IOException {
        if (pictureFile.getSize()>0){

        // 图片上传
        // 设置图片名称，不能重复，可以使用uuid
        String picName = UUID.randomUUID().toString();

        // 获取文件名
        String oriName = pictureFile.getOriginalFilename();
        // 获取图片后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));
        //上传到自定的目录
        pictureFile.transferTo(new File("C:/Users/hh/workspace/uploadImg/"+ picName + extName));

        //图片的名设置到news.img中.
        news.setImg( picName + extName);
        //调用service添加news
        newsManageService.saveNews(news);
        return "redirect:/index_2";
        }
        model.addAttribute("message","必须插入标题图片:)");
        return "forward: /index_2";
    }

    /**
     * 编辑新闻
     */
    @RequestMapping(value = "getNewsById")
    public @ResponseBody News getNewsById(News news){
        return  newsManageService.getNewsById(news);
    }

    /**
     * 修改新闻
     */
    @RequestMapping(value = "editNews")
    public String editNews(News news,MultipartFile pictureFile) throws IOException {
        if (pictureFile.getSize()>0){
        // 图片上传
        // 设置图片名称，不能重复，可以使用uuid
        String picName = UUID.randomUUID().toString();

        // 获取文件名
        String oriName = pictureFile.getOriginalFilename();
        // 获取图片后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));
        //上传到自定的目录
        pictureFile.transferTo(new File("C:/Users/hh/workspace/uploadImg/"+ picName + extName));

        //图片的名设置到news.img中.
        news.setImg( picName + extName);
        }
        newsManageService.updataNews(news);
        return "redirect:/index_2";
    }
    /**
     * 逻辑删除
     */
    @RequestMapping(value = "deleteNews")
    public @ResponseBody
    void deleteNews(Integer pid[]){

        Integer[] newsState = newsManageService.getNewsState(pid);

        newsManageService.deleteNewsById(pid,newsState);
    }
}
