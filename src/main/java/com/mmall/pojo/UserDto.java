package com.mmall.pojo;

public class UserDto {
    private String acct;

    private String passwd;

    private String localId;

    private String state;

    private String parse1;

    private String parse2;




    private String localName;

    private String localAttr;

    private String localPhone;

    private String localParse1;

    private String localParse2;

    private String localParse3;


    private Integer localCurrentlyId;

    private Integer localUpId;

    private Integer type;






    public UserDto(String acct, String passwd, String localId, String state, String parse1, String parse2,
                   String localName, String localAttr, String localPhone,
                   String localParse1, String localParse2, String localParse3) {
        this.acct = acct;
        this.passwd = passwd;
        this.localId = localId;
        this.state = state;
        this.parse1 = parse1;
        this.parse2 = parse2;

        this.localName = localName;
        this.localAttr = localAttr;
        this.localPhone = localPhone;
        this.localParse1 = localParse1;
        this.localParse2 = localParse2;
        this.localParse3 = localParse3;
    }

    public UserDto() {
        super();
    }

    public String getAcct() {
        return acct;
    }

    public void setAcct(String acct) {
        this.acct = acct == null ? null : acct.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
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


    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
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

    public String getLocalParse1() {
        return localParse1;
    }

    public void setLocalParse1(String localParse1) {
        this.localParse1 = localParse1;
    }

    public String getLocalParse2() {
        return localParse2;
    }

    public void setLocalParse2(String localParse2) {
        this.localParse2 = localParse2;
    }

    public String getLocalParse3() {
        return localParse3;
    }

    public void setLocalParse3(String localParse3) {
        this.localParse3 = localParse3;
    }

    public Integer getLocalCurrentlyId() {
        return localCurrentlyId;
    }

    public void setLocalCurrentlyId(Integer localCurrentlyId) {
        this.localCurrentlyId = localCurrentlyId;
    }

    public Integer getLocalUpId() {
        return localUpId;
    }

    public void setLocalUpId(Integer localUpId) {
        this.localUpId = localUpId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
