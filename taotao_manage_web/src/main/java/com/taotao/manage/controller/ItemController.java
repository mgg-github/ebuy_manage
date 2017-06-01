package com.taotao.manage.controller;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zm on 5/24/17.
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<Void> saveItem(Item item , @RequestParam("desc")String desc){
        if(StringUtils.isBlank(item.getTitle())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            itemService.insert(item,desc);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
