package com.mmall.dao;

import com.mmall.pojo.Picnfo;
import com.mmall.pojo.PicnfoKey;

public interface PicnfoMapper {
    int deleteByPrimaryKey(PicnfoKey key);

    int insert(Picnfo record);

    int insertSelective(Picnfo record);

    Picnfo selectByPrimaryKey(PicnfoKey key);

    int updateByPrimaryKeySelective(Picnfo record);

    int updateByPrimaryKey(Picnfo record);
}