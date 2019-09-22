//package com.mmall.controller.backend;
//
//import com.mmall.common.Const;
//import com.mmall.common.ServerResponse;
//import com.mmall.pojo.User;
//import com.mmall.service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpSession;
//
///**
// * Created by jiangyuanyuan on 4/12/17.
// */
//@Controller
//@RequestMapping("/manage/user")
//public class UserManagerController {
//    @Autowired
//    private IUserService iUserService;
//
//    @RequestMapping(value = "login.do",method = RequestMethod.POST)
//    @ResponseBody
//    public ServerResponse<User> login(String username, String password, HttpSession httpSession){
//        ServerResponse<User> serverResponse = iUserService.login(username,password);
//        if (serverResponse.isSuccess()){
//            User user = serverResponse.getData();
//            if (user.getRole() == Const.Role.ADMINISTRATOR){
//                //登录的是管理员
//                httpSession.setAttribute(Const.CURRENT_USER,user);
//                return serverResponse;
//            }else {
//                return ServerResponse.createByErrorMessage("不是管理员，无权限操作");
//            }
//        }
//        return serverResponse;
//    }
//
//}
