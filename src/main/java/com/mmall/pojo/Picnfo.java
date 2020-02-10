package com.mmall.pojo;

public class Picnfo extends PicnfoKey {
    private String picPathOut;

    private String picPathIn;

    private String parse1;

    private String parse2;

    public Picnfo(Integer picid, Integer sensorid, String picPathOut, String picPathIn, String parse1, String parse2) {
        super(picid, sensorid);
        this.picPathOut = picPathOut;
        this.picPathIn = picPathIn;
        this.parse1 = parse1;
        this.parse2 = parse2;
    }

    public Picnfo() {
        super();
    }

    public String getPicPathOut() {
        return picPathOut;
    }

    public void setPicPathOut(String picPathOut) {
        this.picPathOut = picPathOut == null ? null : picPathOut.trim();
    }

    public String getPicPathIn() {
        return picPathIn;
    }

    public void setPicPathIn(String picPathIn) {
        this.picPathIn = picPathIn == null ? null : picPathIn.trim();
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
}