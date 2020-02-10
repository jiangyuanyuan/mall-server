package com.mmall.controller.portal;


import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.LoginDto;
import com.mmall.pojo.UserDto;
import com.mmall.pojo.UserInfo;
import com.mmall.service.IUserService;
import com.mmall.service.IYqService;
import com.mmall.util.MD5Util;
import com.mmall.vo.AlarmMsgDto;
import com.mmall.vo.SearchDto;
import com.mmall.vo.SingleStatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/yq/")
public class YqController {
    @Autowired
    private IYqService iYqService;

    /*
//       登录接口
    */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<UserInfo> login(@RequestBody LoginDto loginDto, HttpSession session) {
        ServerResponse<UserInfo> response = iYqService.login(loginDto.getUserName(), MD5Util.MD5EncodeUtf8(loginDto.getPassWd()));
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    //统计
    @RequestMapping(value = "getListByTime.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse statistics(@RequestBody SingleStatisticsDto singleStatisticsDto, HttpSession session) {
        //统计该用户7天内所属区域的记录条数   每一天的

        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }


        return iYqService.getListByTime(singleStatisticsDto.getTimeNumber(), singleStatisticsDto.getSortType());

    }

    ;

    @RequestMapping(value = "getList.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getList(@RequestBody AlarmMsgDto alarmMsgDto, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return iYqService.getList(alarmMsgDto.getSortType(), alarmMsgDto.getPageNum(), alarmMsgDto.getPageSize());
    }


    //查询
    @RequestMapping(value = "search.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse search(@RequestBody SearchDto alarmMsgDto, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return iYqService.search(alarmMsgDto.getSortType(), alarmMsgDto.getPageNum(), alarmMsgDto.getPageSize(), alarmMsgDto.getStartTime(), alarmMsgDto.getEndTime());
    }

    ;
}
