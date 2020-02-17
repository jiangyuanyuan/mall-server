package com.mmall.service;

import com.mmall.common.ServerResponse;

import java.util.Date;

public interface IYqService {
    ServerResponse login(String username, String password);

    ServerResponse getListByTime(int timeType,int sortType,String localId);

    ServerResponse getList(int sortType,int pageNum, int pageSize,String localId);

    ServerResponse search(int sortType, int pageNum, int pageSize, String startTime,String endTime,String localId);

}
