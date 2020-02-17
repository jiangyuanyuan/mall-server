package com.mmall.vo;

import java.util.Date;

public class PeopleOutInfoVo {

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


    private String picPathOut;

    private String picPathIn;

    private String picParse1;

    private String picParse2;


    private String localName;

    private String localId;

    private String localAttr;

    private String localPhone;




    public PeopleOutInfoVo(Integer id, Date outTime, Date backTime, Integer subCom, Integer subStreet, Integer subArea, Integer subProvice, Integer picid, Integer sensorid, String parse1, String parse2, String parse3,
                           String picPathOut,String picPathIn,String picParse1,String picParse2,
                           String localId,String localName,String localAttr,String localPhone
                           ) {
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

        this.picPathOut = picPathOut;
        this.picPathIn = picPathIn;
        this.picParse1 = picParse1;
        this.picParse2 = picParse2;


        this.localId = localId;
        this.localName = localName;
        this.localAttr = localAttr;
        this.localPhone = localPhone;
    }

    public PeopleOutInfoVo() {
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


    public String getPicPathOut() {
        return picPathOut;
    }

    public void setPicPathOut(String picPathOut) {
        this.picPathOut = picPathOut;
    }

    public String getPicPathIn() {
        return picPathIn;
    }

    public void setPicPathIn(String picPathIn) {
        this.picPathIn = picPathIn;
    }

    public String getPicParse1() {
        return picParse1;
    }

    public void setPicParse1(String picParse1) {
        this.picParse1 = picParse1;
    }

    public String getPicParse2() {
        return picParse2;
    }

    public void setPicParse2(String picParse2) {
        this.picParse2 = picParse2;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getLocalAttr() {
        return localAttr;
    }

    public void setLocalAttr(String localAttr) {
        this.localAttr = localAttr;
    }

    public String getLocalPhone() {
        return localPhone;
    }

    public void setLocalPhone(String localPhone) {
        this.localPhone = localPhone;
    }
}
