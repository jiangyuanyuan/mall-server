package com.mmall.pojo;

import java.util.Date;

public class PeopleOutInfo {
    private Integer id;

    private Date outTime;

    private Date backTime;

    private Integer subCom;

    private Integer subStreet;

    private Integer subArea;

    private Integer subProvice;

    private Integer picid;

    private Integer sensorid;

    private String parse1;

    private String parse2;

    private String parse3;

    public PeopleOutInfo(Integer id, Date outTime, Date backTime, Integer subCom, Integer subStreet, Integer subArea, Integer subProvice, Integer picid, Integer sensorid, String parse1, String parse2, String parse3) {
        this.id = id;
        this.outTime = outTime;
        this.backTime = backTime;
        this.subCom = subCom;
        this.subStreet = subStreet;
        this.subArea = subArea;
        this.subProvice = subProvice;
        this.picid = picid;
        this.sensorid = sensorid;
        this.parse1 = parse1;
        this.parse2 = parse2;
        this.parse3 = parse3;
    }

    public PeopleOutInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public Integer getSubCom() {
        return subCom;
    }

    public void setSubCom(Integer subCom) {
        this.subCom = subCom;
    }

    public Integer getSubStreet() {
        return subStreet;
    }

    public void setSubStreet(Integer subStreet) {
        this.subStreet = subStreet;
    }

    public Integer getSubArea() {
        return subArea;
    }

    public void setSubArea(Integer subArea) {
        this.subArea = subArea;
    }

    public Integer getSubProvice() {
        return subProvice;
    }

    public void setSubProvice(Integer subProvice) {
        this.subProvice = subProvice;
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

    public String getParse1() {
        return parse1;
    }

    public void setParse1(String parse1) {
        this.parse1 = parse1 == null ? null : parse1.trim();
    }

    public String getParse2() {
        return parse2;
    }

    public void setParse2(String parse2) {
        this.parse2 = parse2 == null ? null : parse2.trim();
    }

    public String getParse3() {
        return parse3;
    }

    public void setParse3(String parse3) {
        this.parse3 = parse3 == null ? null : parse3.trim();
    }
}