package com.taotao.manage.controller;

import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.Content;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zm on 6/1/17.
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> selectAllContentByPage(
            @RequestParam(value = "categoryId",defaultValue ="0")Long categoryId,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "20")Integer rows){
        try {
            PageInfo<Content> pageInfo = contentService.selectPageByCategoryId(categoryId, page, rows);
            EasyUIResult easyUIResult = new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
            if(pageInfo==null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.ok(easyUIResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> save(Content content){
                if(content.getCategoryId()==null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
        try {
            content.setId(null);
            contentService.insert(content);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> update(Content content){
        if(content.getId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            contentService.updateSelective(content);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
