package com.mmall.pojo;

public class UserInfo {
    private String acct;

    private String localId;

    private String state;

    private String parse1;

    private String parse2;

    private String passwd;

    public UserInfo(String acct, String localId, String state, String parse1, String parse2, String passwd) {
        this.acct = acct;
        this.localId = localId;
        this.state = state;
        this.parse1 = parse1;
        this.parse2 = parse2;
        this.passwd = passwd;
    }

    public UserInfo() {
        super();
    }

    public String getAcct() {
        return acct;
    }

    public void setAcct(String acct) {
        this.acct = acct == null ? null : acct.trim();
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId == null ? null : localId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }
}