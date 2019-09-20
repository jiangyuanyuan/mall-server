package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

/**
 * Created by jiangyuanyuan on 23/11/17.
 */
public interface IAlarmMsgService {
    ServerResponse getList(int sortType,int pageNum, int pageSize);
    ServerResponse getListByHour(int timeType,int sortType);
    ServerResponse getListByTime(int timeType,int sortType);
    ServerResponse getListByMonth(int timeType,int sortType);
    ServerResponse getListByYear(int timeType,int sortType);
}
