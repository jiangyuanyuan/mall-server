package com.mmall.vo;

public class BlocalVo {
    private Integer id;
    private String name;
    private Integer blId;
    private String parse;
    private String day;
    private Long sum;
    private Integer type;
    public BlocalVo(Integer id,String name,Integer blId,String parse,String day,Long sum) {
        this.id =id;
        this.name = name;
        this.blId = blId;
        this.parse = parse;
        this.day = day;
        this.sum = sum;
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



    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
