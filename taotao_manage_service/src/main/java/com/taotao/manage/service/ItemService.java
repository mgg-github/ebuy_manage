package com.taotao.manage.service;

import com.github.abel533.mapper.Mapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zm on 5/24/17.
 */
@Service
public class ItemService extends BaseService<Item> {
    @Autowired
    Mapper<ItemDesc> itemDescMapper;

    public void insert(Item item, String desc) {
        item.setId(null);
        item.setStatus(1);
        Date created = new Date();
        item.setCreated(created);
        item.setUpdated(created);
        int itemCount = mapper.insert(item);
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        int descCount = itemDescMapper.insert(itemDesc);
    }
}
