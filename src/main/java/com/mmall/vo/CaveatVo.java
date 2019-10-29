package com.mmall.vo;

/**
 * Created by jiangyuanyuan on 07/10/2019.
 */
public class CaveatVo {


    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getCaveatType() {
        return caveatType;
    }

    public void setCaveatType(Integer caveatType) {
        this.caveatType = caveatType;
    }


    private Integer sum;
    private Integer caveatType;
    private String warnName;


    public String getWarnName() {
        return warnName;
    }

    public void setWarnName(String warnName) {
        this.warnName = warnName;
    }
}
