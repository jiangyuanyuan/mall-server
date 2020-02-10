package com.mmall.dao;

import com.mmall.pojo.LocalInfo;

public interface LocalInfoMapper {
    int deleteByPrimaryKey(Integer localId);

    int insert(LocalInfo record);

    int insertSelective(LocalInfo record);

    LocalInfo selectByPrimaryKey(Integer localId);

    int updateByPrimaryKeySelective(LocalInfo record);

    int updateByPrimaryKey(LocalInfo record);
}