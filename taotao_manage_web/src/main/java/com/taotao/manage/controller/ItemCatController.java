package com.taotao.manage.controller;

import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by zm on 5/23/17.
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;
    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<ItemCat>> queryItemCat(@RequestParam(name = "id",defaultValue = "0") Long parentId){
        try {
            ItemCat itemCat = new ItemCat();
            itemCat.setParentId(parentId);
            List<ItemCat> itemCats = itemCatService.select(itemCat);
            if(itemCats==null||itemCats.isEmpty()){
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(itemCats);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
