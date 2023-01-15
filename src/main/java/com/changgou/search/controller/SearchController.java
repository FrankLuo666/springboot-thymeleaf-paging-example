package com.changgou.search.controller;
import com.changgou.search.entity.Page;
import com.changgou.search.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

        Product product = new Product("car","12345",50);
        List<Product> list = new ArrayList<Product>(){{

        }};
        Product product1 = new Product("car1","12345",50);
        Product product2 = new Product("car2","12345",50);
        Product product3 = new Product("car3","12345",50);
        Product product4 = new Product("car4","12345",50);
        Product product5 = new Product("car5","12345",50);
        list.add(product1);
        list.add(product2);
        list.add(product3);
        list.add(product4);
        list.add(product5);
        product.setList(list);
        model.addAttribute("product",product);


        //封装分页数据并返回
        //1.总记录数
        //2.当前页
        //3.每页显示多少条
        Page<Product> page = new Page<Product>(
                product.getList().size(),
                1,
                Page.pageSize
        );
        model.addAttribute("page",page);

        return "search";
    }

    @PostMapping("/list")
    public String list(@ModelAttribute("product") Product product, @ModelAttribute("page") Page page, Model model) {

        System.out.println("start search list!!!!!!!!!!!!");
        StringBuilder url = new StringBuilder("/search/list");
        model.addAttribute("url",url);

        //从数据库取list，这里是假数据
        List<Product> list = new ArrayList<Product>(){{

        }};
        Product product1 = new Product("car1","12345",50);
        Product product2 = new Product("car2","12345",50);
        Product product3 = new Product("car3","12345",50);
        Product product4 = new Product("car4","12345",50);
        Product product5 = new Product("car5","12345",50);
        list.add(product1);
        list.add(product2);
        list.add(product3);
        list.add(product4);
        list.add(product5);

        //根据页面数设置页面的显示数据
        int startIndex = (product.getPageNum()-1) * Page.pageSize;
        int endIndex = product.getPageNum() * Page.pageSize;
        if(endIndex > list.size()){
            endIndex = list.size();
        }
        List subList = list.subList(startIndex, endIndex);
        product.setList(list);

        //封装分页数据并返回
        //1.总记录数
        //2.当前页
        //3.每页显示多少条
        Page<Product> page2 = new Page<Product>(
                product.getList().size(),
                product.getPageNum(),
                Page.pageSize
        );
        model.addAttribute("page",page2);

        model.addAttribute("product",product);
        return "search";
    }
}
