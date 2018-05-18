package com.hh.news.service;
import com.hh.news.entity.Column;
import com.hh.news.entity.News;
import com.hh.news.entity.QueryVo;
import com.hh.news.mapper.NewsManageMapper;
import com.hh.news.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//新闻管理
@Service
public class NewsManageServiceImpl implements NewsManageService {
    @Autowired
    private NewsManageMapper newsManageMapper;
    /**
     * 获得 下拉菜单的栏目名
     * @return
     */
    public List<Column> getColumnName() {
        return newsManageMapper.findAllColumnName();
    }
    /**
     * 新闻的分页
     * @param pageBean
     */
    public PageBean getAllNews(PageBean pageBean, Integer page,String title) {
        QueryVo queryVo = new QueryVo();
        News news = new News();
        news.setTitle(title);
        //每页显示4条
        pageBean.setPageSize(4);
        //判断当前页
        if(page!=null) {
            pageBean.setCurrentPage(page);
        }else {
            pageBean.setCurrentPage(1);
        }
        //计算当前页
        int i = (pageBean.getCurrentPage() - 1 ) * pageBean.getPageSize();
        pageBean.setCurrentPage(i);

        //吧查询对象赋值,搜索框的name ,当前页,每页显示数据
        queryVo.setTitle(news.getTitle());
        queryVo.setCurrentPage(pageBean.getCurrentPage());
        queryVo.setPageSize(pageBean.getPageSize());

        //查询每页显示的数据
        List<News> allNews = newsManageMapper.getAllNews(queryVo);

        List list = new ArrayList();
        //把查询到的数据,时间格式装换一下
        for(int a=0;a<allNews.size();a++){
            Date date = allNews.get(a).getDate();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = formatter.format(date);
            list.add(format);
            pageBean.setToDate(list);
        }
        //统计总数据的数量
        Integer newsTotal = newsManageMapper.getNewsTotal(queryVo);

        //放入pagebean
        pageBean.setNewsList(allNews);
        pageBean.setTotal(newsTotal);

        //设置总页数
        Integer totalPage =(pageBean.getTotal()  +  pageBean.getPageSize()  - 1) / pageBean.getPageSize();
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    /**
     * 根据id查询所属新闻
     * @param c_id
     * @return
     */
    public List<News> getNewsByCloumnId(Integer c_id) {
        return  newsManageMapper.getNewsByColumnId(c_id);
    }

    /**
     *  添加新闻
     * @param news
     */
    public void saveNews(News news) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = formatter.format(date.getTime());
            news.setFormatDate(format);
            newsManageMapper.saveNews(news);
    }

    /**
     * 根据id 回显数据
     * @param news
     * @return
     */
    public News getNewsById(News news) {

        News newsById = newsManageMapper.getNewsById(news);
        //转换时间格式
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(newsById.getDate());
        newsById.setFormatDate(format);

        return newsById;
    }

    /**
     * 修改新闻
     * @param news
     */
    public void updataNews(News news) {
        //转换时间格式,设置当前时间
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String format = formatter.format(date.getTime());

        news.setFormatDate(format);

        news.setDate(date);

        newsManageMapper.updateNews(news);
    }

    /**
     * 删除新闻 ,逻辑删除
     * @param pid
     */
    public void deleteNewsById(Integer[] pid,Integer[] newsState) {
      //遍历输出,完成还原功能
        for (int j = 0;j<newsState.length;j++){
           if (newsState[j]==1){
               newsState[j] = 0;
           }else {
               newsState[j] = 1;
           }
       }
       //创建查询对象
        QueryVo queryVo = new QueryVo();
        //遍历数组
        for (int i =0;i<newsState.length;i++){

            queryVo.setNewsState(newsState[i]);

            for (int k=0;k<pid.length;k++){

             queryVo.setP_id(pid[i]);
             break;

            }

            newsManageMapper.deleteNewsById(queryVo);

        }
    }

    /**
     * 更具id 新闻 是否可用
     * @param pid
     * @return
     */
    public Integer[] getNewsState(Integer[] pid) {
        Integer[] pid1 = new Integer[pid.length];
        for (int i = 0;i<pid.length;i++) {
            Integer newsState1 = newsManageMapper.getNewsState(pid[i]);
            pid1[i] = newsState1;
        }

        return pid1;
         }
}
