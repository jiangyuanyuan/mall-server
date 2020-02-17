package com.mmall.dao;

import com.mmall.pojo.ComInfo;

import java.util.List;

public interface ComInfoMapper {
    int deleteByPrimaryKey(Integer comid);

    int insert(ComInfo record);

    int insertSelective(ComInfo record);

    ComInfo selectByPrimaryKey(Integer comid);

    List<ComInfo> selectById(Integer bl_streetid);

    int updateByPrimaryKeySelective(ComInfo record);

    int updateByPrimaryKey(ComInfo record);
}