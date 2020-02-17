package com.mmall.dao;

import com.mmall.pojo.ProvInfo;

import java.util.List;

public interface ProvInfoMapper {
    int deleteByPrimaryKey(Integer provid);

    int insert(ProvInfo record);

    int insertSelective(ProvInfo record);

    ProvInfo selectByPrimaryKey(Integer provid);

    int updateByPrimaryKeySelective(ProvInfo record);

    int updateByPrimaryKey(ProvInfo record);

//    AreaInfo selectById(Integer areaid);
//    List<ProvInfo> selectById(Integer provid);
}