package com.mmall.pojo;

public class Sensorinfo {
    private Integer sensorid;

    private String blName;

    private Integer blComid;

    private String parse;

    public Sensorinfo(Integer sensorid, String blName, Integer blComid, String parse) {
        this.sensorid = sensorid;
        this.blName = blName;
        this.blComid = blComid;
        this.parse = parse;
    }

    public Sensorinfo() {
        super();
    }

    public Integer getSensorid() {
        return sensorid;
    }

    public void setSensorid(Integer sensorid) {
        this.sensorid = sensorid;
    }

    public String getBlName() {
        return blName;
    }

    public void setBlName(String blName) {
        this.blName = blName == null ? null : blName.trim();
    }

    public Integer getBlComid() {
        return blComid;
    }

    public void setBlComid(Integer blComid) {
        this.blComid = blComid;
    }

    public String getParse() {
        return parse;
    }

    public void setParse(String parse) {
        this.parse = parse == null ? null : parse.trim();
    }
}