package com.taotao.manage.service;

import com.taotao.manage.pojo.ContentCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm on 6/1/17.
 */
@Service
public class ContentCatService extends BaseService<ContentCategory> {
    public int deleteById(ContentCategory contentCategory){
        ArrayList<Object> ids = new ArrayList<>();
        ids.add(contentCategory.getId());
        findAllIds(contentCategory.getId(),ids);
        int result = super.deleteByIds(ids,ContentCategory.class);
        ContentCategory siblingTmp = new ContentCategory();
        siblingTmp.setParentId(contentCategory.getParentId());
        List<ContentCategory> siblings = super.select(siblingTmp);
        if(siblings.isEmpty()){
            ContentCategory parent = new ContentCategory();
            parent.setId(contentCategory.getParentId());
            parent.setIsParent(false);
            super.updateSelective(parent);
        }
        return result;
    }

    private void findAllIds(Long id, ArrayList<Object> ids) {
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setParentId(id);
        List<ContentCategory> categoryList = super.select(contentCategory);
        for (ContentCategory category :
                categoryList) {
            ids.add(category.getId());
            if(category.getIsParent()){
                findAllIds(category.getId(),ids);
            }
        }
    }
}
