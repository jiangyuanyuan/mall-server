package com.mmall.pojo;

import java.util.Date;

public class AlarmMsg {
    private Integer id;

    private Integer cellid;

    private Integer sensorid;

    private Date alarmTime;

    private Integer pictureType;

    private String alarmPictureName;

    private Integer alarmType;

    private Integer alarmReason;

    private String parse;

    public AlarmMsg(Integer id, Integer cellid, Integer sensorid, Date alarmTime, Integer pictureType, String alarmPictureName, Integer alarmType, Integer alarmReason, String parse) {
        this.id = id;
        this.cellid = cellid;
        this.sensorid = sensorid;
        this.alarmTime = alarmTime;
        this.pictureType = pictureType;
        this.alarmPictureName = alarmPictureName;
        this.alarmType = alarmType;
        this.alarmReason = alarmReason;
        this.parse = parse;
    }

    public AlarmMsg() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCellid() {
        return cellid;
    }

    public void setCellid(Integer cellid) {
        this.cellid = cellid;
    }

    public Integer getSensorid() {
        return sensorid;
    }

    public void setSensorid(Integer sensorid) {
        this.sensorid = sensorid;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Integer getPictureType() {
        return pictureType;
    }

    public void setPictureType(Integer pictureType) {
        this.pictureType = pictureType;
    }

    public String getAlarmPictureName() {
        return alarmPictureName;
    }

    public void setAlarmPictureName(String alarmPictureName) {
        this.alarmPictureName = alarmPictureName == null ? null : alarmPictureName.trim();
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public Integer getAlarmReason() {
        return alarmReason;
    }

    public void setAlarmReason(Integer alarmReason) {
        this.alarmReason = alarmReason;
    }

    public String getParse() {
        return parse;
    }

    public void setParse(String parse) {
        this.parse = parse == null ? null : parse.trim();
    }
}