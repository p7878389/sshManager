package com.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/6/28.
 */
@Controller
@RequestMapping(value = "")
public class HomeController {

    @RequestMapping(value = "/")
    public String loginPage(){
        return "redirect:/admin/login.html";
    }
}
