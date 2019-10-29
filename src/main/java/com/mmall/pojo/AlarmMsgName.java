package com.mmall.pojo;

public class AlarmMsgName {
    private Integer warntype;

    private String warnname;

    private String parse1;

    private String parse2;

    private String parse3;

    public AlarmMsgName(Integer warntype, String warnname, String parse1, String parse2, String parse3) {
        this.warntype = warntype;
        this.warnname = warnname;
        this.parse1 = parse1;
        this.parse2 = parse2;
        this.parse3 = parse3;
    }

    public AlarmMsgName() {
        super();
    }

    public Integer getWarntype() {
        return warntype;
    }

    public void setWarntype(Integer warntype) {
        this.warntype = warntype;
    }

    public String getWarnname() {
        return warnname;
    }

    public void setWarnname(String warnname) {
        this.warnname = warnname == null ? null : warnname.trim();
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