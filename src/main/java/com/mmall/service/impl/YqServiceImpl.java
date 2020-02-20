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
        System.out.println(acct + "   password:::::" + password);
        int resultCount = userMapper.checkUserName(acct);
        System.out.println("resultCount:::::" + resultCount);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        UserDto user = userMapper.selectLogin(acct, password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        String localId = user.getLocalId();
        String[] splitIds = localId.split("_");
        if (splitIds != null && splitIds.length > 0) {
            switch (splitIds.length) {
                case 1:
                    ProvInfo provInfo = provInfoMapper.selectByPrimaryKey(Integer.parseInt(splitIds[0]));
                    if (provInfo != null) {
                        user.setLocalName(provInfo.getProvname());
                        user.setType(1);
                        user.setLocalCurrentlyId(provInfo.getProvid());
                        user.setLocalUpId(-1);
                    }
                    break;
                case 2:
                    AreaInfo areaInfo = areaInfoMapper.selectByPrimaryKey(Integer.parseInt(splitIds[1]));
                    if (areaInfo != null) {
                        user.setLocalName(areaInfo.getAreaname());
                        user.setType(2);
                        user.setLocalCurrentlyId(areaInfo.getAreaid());
                        user.setLocalUpId(areaInfo.getBlProvid());
                    }
                    break;
                case 3:
                    StreetInfo streetInfo = streetInfoMapper.selectByPrimaryKey(Integer.parseInt(splitIds[2]));
                    if (streetInfo != null) {
                        user.setLocalName(streetInfo.getStreetname());
                        user.setType(3);
                        user.setLocalCurrentlyId(streetInfo.getStreetid());
                        user.setLocalUpId(streetInfo.getBlAreaid());
                    }
                    break;
                case 4:
                    ComInfo comInfo = comInfoMapper.selectByPrimaryKey(Integer.parseInt(splitIds[3]));
                    if (comInfo != null) {
                        user.setLocalName(comInfo.getComname());
                        user.setType(4);
                        user.setLocalCurrentlyId(comInfo.getComid());
                        user.setLocalUpId(comInfo.getBlStreetid());
                    }
                    break;
                case 5:
                    Sensorinfo sensorinfo = sensorinfoMapper.selectByPrimaryKey(Integer.parseInt(splitIds[4]));
                    if (sensorinfo != null) {
                        user.setLocalName(sensorinfo.getBlName());
                        user.setType(5);
                        user.setLocalCurrentlyId(sensorinfo.getSensorid());
                        user.setLocalUpId(sensorinfo.getBlComid());
                    }
                    break;
            }
        }


        user.setPasswd(StringUtils.EMPTY);
        UserVo userVo = new UserVo();
        userVo.setUserDto(user);
        return ServerResponse.createBySuccessMessageAndData("登录成功", userVo);
    }

    @Override
    public ServerResponse getBelowLocals(Integer id, Integer type) {
        List<BlocalVo> blocalVos = getBlocalVos(id, type);
        return ServerResponse.createBySuccessMessageAndData("下属区域", blocalVos);
    }


    @Override
    public ServerResponse getUserBelowLocals(String localId) {
        String[] ids = localId.split("_");
        List<BlocalVo> blocalVos = getBlocalVos(Integer.parseInt(ids[ids.length - 1]), ids.length);
        return ServerResponse.createBySuccessMessageAndData("下属区域", blocalVos);
    }

    private List<BlocalVo> getBlocalVos(Integer id, Integer type) {
        List<BlocalVo> blocalVos = new ArrayList<>();
        switch (type) {
//            case 1:
//                List<ProvInfo> provInfos = provInfoMapper.selectById(Integer.parseInt(locals[0]));
//                break;
            case 1:
                List<AreaInfo> areaInfos = areaInfoMapper.selectById(id);
                if (areaInfos != null && areaInfos.size() > 0) {
                    for (AreaInfo areaInfo : areaInfos) {
                        BlocalVo blocalVo = new BlocalVo();
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
                if (streetInfos != null && streetInfos.size() > 0) {
                    for (StreetInfo streetInfo : streetInfos) {
                        BlocalVo blocalVo = new BlocalVo();
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
                if (comInfos != null && comInfos.size() > 0) {
                    for (ComInfo comInfo : comInfos) {
                        BlocalVo blocalVo = new BlocalVo();
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
                if (sensorinfos != null && sensorinfos.size() > 0) {
                    for (Sensorinfo sensorinfo : sensorinfos) {
                        BlocalVo blocalVo = new BlocalVo();
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
    public ServerResponse getListByTime(int timeNumber, int sortType, String localId) {
        List<SingleStatisticsVo> alarmMsgList = peopleOutInfoMapper.getListByTime(timeNumber, Integer.parseInt(localId));
        return ServerResponse.createBySuccessMessageAndData("统计数据", alarmMsgList);
    }

    //历史数据

    @Override
    public ServerResponse getList(int sortType, int pageNum, int pageSize, String localId) {
        PageHelper.startPage(pageNum, pageSize);


        List<PeopleOutInfoVo> peopleOutInfoList = peopleOutInfoMapper.selectList(Integer.parseInt(localId));
        PageInfo pageInfo = new PageInfo(peopleOutInfoList);
        return ServerResponse.createBySuccessMessageAndData("分页查询", pageInfo);
    }

    //查询数据

    @Override
    public ServerResponse search(int sortType, int pageNum, int pageSize, String startTime, String endTime, String localId) {
        PageHelper.startPage(pageNum, pageSize);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        List<PeopleOutInfoVo> peopleOutInfoList = new ArrayList<>();
        try {
            Date startTimedate = simpleDateFormat.parse(startTime);
            Date endTimedate = simpleDateFormat.parse(endTime);
            List<PeopleOutInfoVo> peopleList = peopleOutInfoMapper.search(startTimedate, endTimedate, Integer.parseInt(localId));
            peopleOutInfoList.addAll(peopleList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo(peopleOutInfoList);
        return ServerResponse.createBySuccessMessageAndData("分页查询", pageInfo);
    }


    //统计
    @Override
    public ServerResponse getListByTimeV2(int timeNumber, int sortType, int id, int type) {
        List<SingleStatisticsVo> alarmMsgList = new ArrayList<>();

        switch (type) {
            case 1:
                List<SingleStatisticsVo> provList = peopleOutInfoMapper.getListByTimeProv(timeNumber, id);
                if (provList != null) {
                    alarmMsgList.addAll(provList);
                }
                break;
            case 2:
                List<SingleStatisticsVo> areaList = peopleOutInfoMapper.getListByTimeArea(timeNumber, id);
                if (areaList != null) {
                    alarmMsgList.addAll(areaList);
                }
                break;
            case 3:
                List<SingleStatisticsVo> streetList = peopleOutInfoMapper.getListByTimeStreet(timeNumber, id);
                if (streetList != null) {
                    alarmMsgList.addAll(streetList);
                }
                break;
            case 4:

                List<SingleStatisticsVo> comList = peopleOutInfoMapper.getListByTimeCom(timeNumber, id);
                if (comList != null) {
                    alarmMsgList.addAll(comList);
                }
                break;
            case 5:
                List<SingleStatisticsVo> sensorList = peopleOutInfoMapper.getListByTimeSensor(timeNumber, id);
                if (sensorList != null) {
                    alarmMsgList.addAll(sensorList);
                }
                break;
        }


        return ServerResponse.createBySuccessMessageAndData("统计数据", alarmMsgList);
    }

    //历史数据

    @Override
    public ServerResponse getListV2(int sortType, int pageNum, int pageSize, int id, int type) {
        PageHelper.startPage(pageNum, pageSize);


        List<PeopleOutInfoVo> selectListSensor = peopleOutInfoMapper.selectList(id);
//        if (selectListSensor != null) {
//            peopleOutInfoList.addAll(selectListSensor);
//        }

//        switch (type){
//            case 1:
//                List<PeopleOutInfoVo> selectListProv = peopleOutInfoMapper.selectListProv(id);
//                if (selectListProv!=null){
//                    peopleOutInfoList.addAll(selectListProv);
//                }
//                break;
//            case 2:
//                List<PeopleOutInfoVo> selectListArea = peopleOutInfoMapper.selectListArea(id);
//                if (selectListArea!=null){
//                    peopleOutInfoList.addAll(selectListArea);
//                }
//                break;
//            case 3:
//                List<PeopleOutInfoVo> selectListStreet = peopleOutInfoMapper.selectListStreet(id);
//                if (selectListStreet!=null){
//                    peopleOutInfoList.addAll(selectListStreet);
//                }
//                break;
//            case 4:
//                List<PeopleOutInfoVo> selectListCom = peopleOutInfoMapper.selectListCom(id);
//                if (selectListCom!=null){
//                    peopleOutInfoList.addAll(selectListCom);
//                }
//                break;
//            case 5:
//                List<PeopleOutInfoVo> selectListSensor = peopleOutInfoMapper.selectListSensor(id);
//                if (selectListSensor!=null){
//                    peopleOutInfoList.addAll(selectListSensor);
//                }
//                break;
//        }

        PageInfo pageInfo = new PageInfo(selectListSensor);
        return ServerResponse.createBySuccessMessageAndData("分页查询", pageInfo);
    }

    //查询数据

    @Override
    public ServerResponse searchV2(int sortType, int pageNum, int pageSize, String startTime, String endTime, int id, int type) {
        PageHelper.startPage(pageNum, pageSize);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
        List<PeopleOutInfoVo> peopleOutInfoList = null;
        try {
            Date startTimedate = simpleDateFormat.parse(startTime);
            Date endTimedate = simpleDateFormat.parse(endTime);
            peopleOutInfoList = peopleOutInfoMapper.search(startTimedate, endTimedate,id);
//            if (selectListProv != null) {
////                peopleOutInfoList.addAll(selectListProv);
//            }
//            switch (type) {
//                case 1:
//                    List<PeopleOutInfoVo> selectListProv = peopleOutInfoMapper.searchProv(startTimedate, endTimedate,id);
//                    if (selectListProv != null) {
//                        peopleOutInfoList.addAll(selectListProv);
//                    }
//                    break;
//                case 2:
//                    List<PeopleOutInfoVo> selectListArea = peopleOutInfoMapper.searchArea(startTimedate, endTimedate, id);
//                    if (selectListArea != null) {
//                        peopleOutInfoList.addAll(selectListArea);
//                    }
//                    break;
//                case 3:
//                    List<PeopleOutInfoVo> selectListStreet = peopleOutInfoMapper.searchStreet(startTimedate, endTimedate, id);
//                    if (selectListStreet != null) {
//                        peopleOutInfoList.addAll(selectListStreet);
//                    }
//                    break;
//                case 4:
//                    List<PeopleOutInfoVo> selectListCom = peopleOutInfoMapper.searchCom(startTimedate, endTimedate, id);
//                    if (selectListCom != null) {
//                        peopleOutInfoList.addAll(selectListCom);
//                    }
//                    break;
//                case 5:
//                    List<PeopleOutInfoVo> selectListSensor = peopleOutInfoMapper.searchSensor(startTimedate, endTimedate, id);
//                    if (selectListSensor != null) {
//                        peopleOutInfoList.addAll(selectListSensor);
//                    }
//                    break;
//            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (peopleOutInfoList==null){
            peopleOutInfoList =new ArrayList<>();
        }
        PageInfo pageInfo = new PageInfo(peopleOutInfoList);
        return ServerResponse.createBySuccessMessageAndData("分页查询", pageInfo);
    }


}
