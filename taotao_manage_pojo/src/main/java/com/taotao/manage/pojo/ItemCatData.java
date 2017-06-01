package com.taotao.manage.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by zm on 6/1/17.
 */
public class ItemCatData {
    @JsonProperty("n")
    private String name;
    @JsonProperty("s")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<?> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }

}
