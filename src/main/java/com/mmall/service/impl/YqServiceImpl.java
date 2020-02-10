package com.mmall.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.PeopleOutInfoMapper;
import com.mmall.dao.UserInfoMapper;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.*;
import com.mmall.service.IYqService;
import com.mmall.vo.SingleStatisticsVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("iYqService")
public class YqServiceImpl implements IYqService {
    @Autowired
    UserInfoMapper userMapper;



    @Autowired
    PeopleOutInfoMapper peopleOutInfoMapper;


    /*
         登录
      */
    @Override
    public ServerResponse<UserDto> login(String acct, String password) {
        System.out.println(acct+"   password:::::"+password);
        int resultCount = userMapper.checkUserName(acct);
        System.out.println("resultCount:::::"+resultCount);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        UserDto user = userMapper.selectLogin(acct,password);
        if (user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPasswd(StringUtils.EMPTY);
        return ServerResponse.createBySuccessMessageAndData("登录成功",user);
    }


    //统计
    @Override
    public ServerResponse getListByTime(int timeNumber, int sortType) {
        List<SingleStatisticsVo> alarmMsgList = peopleOutInfoMapper.getListByTime(timeNumber);
        return ServerResponse.createBySuccessMessageAndData("统计数据", alarmMsgList);
    }

    //历史数据

    @Override
    public ServerResponse getList(int sortType, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (sortType == 1) {
            PageHelper.orderBy("id asc");
        } else {
            PageHelper.orderBy("id desc");
        }

        List<PeopleOutInfo> peopleOutInfoList = peopleOutInfoMapper.selectList();
        PageInfo pageInfo = new PageInfo(peopleOutInfoList);
        return ServerResponse.createBySuccessMessageAndData("分页查询", pageInfo);
    }

    //查询数据

    @Override
    public ServerResponse search(int sortType, int pageNum, int pageSize, Date startTime,Date endTime) {
        PageHelper.startPage(pageNum, pageSize);
        if (sortType == 1) {
            PageHelper.orderBy("id asc");
        } else {
            PageHelper.orderBy("id desc");
        }

        List<PeopleOutInfo> peopleOutInfoList = peopleOutInfoMapper.search(startTime,endTime);
        PageInfo pageInfo = new PageInfo(peopleOutInfoList);
        return ServerResponse.createBySuccessMessageAndData("分页查询", pageInfo);
    }








}
