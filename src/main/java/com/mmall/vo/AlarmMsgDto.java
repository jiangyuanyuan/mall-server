package com.mmall.vo;

/**
 * Created by jiangyuanyuan on 15/09/2019.
 */
public class AlarmMsgDto {
    private Integer sortType;
    private Integer pageNum;
    private Integer pageSize;

    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
