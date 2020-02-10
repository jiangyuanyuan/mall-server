package com.mmall.pojo;

public class LocalInfo {
    private Integer localId;

    private String localName;

    private String attr;

    private String phone;

    private String parse1;

    private String parse2;

    private String parse3;

    public LocalInfo(Integer localId, String localName, String attr, String phone, String parse1, String parse2, String parse3) {
        this.localId = localId;
        this.localName = localName;
        this.attr = attr;
        this.phone = phone;
        this.parse1 = parse1;
        this.parse2 = parse2;
        this.parse3 = parse3;
    }

    public LocalInfo() {
        super();
    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName == null ? null : localName.trim();
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr == null ? null : attr.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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