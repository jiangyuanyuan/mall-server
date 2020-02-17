package com.mmall.pojo;

public class ProvInfo {
    private Integer provid;

    private String provname;

    private String parse;

    public ProvInfo(Integer provid, String provname, String parse) {
        this.provid = provid;
        this.provname = provname;
        this.parse = parse;
    }

    public ProvInfo() {
        super();
    }

    public Integer getProvid() {
        return provid;
    }

    public void setProvid(Integer provid) {
        this.provid = provid;
    }

    public String getProvname() {
        return provname;
    }

    public void setProvname(String provname) {
        this.provname = provname == null ? null : provname.trim();
    }

    public String getParse() {
        return parse;
    }

    public void setParse(String parse) {
        this.parse = parse == null ? null : parse.trim();
    }
}