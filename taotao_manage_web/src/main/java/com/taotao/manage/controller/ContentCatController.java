package com.taotao.manage.controller;

import com.taotao.manage.pojo.ContentCategory;
import com.taotao.manage.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by zm on 6/1/17.
 */
@Controller
@RequestMapping("/content/category")
public class ContentCatController {
    @Autowired
    private ContentCatService contentCatService;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ContentCategory>> selectContentCategory(@RequestParam(value = "id",defaultValue = "0")Long parentId){
        try {
            ContentCategory contentCategory = new ContentCategory();
            contentCategory.setParentId(parentId);
            List<ContentCategory> contentCategoryList = contentCatService.select(contentCategory);
            if(contentCategoryList==null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.ok(contentCategoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ContentCategory> save(ContentCategory contentCategory){
        try {
            contentCategory.setId(null);
            contentCategory.setIsParent(false);
            contentCategory.setStatus(1);
            contentCategory.setSortOrder(1);
            contentCatService.insert(contentCategory);
            ContentCategory parentTmp = new ContentCategory();
            parentTmp.setId(contentCategory.getParentId());
            ContentCategory parent = contentCatService.selectOne(parentTmp);
            if(parent!=null&&parent.getIsParent()==false){
                parent.setIsParent(true);
                contentCatService.updateSelective(parent);
            }
            return ResponseEntity.ok(contentCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> update(ContentCategory contentCategory){
        if(contentCategory.getId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            contentCatService.updateSelective(contentCategory);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(ContentCategory contentCategory){
        if(contentCategory.getId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            contentCatService.deleteById(contentCategory);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
