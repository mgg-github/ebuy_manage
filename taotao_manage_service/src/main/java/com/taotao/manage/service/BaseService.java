package com.taotao.manage.service;

import com.github.abel533.mapper.Mapper;
import com.taotao.manage.pojo.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zm on 17-5-23.
 */
public class BaseService<T extends BasePojo> {
    @Autowired
    Mapper<T> mapper;

    public List<T> select(T record){
        return mapper.select(record);
    }

    public T selectByPrimaryKey(Object id){
        return mapper.selectByPrimaryKey(id);
    }

    public int insertSelective(T record){
        return mapper.insertSelective(record);
    }

    public int insert(T record){
        return  mapper.insert(record);
    }

}
