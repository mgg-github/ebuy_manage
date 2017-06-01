package com.taotao.manage.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;

/**
 * Created by zm on 6/1/17.
 */
public class ItemCatResult{

    @JsonProperty("data")
    private List<ItemCatData> itemCatDataList;

    public List<ItemCatData> getItemCatDataList() {
        return itemCatDataList;
    }

    public void setItemCatDataList(List<ItemCatData> itemCatDataList) {
        this.itemCatDataList = itemCatDataList;
    }
}
