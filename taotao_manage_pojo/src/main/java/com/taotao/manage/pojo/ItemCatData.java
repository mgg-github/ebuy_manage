package com.taotao.manage.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm on 6/1/17.
 */
public class ItemCatData {
    @JsonProperty("n")
    private String name;
    @JsonProperty("s")
    private List<ItemCatData> items = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemCatData> getItems() {
        return items;
    }

    public void setItems(List<ItemCatData> items) {
        this.items = items;
    }

}
