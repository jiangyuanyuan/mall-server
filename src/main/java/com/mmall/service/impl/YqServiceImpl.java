package com.mmall.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.PeopleOutInfoMapper;
import com.mmall.dao.UserInfoMapper;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.*;
import com.mmall.service.IYqService;
import com.mmall.vo.PeopleOutInfoVo;
import com.mmall.vo.SingleStatisticsVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public ServerResponse<UserVo> login(String acct, String password) {
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
        UserVo userVo =new UserVo();
        userVo.setUserDto(user);
        return ServerResponse.createBySuccessMessageAndData("登录成功",userVo);
    }


    //统计
    @Override
    public ServerResponse getListByTime(int timeNumber, int sortType,String localId) {
        List<SingleStatisticsVo> alarmMsgList = peopleOutInfoMapper.getListByTime(timeNumber,Integer.parseInt(localId));
        return ServerResponse.createBySuccessMessageAndData("统计数据", alarmMsgList);
    }

    //历史数据

    @Override
    public ServerResponse getList(int sortType, int pageNum, int pageSize,String localId) {
        PageHelper.startPage(pageNum, pageSize);
        if (sortType == 1) {
            PageHelper.orderBy("id asc");
        } else {
            PageHelper.orderBy("id desc");
        }

        List<PeopleOutInfoVo> peopleOutInfoList = peopleOutInfoMapper.selectList(Integer.parseInt(localId));
        PageInfo pageInfo = new PageInfo(peopleOutInfoList);
        return ServerResponse.createBySuccessMessageAndData("分页查询", pageInfo);
    }

    //查询数据

    @Override
    public ServerResponse search(int sortType, int pageNum, int pageSize, String startTime,String endTime,String localId) {
        PageHelper.startPage(pageNum, pageSize);
        if (sortType == 1) {
            PageHelper.orderBy("id asc");
        } else {
            PageHelper.orderBy("id desc");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        List<PeopleOutInfoVo> peopleOutInfoList =  new ArrayList<>();
        try {
            Date startTimedate = simpleDateFormat.parse(startTime);
            Date endTimedate = simpleDateFormat.parse(endTime);
            List<PeopleOutInfoVo> peopleList = peopleOutInfoMapper.search(startTimedate,endTimedate,Integer.parseInt(localId));
            peopleOutInfoList.addAll(peopleList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo(peopleOutInfoList);
        return ServerResponse.createBySuccessMessageAndData("分页查询", pageInfo);
    }








}
