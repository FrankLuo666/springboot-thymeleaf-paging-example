package com.changgou.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForwardTestController2 {

    @RequestMapping(value="/test2")
    public String showTestPage(@RequestParam String param1, @RequestParam String param2) {
        return "testPageView";
    }

}
