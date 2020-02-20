package com.mmall.dao;

import com.mmall.pojo.PeopleOutInfo;
import com.mmall.vo.PeopleOutInfoVo;
import com.mmall.vo.SingleStatisticsVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PeopleOutInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PeopleOutInfo record);

    int insertSelective(PeopleOutInfo record);

    PeopleOutInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PeopleOutInfo record);

    int updateByPrimaryKey(PeopleOutInfo record);

    List<SingleStatisticsVo> getListByTime(@Param("timeNumber") Integer timeNumber,@Param("localId") Integer localId);


    List<SingleStatisticsVo> getListByTimeProv(@Param("timeNumber") Integer timeNumber,@Param("localId") Integer localId);
    List<SingleStatisticsVo> getListByTimeArea(@Param("timeNumber") Integer timeNumber,@Param("localId") Integer localId);
    List<SingleStatisticsVo> getListByTimeStreet(@Param("timeNumber") Integer timeNumber,@Param("localId") Integer localId);
    List<SingleStatisticsVo> getListByTimeCom(@Param("timeNumber") Integer timeNumber,@Param("localId") Integer localId);
    List<SingleStatisticsVo> getListByTimeSensor(@Param("timeNumber") Integer timeNumber,@Param("localId") Integer localId);




    List<PeopleOutInfoVo> selectList(@Param("localId") Integer localId);

    List<PeopleOutInfoVo> selectListProv(@Param("localId") Integer localId);
    List<PeopleOutInfoVo> selectListArea(@Param("localId") Integer localId);
    List<PeopleOutInfoVo> selectListStreet(@Param("localId") Integer localId);
    List<PeopleOutInfoVo> selectListCom(@Param("localId") Integer localId);
    List<PeopleOutInfoVo> selectListSensor(@Param("localId") Integer localId);

    List<PeopleOutInfoVo> search(@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("localId") Integer localId);

    List<PeopleOutInfoVo> searchProv(@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("localId") Integer localId);
    List<PeopleOutInfoVo> searchArea(@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("localId") Integer localId);
    List<PeopleOutInfoVo> searchStreet(@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("localId") Integer localId);
    List<PeopleOutInfoVo> searchCom(@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("localId") Integer localId);
    List<PeopleOutInfoVo> searchSensor(@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("localId") Integer localId);


}