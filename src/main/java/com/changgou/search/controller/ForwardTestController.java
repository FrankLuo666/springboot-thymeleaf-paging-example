package com.changgou.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ForwardTestController {

    @RequestMapping(value="/test")
    public String showTestPage() {
        return "forward:/test2?param1=foo&param2=bar";
    }

}
