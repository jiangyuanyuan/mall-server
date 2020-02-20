package com.mmall.vo;

/**
 * Created by jiangyuanyuan on 15/09/2019.
 */
public class SingleStatisticsDto {
    private Integer timeNumber;
    private Integer sortType;

    private Integer id;
    private Integer type;

    public Integer getTimeNumber() {
        return timeNumber;
    }

    public void setTimeNumber(Integer timeNumber) {
        this.timeNumber = timeNumber;
    }

    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
