package com.mmall.dao;

import com.mmall.pojo.StreetInfo;

import java.util.List;

public interface StreetInfoMapper {
    int deleteByPrimaryKey(Integer streetid);

    int insert(StreetInfo record);

    int insertSelective(StreetInfo record);

    StreetInfo selectByPrimaryKey(Integer streetid);

    List<StreetInfo> selectById(Integer bl_areaid);

    int updateByPrimaryKeySelective(StreetInfo record);

    int updateByPrimaryKey(StreetInfo record);
}