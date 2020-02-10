package com.mmall.service;

import com.mmall.common.ServerResponse;

import java.util.Date;

public interface IYqService {
    ServerResponse login(String username, String password);

    ServerResponse getListByTime(int timeType,int sortType);

    ServerResponse getList(int sortType,int pageNum, int pageSize);

    ServerResponse search(int sortType, int pageNum, int pageSize, Date startTime,Date endTime);

}
