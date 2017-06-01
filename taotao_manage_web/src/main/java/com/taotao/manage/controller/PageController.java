package com.taotao.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zm on 5/22/17.
 */
@Controller
@RequestMapping("page")
public class PageController {

    @RequestMapping(value = "{pageName}",method = RequestMethod.GET)
    public String toPage(@PathVariable("pageName")String pagaName){
        return pagaName;
    }
}
