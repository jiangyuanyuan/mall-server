package com.mmall.dao;

import com.mmall.pojo.AlarmMsg;
import com.mmall.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlarmMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlarmMsg record);

    int insertSelective(AlarmMsg record);

    AlarmMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlarmMsg record);

    int updateByPrimaryKey(AlarmMsg record);


    List<AlarmMsg> selectList();
    List<AlarmMsg> selectByTime(@Param("timeNumber") Integer timeNumber,@Param("sortType") Integer sortType);
    List<AlarmMsg> getListByMonth(@Param("timeNumber") Integer timeNumber,@Param("sortType") Integer sortType);
    List<AlarmMsg> getListByYear(@Param("timeNumber") Integer timeNumber,@Param("sortType") Integer sortType);
}