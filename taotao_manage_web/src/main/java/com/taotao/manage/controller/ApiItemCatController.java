package com.taotao.manage.controller;

import com.taotao.manage.pojo.ItemCatData;
import com.taotao.manage.pojo.ItemCatResult;
import com.taotao.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

/**
 * Created by zm on 6/1/17.
 */
@Controller
@RequestMapping("/api/item/cat")
public class ApiItemCatController {
    @Autowired
    private ItemCatService itemCatService;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ItemCatResult> queryItemCat(){
        try {
            List<ItemCatData> itemCatDataList = itemCatService.queryAllItemCatToTree();
            if(itemCatDataList!=null&&!itemCatDataList.isEmpty()){
                ItemCatResult itemCatResult = new ItemCatResult();
                itemCatResult.setItemCatDataList(itemCatDataList);
                return ResponseEntity.ok(itemCatResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
