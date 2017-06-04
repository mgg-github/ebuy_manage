package com.taotao.manage.service;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by zm on 17-5-23.
 */
public class BaseService<T extends BasePojo> {
    @Autowired
    Mapper<T> mapper;

    public List<T> select(T record) {
        return mapper.select(record);
    }

    public List<T> selectAll() {
        return mapper.select(null);
    }

    public PageInfo<T> selectPage(T record, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<T> pageList = mapper.select(record);
        return new PageInfo<T>(pageList);
    }

    public T selectByPrimaryKey(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    public T selectOne(T record){
        return mapper.selectOne(record);
    }
    public int insertSelective(T record) {
        record.setCreated(new Date());
        record.setUpdated(record.getCreated());
        return mapper.insertSelective(record);
    }

    public int insert(T record) {
        record.setCreated(new Date());
        record.setUpdated(record.getCreated());
        return mapper.insert(record);
    }

    public int updateSelective(T record){
        return mapper.updateByPrimaryKeySelective(record);
    }

    public int deleteByIds(List<Object> ids,Class tClass){
        Example example = new Example(tClass);
        example.createCriteria().andIn("id",ids);
        return mapper.deleteByExample(example);
    }
}
