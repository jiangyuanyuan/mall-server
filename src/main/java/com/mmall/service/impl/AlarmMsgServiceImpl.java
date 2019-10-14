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
//        if (sortType == 1) {
//            PageHelper.orderBy("picture_type asc");
//        } else {
//            PageHelper.orderBy("picture_type desc");
//        }
//        PageHelper.orderBy("picture_type asc");
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
        List<CaveatVo> caveatList;
        List<CaveatVo> caveatTempList = new ArrayList<>();
        if (type == 1) {
            caveatList = alarmMsgMapper.getCaveatByMonth(timeNumber);
        } else if (type == 2) {
            caveatList = alarmMsgMapper.getCaveatByYear(timeNumber);
        } else {
            caveatList = alarmMsgMapper.getCaveatByDay(timeNumber);
        }
        if (caveatList != null && caveatList.size() > 0) {

//            "1" -> "未在指定时间休息"
//            "2" -> "未在指定区域监督"
//            "4" -> "厕所区域异常"
//            "8" -> "窗户区域异常"
//            "16" -> "高度异常"
//            "32" -> "非休息时间休息"
//            "64" -> "进入三角区域"
//            "128" -> "内务不整"
//            "512" -> "单人留仓"
//            "1024" -> "吊拉窗户"
//            "2048" -> "搭人梯"
//            "4096" -> "站被子上做板报"
            Integer otherSum = 0;
            for (CaveatVo caveatVo : caveatList) {
                Integer caveatType = caveatVo.getCaveatType();
                switch (caveatType) {
                    case 1:
                    case 2:
                    case 4:
                    case 8:
                    case 16:
                    case 32:
                    case 64:
                    case 128:
                    case 512:
                    case 1024:
                    case 2048:
                    case 4096:
                        caveatTempList.add(caveatVo);
                        break;
                    default:
                        otherSum = otherSum+caveatVo.getSum();
                        break;
                }
            }
            CaveatVo otherCaveatVo = new CaveatVo();
            otherCaveatVo.setCaveatType(-1);
            otherCaveatVo.setSum(otherSum);
            caveatTempList.add(otherCaveatVo);
        }


        return ServerResponse.createBySuccessMessageAndData("统计警报", caveatTempList);
    }


}
