package com.changgou.search.controller;

import com.changgou.search.entity.Page;
import com.changgou.search.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ForwardTestController {

    @RequestMapping(value="/test")
    public String showTestPage() {
        return "forward:/test2?param1=foo&param2=bar";
    }

}
