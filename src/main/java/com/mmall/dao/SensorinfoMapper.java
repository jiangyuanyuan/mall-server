package com.mmall.dao;

import com.mmall.pojo.Sensorinfo;
import com.mmall.vo.BlocalVo;

import java.util.List;

public interface SensorinfoMapper {
    int deleteByPrimaryKey(Integer sensorid);

    int insert(Sensorinfo record);

    int insertSelective(Sensorinfo record);

    Sensorinfo selectByPrimaryKey(Integer sensorid);

    List<BlocalVo> selectById(Integer bl_comid);

    int updateByPrimaryKeySelective(Sensorinfo record);

    int updateByPrimaryKey(Sensorinfo record);
}