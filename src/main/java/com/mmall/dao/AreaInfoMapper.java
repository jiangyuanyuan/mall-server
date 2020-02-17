package com.mmall.dao;

import com.mmall.pojo.AreaInfo;

import java.util.List;

public interface AreaInfoMapper {
    int deleteByPrimaryKey(Integer areaid);

    int insert(AreaInfo record);

    int insertSelective(AreaInfo record);

    AreaInfo selectByPrimaryKey(Integer areaid);

    int updateByPrimaryKeySelective(AreaInfo record);

    int updateByPrimaryKey(AreaInfo record);

    List<AreaInfo> selectById(Integer bl_provid);

}