package com.taotao.manage.service;

import com.github.abel533.entity.Example;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.pojo.ItemCatData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zm on 5/23/17.
 */
@Service
public class ItemCatService extends BaseService<ItemCat> {
    public List<ItemCatData> queryAllItemCatToTree(){
        List<ItemCat> itemCatList = super.selectAll();
        HashMap<Long, List<ItemCat>> mapById = new HashMap<>();
        for (ItemCat itemCat :
                itemCatList) {
            Long parentId = itemCat.getParentId();
            if (!mapById.containsKey(parentId)){
                List<ItemCat> itemCats = new ArrayList<>();
                mapById.put(parentId,itemCats);
            }
            mapById.get(parentId).add(itemCat);
        }
        List<ItemCat> topLevel = mapById.get(0L);
        List<ItemCatData> topDataList = new ArrayList<>();
        for (ItemCat topCat :topLevel) {
            ItemCatData topData = new ItemCatData();
            topDataList.add(topData);
            String topUrl ="www.taotao.com/products/"+topCat.getId()+".html";
            topData.setName(topUrl+"|"+topCat.getName());
            if(topCat.getIsParent()){
                List<ItemCatData> middleDataList = topData.getItems();
                List<ItemCat> middleLevel = mapById.get(topCat.getId());
                for (ItemCat middleCat :
                     middleLevel) {
                    ItemCatData middleData = new ItemCatData();
                    middleDataList.add(middleData);
                    String middleUrl ="www.taotao.com/products/"+middleCat.getId()+".html";
                    middleData.setName(middleUrl+"|"+middleCat.getName());
                    if(middleCat.getIsParent()){
                        List<ItemCatData> bottomDataList = middleData.getItems();
                        List<ItemCat> bottomLevel = mapById.get(middleCat.getId());
                        for (ItemCat bottomCat :
                                bottomLevel) {
                            ItemCatData bottomData = new ItemCatData();
                            bottomDataList.add(bottomData);
                            String bottomUrl ="www.taotao.com/products/"+bottomCat.getId()+".html";
                            bottomData.setName(bottomUrl+"|"+bottomCat.getName());
                        }
                    }
                }
            }
        }
        return topDataList;
    }
}
