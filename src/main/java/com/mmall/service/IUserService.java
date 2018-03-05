package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

/**
 * Created by jiangyuanyuan on 23/11/17.
 */
public interface IUserService {
    ServerResponse login(String username, String password);
    ServerResponse register(User user);
    ServerResponse checkValid(String string,String type);
    ServerResponse selectQuestion(String username);
    ServerResponse checkAnswer(String username,String question,String answer);
    ServerResponse forgetResetPassword(String username,String newPassword,String forgetToken);

    ServerResponse<String> resetPassword(String newPassword, String oldPassword, User user);

    ServerResponse<String> updateInformation(User user);

    ServerResponse<User> getInformation(Integer id);
    ServerResponse<String> checkAdminRole(User user);
}
