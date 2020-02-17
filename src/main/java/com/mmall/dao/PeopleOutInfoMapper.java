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


    List<PeopleOutInfoVo> selectList(@Param("localId") Integer localId);

    List<PeopleOutInfoVo> search(@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("localId") Integer localId);


}