package com.hh.news.service;

import com.hh.news.entity.Column;
import com.hh.news.entity.QueryVo;
import com.hh.news.mapper.ColumnMangerMapper;
import com.hh.news.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnMangerMapper columnMangerMapper;
        QueryVo queryVo = new QueryVo();
    /**
     * 查询栏目表
     */
    public List<Column> selectColumnAll(PageBean pb, Column column) {
        queryVo.setC_name(column.getC_name());
        queryVo.setCurrentPage(pb.getCurrentPage());
        queryVo.setPageSize(pb.getPageSize());
        //调用mapper
        return columnMangerMapper.selectColumnAll(queryVo);
    }



    /**
     * 计算总记录数
     * @return
     */
    public Integer countColumn() {
        return columnMangerMapper.countColumn(queryVo);
    }

    /**
     * 添加栏目
     * @param
     */
    public void addColumn(Column column) {
        columnMangerMapper.addColumn(column);
    }

    /**
     * 回显栏目
     */
    public String getColumn(Integer c_id) {
        return  columnMangerMapper.getColumnName(c_id);
    }

    /**
     * 更新栏目名称
     */
    public void updataColumnName(Column column) {
        columnMangerMapper.updataColumnName(column);
    }

    /**
     * 删除栏目 逻辑删除
     */
    public void  deleteColumn(Integer c_id,Integer columnState){
        if (columnState==1){
            columnState = 0;
        }else {
            columnState = 1;
        }
        QueryVo queryVo = new QueryVo();
        queryVo.setC_id(c_id);
        queryVo.setColumnState(columnState);
        columnMangerMapper.deleteColumn(queryVo);
    }

    /**
     * 获取当前id的状态
     * @param c_id
     * @return
     */
    public Integer getColumnState(Integer c_id) {
        return columnMangerMapper.getColumnState(c_id);
    }

}
