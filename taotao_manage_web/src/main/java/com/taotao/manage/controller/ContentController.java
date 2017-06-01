package com.taotao.manage.controller;

import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.Content;
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
    public ResponseEntity<PageInfo<Content>> selectAllContentByPage(
            @RequestParam(value = "categoryId",defaultValue ="0L")Long categoryId,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "20")Integer rows){
        try {
            Content content = new Content();
            content.setCategoryId(categoryId);
            PageInfo<Content> pageInfo = contentService.selectPage(content, page, rows);
            if(pageInfo==null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.ok(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
