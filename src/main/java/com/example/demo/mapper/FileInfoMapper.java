package com.example.demo.mapper;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author bofei
 * @date 2018/9/13 11:13
 */
@Mapper
public interface FileInfoMapper {

    @Select("SELECT * FROM FileInfo")
    List<JSONObject> getFileInfos();

    @Select("SELECT * FROM FileInfo WHERE pid = #{id}")
    List<JSONObject> getChildNodes(@Param("id") Integer id);

    @Select("SELECT * FROM FileInfo WHERE abppath = #{abppath}")
    List<FileInfo> getChildNodes2(@Param("abppath") String abppath);
}
