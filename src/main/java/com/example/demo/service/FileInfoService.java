package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.DynamicDataSourceContextHolder;
import com.example.demo.entity.FileInfo;
import com.example.demo.mapper.FileInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.*;
import java.util.*;

/**
 * @author bofei
 * @date 2018/9/13 11:15
 */
@Service
public class FileInfoService {

    @Resource
    private FileInfoMapper fim;
//
//    public List<FileInfo> getFileInfos() {
//        return fim.getFileInfos();
//    }

    public List<JSONObject> getChildNodes(Integer id) throws SQLException {

        return fim.getChildNodes(id);
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<FileInfo> list = new LinkedList();
//        try {
//            String url = "spring.datasource.url=jdbc:sqlite:F:/splitespace/fileinfo1.db";
//            conn = DriverManager.getConnection(url);
//            String sql = "SELECT * FROM FileInfo WHERE pid = ?";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                FileInfo fileInfo = new FileInfo();
//                FileInfo jo = new FileInfo();
//                jo.put("id", rs.getInt("id"));
//                jo.put("pid", rs.getInt("pid"));
//                jo.put("path", rs.getString("path"));
//                jo.put("isParent", rs.getString("isParent"));
//                list.add(jo);
//            }
//
//        } finally {
//            if (rs != null) rs.close();
//            if (ps != null) ps.close();
//            if (conn != null) conn.close();
//        }
//
//        return list;
    }

    public List<JSONObject> test(String abpath, Integer key1, Integer key2) {

        DynamicDataSourceContextHolder dh = new DynamicDataSourceContextHolder();
        dh.setDataSourceType("db" + key1);
        long l = System.currentTimeMillis();
        List<FileInfo> childNodes1 = fim.getChildNodes2(abpath);
        System.out.println("查询树1时间： " + (System.currentTimeMillis() - l) + "毫秒");
        dh.setDataSourceType("db" + key2);
        long l2 = System.currentTimeMillis();
        List<FileInfo> childNodes2 = fim.getChildNodes2(abpath);
        System.out.println("查询树2时间： " + (System.currentTimeMillis() - l2) + "毫秒");
        List<FileInfo> diffrent3 = getDiffrent3(childNodes1, childNodes2);
        List<JSONObject> jsonObjects = JSONArray.parseArray(JSON.toJSONString(diffrent3), JSONObject.class);
        return jsonObjects;

    }
    private static List<FileInfo> getDiffrent3(List<FileInfo> list1, List<FileInfo> list2) {
        List<FileInfo> diff = new ArrayList<FileInfo>();
        long start = System.currentTimeMillis();
        Map<FileInfo, Integer> map = new LinkedHashMap<FileInfo, Integer>(list1.size() + list2.size());
        // 将List1元素放入Map，计数1
        for (FileInfo FileInfo : list1) {
            map.put(FileInfo, 1);
        }
        // 遍历List2，在Map中查找List2的元素，找到则计数+1；未找到则放入map，计数1
        for (FileInfo FileInfo : list2) {
            Integer count = map.get(FileInfo);
            if (count != null) {
                map.put(FileInfo, ++count);// 此处可优化，减少put次数，即为方法4
                continue;
            }
            FileInfo.setPath(FileInfo.getPath() + "_新增");
            FileInfo.setFlag("+++++++");

            map.put(FileInfo, 1);
        }
        for (Map.Entry<FileInfo, Integer> entry : map.entrySet()) {
            FileInfo jo = entry.getKey();
            if (entry.getValue() == 1) {
                if (jo.getFlag() == null) {
                    jo.setFlag("-----");
                    jo.setPath(jo.getPath() + "_删除");
                }
            }
            diff.add(jo);
        }
        System.out.println("比较 耗时：" + (System.currentTimeMillis() - start) + " 毫秒");
        return diff;
    }



}
