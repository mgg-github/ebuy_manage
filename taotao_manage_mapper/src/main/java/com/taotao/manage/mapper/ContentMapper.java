package com.taotao.manage.mapper;

import com.github.abel533.mapper.Mapper;
import com.taotao.common.bean.Content;

import java.util.List;

/**
 * Created by zm on 6/1/17.
 */
public interface ContentMapper extends Mapper<Content> {
    List<Content> selectPageByCategoryId(Long categoryId);
}
