package com.mmall.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.*;
import com.mmall.pojo.*;
import com.mmall.service.IYqService;
import com.mmall.vo.BlocalVo;
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

    @Autowired
    ProvInfoMapper provInfoMapper;//市

    @Autowired
    AreaInfoMapper areaInfoMapper;//区

    @Autowired
    StreetInfoMapper streetInfoMapper;//街道

    @Autowired
    ComInfoMapper comInfoMapper;//社区

    @Autowired
    SensorinfoMapper sensorinfoMapper;//具体楼道


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

    @Override
    public ServerResponse getBelowLocals(Integer id,Integer type) {
        List<BlocalVo> blocalVos = getBlocalVos(id, type);
        return ServerResponse.createBySuccessMessageAndData("下属区域",blocalVos);
    }


    @Override
    public ServerResponse getUserBelowLocals(String localId) {
        String[] ids = localId.split("_");
        List<BlocalVo> blocalVos = getBlocalVos(Integer.parseInt(ids[ids.length-1]),ids.length);
        return ServerResponse.createBySuccessMessageAndData("下属区域",blocalVos);
    }

    private List<BlocalVo> getBlocalVos(Integer id, Integer type) {
        List<BlocalVo> blocalVos = new ArrayList<>();
        switch (type){
//            case 1:
//                List<ProvInfo> provInfos = provInfoMapper.selectById(Integer.parseInt(locals[0]));
//                break;
            case 1:
                List<AreaInfo> areaInfos = areaInfoMapper.selectById(id);
                if (areaInfos!=null&&areaInfos.size()>0){
                    for (AreaInfo areaInfo : areaInfos) {
                        BlocalVo blocalVo =  new BlocalVo();
                        blocalVo.setId(areaInfo.getAreaid());
                        blocalVo.setName(areaInfo.getAreaname());
                        blocalVo.setBlId(areaInfo.getBlProvid());
                        blocalVo.setParse(areaInfo.getParse());
                        blocalVo.setType(2);
                        blocalVos.add(blocalVo);
                    }
                }
                break;
            case 2:
                List<StreetInfo> streetInfos = streetInfoMapper.selectById(id);
                if (streetInfos!=null&&streetInfos.size()>0){
                    for (StreetInfo streetInfo : streetInfos) {
                        BlocalVo blocalVo =  new BlocalVo();
                        blocalVo.setId(streetInfo.getStreetid());
                        blocalVo.setName(streetInfo.getStreetname());
                        blocalVo.setBlId(streetInfo.getBlAreaid());
                        blocalVo.setParse(streetInfo.getParse());
                        blocalVo.setType(3);
                        blocalVos.add(blocalVo);
                    }
                }

                break;
            case 3:
                List<ComInfo> comInfos = comInfoMapper.selectById(id);
                if (comInfos!=null&&comInfos.size()>0){
                    for (ComInfo comInfo : comInfos) {
                        BlocalVo blocalVo =  new BlocalVo();
                        blocalVo.setId(comInfo.getComid());
                        blocalVo.setName(comInfo.getComname());
                        blocalVo.setBlId(comInfo.getBlStreetid());
                        blocalVo.setParse(comInfo.getParse());
                        blocalVo.setType(4);
                        blocalVos.add(blocalVo);
                    }
                }

                break;
            case 4:
                List<Sensorinfo> sensorinfos = sensorinfoMapper.selectById(id);
                if (sensorinfos!=null&&sensorinfos.size()>0){
                    for (Sensorinfo sensorinfo : sensorinfos) {
                        BlocalVo blocalVo =  new BlocalVo();
                        blocalVo.setId(sensorinfo.getSensorid());
                        blocalVo.setName(sensorinfo.getBlName());
                        blocalVo.setBlId(sensorinfo.getBlComid());
                        blocalVo.setParse(sensorinfo.getParse());
                        blocalVo.setType(5);
                        blocalVos.add(blocalVo);
                    }
                }
                break;
            default:
                break;
        }
        return blocalVos;
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
