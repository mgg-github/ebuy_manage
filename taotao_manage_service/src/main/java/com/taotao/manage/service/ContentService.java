package com.taotao.manage.service;

import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.Content;
import com.taotao.manage.mapper.ContentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zm on 6/1/17.
 */
@Service
public class ContentService extends BaseService<Content>{
    public PageInfo<Content> selectPageByCategoryId(Long categoryId, Integer page, Integer rows) {
//        PageHelper.startPage(page,rows);
//        Example example = new Example(Content.class);
//        example.setOrderByClause("updated desc");
//        example.createCriteria().andEqualTo("categoryId",categoryId);
//        List<Content> contentList = mapper.selectByExample(example);
        List<Content> contentList = ((ContentMapper) mapper).selectPageByCategoryId(categoryId);
        return new PageInfo<>(contentList);
    }
}
