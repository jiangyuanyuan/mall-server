package com.mmall.pojo;

public class PicnfoKey {
    private Integer picid;

    private Integer sensorid;

    public PicnfoKey(Integer picid, Integer sensorid) {
        this.picid = picid;
        this.sensorid = sensorid;
    }

    public PicnfoKey() {
        super();
    }

    public Integer getPicid() {
        return picid;
    }

    public void setPicid(Integer picid) {
        this.picid = picid;
    }

    public Integer getSensorid() {
        return sensorid;
    }

    public void setSensorid(Integer sensorid) {
        this.sensorid = sensorid;
    }
}