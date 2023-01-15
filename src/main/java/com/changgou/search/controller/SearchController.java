package com.changgou.search.controller;
import com.changgou.search.entity.Page;
import com.changgou.search.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/search")
public class SearchController {

    @GetMapping("")
    public String init(Model model){

        System.out.println("start search!!!!!!!!!!!!");
        StringBuilder url = new StringBuilder("/search/list");
        model.addAttribute("url",url);

        //封装分页数据并返回
        //1.总记录数
        //2.当前页
        //3.每页显示多少条
        Page<Product> page = new Page<Product>(
                29,
                1,
                Page.pageSize
        );
        model.addAttribute("page",page);


        return "search";
    }

    @PostMapping("/list")
    public String list(@ModelAttribute Page page, Model model) {

        System.out.println("start search list!!!!!!!!!!!!");
//        StringBuilder url = new StringBuilder("/search/list");
//        model.addAttribute("url",url);

        //封装分页数据并返回
        //1.总记录数
        //2.当前页
        //3.每页显示多少条
//        Page<Product> page = new Page<Product>(
//                29,
//                1,
//                Page.pageSize
//        );
//        model.addAttribute("page",page);


        return "search";
    }
}
