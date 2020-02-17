package com.mmall.pojo;

public class StreetInfo {
    private Integer streetid;

    private String streetname;

    private Integer blAreaid;

    private String parse;

    public StreetInfo(Integer streetid, String streetname, Integer blAreaid, String parse) {
        this.streetid = streetid;
        this.streetname = streetname;
        this.blAreaid = blAreaid;
        this.parse = parse;
    }

    public StreetInfo() {
        super();
    }

    public Integer getStreetid() {
        return streetid;
    }

    public void setStreetid(Integer streetid) {
        this.streetid = streetid;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname == null ? null : streetname.trim();
    }

    public Integer getBlAreaid() {
        return blAreaid;
    }

    public void setBlAreaid(Integer blAreaid) {
        this.blAreaid = blAreaid;
    }

    public String getParse() {
        return parse;
    }

    public void setParse(String parse) {
        this.parse = parse == null ? null : parse.trim();
    }
}