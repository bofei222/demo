package com.example.demo.entity;

/**
 * @author bofei
 * @date 2018/9/11 18:02
 */
public class FileInfo {
    private Integer id;
    private Integer pid;
    private String path;
    private String isParent;
    private String abpath;
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAbpath() {
        return abpath;
    }

    public void setAbpath(String abpath) {
        this.abpath = abpath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileInfo fileInfo = (FileInfo) o;

        return abpath.equals(fileInfo.abpath);
    }

    @Override
    public int hashCode() {
        return abpath.hashCode();
    }
}
