package com.mmall.dao;

import com.mmall.pojo.User;
import com.mmall.pojo.UserDto;
import com.mmall.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String acct);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String acct);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int checkUserName(String acct);

    UserDto selectLogin(@Param("acct") String acct, @Param("passwd") String passwd);


}