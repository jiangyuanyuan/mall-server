package com.mmall.vo;

public class BlocalVo {
    private Integer id;
    private String name;
    private Integer blId;
    private String parse;
    private Integer type;
    public BlocalVo() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlId() {
        return blId;
    }

    public void setBlId(Integer blId) {
        this.blId = blId;
    }

    public String getParse() {
        return parse;
    }

    public void setParse(String parse) {
        this.parse = parse;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
