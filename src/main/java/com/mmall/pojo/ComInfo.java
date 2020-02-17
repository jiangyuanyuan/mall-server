package com.mmall.pojo;

public class ComInfo {
    private Integer comid;

    private String comname;

    private Integer blStreetid;

    private String parse;

    public ComInfo(Integer comid, String comname, Integer blStreetid, String parse) {
        this.comid = comid;
        this.comname = comname;
        this.blStreetid = blStreetid;
        this.parse = parse;
    }

    public ComInfo() {
        super();
    }

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname == null ? null : comname.trim();
    }

    public Integer getBlStreetid() {
        return blStreetid;
    }

    public void setBlStreetid(Integer blStreetid) {
        this.blStreetid = blStreetid;
    }

    public String getParse() {
        return parse;
    }

    public void setParse(String parse) {
        this.parse = parse == null ? null : parse.trim();
    }
}