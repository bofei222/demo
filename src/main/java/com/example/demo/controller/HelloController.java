package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bofei
 * @date 2018/9/11 15:23
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://bofei.com");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

}
