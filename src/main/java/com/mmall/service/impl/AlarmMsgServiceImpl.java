package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.AlarmMsgMapper;
import com.mmall.pojo.AlarmMsg;
import com.mmall.service.IAlarmMsgService;
import com.mmall.util.DateTimeUtil;
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
    public ServerResponse getListByTime(int timeNumber, int sortType) {

        List<AlarmMsg> alarmMsgList = alarmMsgMapper.selectByTime(timeNumber, sortType);
        HashMap<String, Integer> hashMap = new HashMap<>();
        List singleStatisticsList = new ArrayList();
        if (alarmMsgList.size() > 0) {
            for (AlarmMsg alarmMsg : alarmMsgList) {
                String timeKey = DateTimeUtil.convert(alarmMsg.getAlarmTime(), DateTimeUtil.DATE_SHORT_FORMAT);
                Integer count = hashMap.get(timeKey);
                if (count == null || count == 0) {
                    hashMap.put(timeKey, 1);
                } else {
                    count = count + 1;
                    hashMap.put(timeKey, count);
                }
            }
            if (hashMap.size() > 0) {
                for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                    SingleStatisticsVo singleStatisticsVo = new SingleStatisticsVo();
                    singleStatisticsVo.setDay(entry.getKey());
                    singleStatisticsVo.setCount(entry.getValue());
                    singleStatisticsList.add(singleStatisticsVo);
                }

                Collections.sort(singleStatisticsList, (Comparator<SingleStatisticsVo>) (SingleStatisticsVo u1, SingleStatisticsVo u2) -> {
                    BigDecimal diff = new BigDecimal(u1.getDay()).subtract(new BigDecimal(u2.getDay()));
                    return diff.compareTo(new BigDecimal(0)); //相等为0
                });

            }


        }

        return ServerResponse.createBySuccessMessageAndData("统计数据", singleStatisticsList);
    }


    @Override
    public ServerResponse getListByMonth(int timeNumber, int sortType) {

        List<AlarmMsg> alarmMsgList = alarmMsgMapper.getListByMonth(timeNumber, sortType);
        HashMap<String, Integer> hashMap = new HashMap<>();
        List singleStatisticsList = new ArrayList();
        if (alarmMsgList.size() > 0) {
            for (AlarmMsg alarmMsg : alarmMsgList) {
                String timeKey = DateTimeUtil.convert(alarmMsg.getAlarmTime(), DateTimeUtil.DATE_SHORT_FORMAT_MONTH);
                Integer count = hashMap.get(timeKey);
                if (count == null || count == 0) {
                    hashMap.put(timeKey, 1);
                } else {
                    count = count + 1;
                    hashMap.put(timeKey, count);
                }
            }
            if (hashMap.size() > 0) {
                for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                    SingleStatisticsVo singleStatisticsVo = new SingleStatisticsVo();
                    singleStatisticsVo.setDay(entry.getKey());
                    singleStatisticsVo.setCount(entry.getValue());
                    singleStatisticsList.add(singleStatisticsVo);
                }

                Collections.sort(singleStatisticsList, (Comparator<SingleStatisticsVo>) (SingleStatisticsVo u1, SingleStatisticsVo u2) -> {
                    BigDecimal diff = new BigDecimal(u1.getDay()).subtract(new BigDecimal(u2.getDay()));
                    return diff.compareTo(new BigDecimal(0)); //相等为0
                });

            }


        }

        return ServerResponse.createBySuccessMessageAndData("统计数据", singleStatisticsList);
    }



    @Override
    public ServerResponse getListByYear(int timeNumber, int sortType) {

        List<AlarmMsg> alarmMsgList = alarmMsgMapper.getListByYear(timeNumber, sortType);
        HashMap<String, Integer> hashMap = new HashMap<>();
        List singleStatisticsList = new ArrayList();
        if (alarmMsgList.size() > 0) {
            for (AlarmMsg alarmMsg : alarmMsgList) {
                String timeKey = DateTimeUtil.convert(alarmMsg.getAlarmTime(), DateTimeUtil.DATE_SHORT_FORMAT_YEAR);
                Integer count = hashMap.get(timeKey);
                if (count == null || count == 0) {
                    hashMap.put(timeKey, 1);
                } else {
                    count = count + 1;
                    hashMap.put(timeKey, count);
                }
            }
            if (hashMap.size() > 0) {
                for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                    SingleStatisticsVo singleStatisticsVo = new SingleStatisticsVo();
                    singleStatisticsVo.setDay(entry.getKey());
                    singleStatisticsVo.setCount(entry.getValue());
                    singleStatisticsList.add(singleStatisticsVo);
                }

                Collections.sort(singleStatisticsList, (Comparator<SingleStatisticsVo>) (SingleStatisticsVo u1, SingleStatisticsVo u2) -> {
                    BigDecimal diff = new BigDecimal(u1.getDay()).subtract(new BigDecimal(u2.getDay()));
                    return diff.compareTo(new BigDecimal(0)); //相等为0
                });

            }


        }

        return ServerResponse.createBySuccessMessageAndData("统计数据", singleStatisticsList);
    }


//    @Override
//    public ServerResponse getListByYear(int timeNumber, int sortType) {
//
//        List<SingleStatisticsVo> alarmMsgList = alarmMsgMapper.getListByYear(timeNumber, sortType);
//
//
//
//        return ServerResponse.createBySuccessMessageAndData("统计数据", alarmMsgList);
//    }


}
