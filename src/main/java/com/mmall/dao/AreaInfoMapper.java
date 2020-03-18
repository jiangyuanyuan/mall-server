package com.mmall.dao;

import com.mmall.pojo.AreaInfo;
import com.mmall.vo.BlocalVo;

import java.util.List;

public interface AreaInfoMapper {
    int deleteByPrimaryKey(Integer areaid);

    int insert(AreaInfo record);

    int insertSelective(AreaInfo record);

    AreaInfo selectByPrimaryKey(Integer areaid);

    int updateByPrimaryKeySelective(AreaInfo record);

    int updateByPrimaryKey(AreaInfo record);

    List<BlocalVo> selectById(Integer bl_provid);

}