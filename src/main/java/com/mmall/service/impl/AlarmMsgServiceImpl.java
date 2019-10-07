package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.AlarmMsgMapper;
import com.mmall.pojo.AlarmMsg;
import com.mmall.service.IAlarmMsgService;
import com.mmall.util.DateTimeUtil;
import com.mmall.vo.CaveatVo;
import com.mmall.vo.SingleStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by jiangyuanyuan on 23/11/17.
 */
@Service("iAlarmMsgService")
public class AlarmMsgServiceImpl implements IAlarmMsgService {
    @Autowired
    AlarmMsgMapper alarmMsgMapper;



    @Override
    public ServerResponse getNewest() {
        List<AlarmMsg> NewestList = alarmMsgMapper.getNewest();
        return ServerResponse.createBySuccessMessageAndData("最新ID", NewestList);
    }


    @Override
    public ServerResponse getTodayList(int sortType, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("picture_type asc");
        List<AlarmMsg> alarmMsgList = alarmMsgMapper.getTodayList();
        PageInfo pageInfo = new PageInfo(alarmMsgList);
        return ServerResponse.createBySuccessMessageAndData("分页查询", pageInfo);
    }


    @Override
    public ServerResponse getList(int sortType, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (sortType == 1) {
            PageHelper.orderBy("id asc");
        } else {
            PageHelper.orderBy("id desc");
        }

        List<AlarmMsg> alarmMsgList = alarmMsgMapper.selectList();
        PageInfo pageInfo = new PageInfo(alarmMsgList);
        return ServerResponse.createBySuccessMessageAndData("分页查询", pageInfo);
    }


    @Override
    public ServerResponse getListByHour(int timeNumber, int sortType) {
        List<SingleStatisticsVo> alarmMsgList = alarmMsgMapper.getListByHour(timeNumber, sortType);
        return ServerResponse.createBySuccessMessageAndData("统计数据", alarmMsgList);
    }

    @Override
    public ServerResponse getListByTime(int timeNumber, int sortType) {
        List<SingleStatisticsVo> alarmMsgList = alarmMsgMapper.getListByTime(timeNumber, sortType);
        return ServerResponse.createBySuccessMessageAndData("统计数据", alarmMsgList);
    }

    @Override
    public ServerResponse getListByMonth(int timeNumber, int sortType) {
        List<SingleStatisticsVo> alarmMsgList = alarmMsgMapper.getListByMonth(timeNumber, sortType);
        return ServerResponse.createBySuccessMessageAndData("统计数据", alarmMsgList);
    }

    @Override
    public ServerResponse getListByYear(int timeNumber, int sortType) {
        List<SingleStatisticsVo> alarmMsgList = alarmMsgMapper.getListByYear(timeNumber, sortType);
        return ServerResponse.createBySuccessMessageAndData("统计数据", alarmMsgList);
    }

    @Override
    public ServerResponse getCaveat(Integer timeNumber, Integer type) {
        List<CaveatVo> caveatList ;
        if (type==1){
           caveatList = alarmMsgMapper.getCaveatByMonth(timeNumber);
        }else if (type ==2){
           caveatList = alarmMsgMapper.getCaveatByYear(timeNumber);
        }else {
           caveatList = alarmMsgMapper.getCaveatByDay(timeNumber);
        }

        return ServerResponse.createBySuccessMessageAndData("统计警报", caveatList);
    }


}
