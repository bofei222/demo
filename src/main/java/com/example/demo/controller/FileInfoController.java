package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.DynamicDataSourceContextHolder;
import com.example.demo.service.FileInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author bofei
 * @date 2018/9/13 11:28
 */
@Controller
public class FileInfoController {
    @Resource
    private FileInfoService fis;

//    @GetMapping("/getFileInfos")
//    public List<JSONObject> getFIleInfos() {
//        return fis.getFileInfos();
//    }

    @GetMapping("/tree")
    public String tree() {

        return "tree";
    }

    @PostMapping("/getChildNodes")
    @ResponseBody
    public List<JSONObject> getChlidNodes(String abpath, Integer id, Integer key1, Integer key2) throws SQLException {

        long l = System.currentTimeMillis();
        if (id == null) {
            id = 0;
//            DynamicDataSourceContextHolder dh = new DynamicDataSourceContextHolder();
//            dh.setDataSourceType("db" + key1);
//
//            return fis.getChildNodes(id);
        }

        if (abpath == null) {
            abpath = "F:\\";
        }
        List<JSONObject> test = fis.test(abpath, key1, key2);
        System.out.println("后台收到请求到响应请求时间: " + (System.currentTimeMillis() - l));
        return test;



    }

//    @PostMapping("/getChildNodes2")
//    @ResponseBody
//    public List<JSONObject> getChlidNodes2(Integer id, Integer key) throws SQLException {
//
//        if (id == null) {
//            id = 0;
//        }
//        DynamicDataSourceContextHolder dh = new DynamicDataSourceContextHolder();
//        dh.setDataSourceType("db" + key);
//
//        return fis.getChildNodes(id);
//    }

    @GetMapping("/compareTree")
    public String compareTree() {
        return "compareTree";
    }
}
