package com.mmall.service;

import com.mmall.common.ServerResponse;

import java.util.Date;

public interface IYqService {
    ServerResponse login(String username, String password);

    ServerResponse getBelowLocals(Integer localId,Integer type);

    ServerResponse getUserBelowLocals(String localId);

    ServerResponse getListByTime(int timeType,int sortType,String localId);

    ServerResponse getListByTimeV2(int timeType,int sortType,int id,int type);

    ServerResponse getList(int sortType,int pageNum, int pageSize,String localId);

    ServerResponse search(int sortType, int pageNum, int pageSize, String startTime,String endTime,String localId);


    ServerResponse getListV2(int sortType,int pageNum, int pageSize,int id,int type);

    ServerResponse searchV2(int sortType, int pageNum, int pageSize, String startTime,String endTime,int id,int type);


}
