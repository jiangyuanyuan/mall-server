package com.mmall.pojo;

public class AreaInfo {
    private Integer areaid;

    private String areaname;

    private Integer blProvid;

    private String parse;

    public AreaInfo(Integer areaid, String areaname, Integer blProvid, String parse) {
        this.areaid = areaid;
        this.areaname = areaname;
        this.blProvid = blProvid;
        this.parse = parse;
    }

    public AreaInfo() {
        super();
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public Integer getBlProvid() {
        return blProvid;
    }

    public void setBlProvid(Integer blProvid) {
        this.blProvid = blProvid;
    }

    public String getParse() {
        return parse;
    }

    public void setParse(String parse) {
        this.parse = parse == null ? null : parse.trim();
    }
}