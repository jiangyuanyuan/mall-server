package com.mmall.service.impl;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.common.TokenCache;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by jiangyuanyuan on 23/11/17.
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService{
    @Autowired
    UserMapper userMapper;

    /*
        登录
     */
    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUserName(username);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        User user = userMapper.selectLogin(username,password);
        if (user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccessMessageAndData("登录成功",user);
    }
    /*
        注册
     */
    @Override
    public ServerResponse register(User user) {

        int resultCount = userMapper.checkUserName(user.getUsername());
        if (resultCount>0){
            return ServerResponse.createByErrorMessage("用户已经存在");
        }
        resultCount = userMapper.checkEmail(user.getEmail());
        if (resultCount>0){
            return ServerResponse.createByErrorMessage("email已经存在");
        }

        user.setRole(Const.Role.GENERAL_USER);
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        resultCount = userMapper.insert(user);
        if (resultCount == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }

        return ServerResponse.createBySuccessMessage("注册成功");
    }
    /*
        校验
     */
    @Override
    public ServerResponse checkValid(String string, String type) {
        if (string!=null&&!StringUtils.isEmpty(string)&&type!=null&&!StringUtils.isEmpty(type)){
            int resultCount = 0;
            if (type.equals(Const.CHECK_USERNAME)){
                resultCount = userMapper.checkUserName(string);
                if(resultCount>0){
                    return ServerResponse.createByErrorMessage("用户已经存在");
                }
            }
            if (type.equals(Const.CHECK_EMAIL)){
                resultCount = userMapper.checkEmail(string);
                if (resultCount>0){
                    return ServerResponse.createByErrorMessage("email已经存在");
                }
            }
        }else {
            return ServerResponse.createByErrorMessage("参数不能为空");
        }

        return ServerResponse.createBySuccessMessage("校验成功");
    }
    /*
        查询问题
     */
    @Override
    public ServerResponse selectQuestion(String username) {
        ServerResponse serverResponse = checkValid(username,Const.CHECK_USERNAME);
        if (serverResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String question = userMapper.selectQuestion(username);
        if (StringUtils.isEmpty(question)){
            return ServerResponse.createBySuccessMessageAndData("查询问题成功",question);
        }
        return ServerResponse.createByErrorMessage("找寻密码的问题是空的");
    }
    /*
        校验找回密码问题答案
     */
    @Override
    public ServerResponse checkAnswer(String username, String question, String answer) {
        int resultCount = userMapper.checkAnswer(username, question, answer);
        if (resultCount>0){
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return ServerResponse.createBySuccessMessageAndData("token",forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题答案错误");
    }
    /*
        token重置密码
     */
    @Override
    public ServerResponse forgetResetPassword(String username, String newPassword, String forgetToken) {
        if (StringUtils.isEmpty(forgetToken)){
            return ServerResponse.createByErrorMessage("token参数为空");
        }
        ServerResponse serverResponse = checkValid(username,Const.CHECK_USERNAME);
        if (serverResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if (StringUtils.isBlank(token)){
            return ServerResponse.createByErrorMessage("token错误，请重新获取重置密码的token");
        }
        if (StringUtils.equals(forgetToken,token)){
            String md5Password = MD5Util.MD5EncodeUtf8(newPassword);
            int resultCount =  userMapper.updatePasswordByUsername(username,newPassword);
            if (resultCount>0){
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }

        }else {
            return ServerResponse.createByErrorMessage("token错误，请重新获取重置密码的token");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    /*
        登录状态修改密码
     */

    @Override
    public ServerResponse<String> resetPassword(String newPassword, String oldPassword, User user) {
        ServerResponse serverResponse = checkValid(user.getUsername(),Const.CHECK_USERNAME);
        if (serverResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(oldPassword),user.getId());
        if (resultCount==0){
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(newPassword));
        resultCount = userMapper.updateByPrimaryKeySelective(user);
        if (resultCount>0){
            return ServerResponse.createBySuccessMessage("修改密码成功");
        }
        return ServerResponse.createByErrorMessage("密码修改失败");
    }
    /*
        更新用户信息 username不能被修改 email不能重复
     */
    @Override
    public ServerResponse<String> updateInformation(User user) {
        int resultCount = userMapper.checkEmailById(user.getEmail(),user.getId());
        if (resultCount>0){
            return ServerResponse.createByErrorMessage("email已经存在，请重新更换email再尝试更新");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setAnswer(user.getAnswer());
        updateUser.setQuestion(user.getQuestion());
        resultCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (resultCount>0){
            return ServerResponse.createBySuccessMessage("用户信息更新成功");
        }
        return ServerResponse.createByErrorMessage("用户信息更新失败");
    }
    /*
        获取用户信息
     */
    @Override
    public ServerResponse<User> getInformation(Integer id) {
        User user = userMapper.getInformationById(id);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        return ServerResponse.createBySuccessMessageAndData("获取用户信息成功",user);
    }

    /*
       校验是不是管理员
     */
    @Override
    public ServerResponse<String> checkAdminRole(User user){
        if(user!=null&&user.getRole().equals(Const.Role.ADMINISTRATOR)){
            return ServerResponse.createBySuccessMessage("管理员用户");
        }
        return ServerResponse.createByErrorMessage("普通用户");
    }

}
