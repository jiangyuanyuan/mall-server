package com.mmall.controller.portal;


import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.common.TokenCache;
import com.mmall.pojo.LocalIdDto;
import com.mmall.pojo.LoginDto;
import com.mmall.pojo.UserVo;
import com.mmall.service.IYqService;
import com.mmall.util.MD5Util;
import com.mmall.util.PushUtil;
import com.mmall.vo.SearchDto;
import com.mmall.vo.SingleStatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping("/yqv2/")
public class YqV2Controller {
    @Autowired
    private IYqService iYqService;

    /*
//       登录接口
    */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<UserVo> login(@RequestBody LoginDto loginDto) {
        ServerResponse<UserVo> response = iYqService.login(loginDto.getUserName(), MD5Util.MD5EncodeUtf8(loginDto.getPassWd()));
        if (response.isSuccess()) {
//            session.setAttribute(Const.CURRENT_USER, response.getData());
            String token = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+token,response.getData().getUserDto().getLocalId()+"");
            response.getData().setToken(token);
        }
        return response;
    }


    @RequestMapping(value = "getBelowLocals.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getBelowLocals(@RequestBody LocalIdDto localIdDto,@RequestHeader("Authorization") String token) {
        String localId = TokenCache.getKey(TokenCache.TOKEN_PREFIX+token);
        if (localId == null) {
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        //判断用户权限

        ServerResponse response = iYqService.getBelowLocals(localIdDto.getId(),localIdDto.getType());

        return response;
    }

    @RequestMapping(value = "getUserBelowLocals.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<UserVo> getUserBelowLocals(@RequestHeader("Authorization") String token) {
        String localId = TokenCache.getKey(TokenCache.TOKEN_PREFIX+token);
        if (localId == null) {
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse<UserVo> response = iYqService.getUserBelowLocals(localId);

        return response;
    }



    //统计
    @RequestMapping(value = "getListByTime.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse statistics(@RequestBody SingleStatisticsDto singleStatisticsDto,@RequestHeader("Authorization") String token) {
        //统计该用户7天内所属区域的记录条数   每一天的
        String localId = TokenCache.getKey(TokenCache.TOKEN_PREFIX+token);
//        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENT_USER);
        if (localId == null) {
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }


        return iYqService.getListByTime(singleStatisticsDto.getTimeNumber(), singleStatisticsDto.getSortType(),localId);

    }

    ;

    @RequestMapping(value = "getList.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getList(@RequestBody SearchDto alarmMsgDto, @RequestHeader("Authorization") String token) {
        String localId = TokenCache.getKey(TokenCache.TOKEN_PREFIX+token);
//        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENT_USER);
        if (localId == null) {
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        if (alarmMsgDto.getStartTime()==null||alarmMsgDto.getStartTime()==""||alarmMsgDto.getEndTime()==null||alarmMsgDto.getEndTime()==""){
            return iYqService.getList(alarmMsgDto.getSortType(), alarmMsgDto.getPageNum(), alarmMsgDto.getPageSize(),localId);
        }else {
            return iYqService.search(alarmMsgDto.getSortType(), alarmMsgDto.getPageNum(), alarmMsgDto.getPageSize(), alarmMsgDto.getStartTime(), alarmMsgDto.getEndTime(),localId);
        }

    }

    //查询
    @RequestMapping(value = "push.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse push() {
//        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENT_USER);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
//        }
        HashMap map =  new HashMap();
        map.put("msg","有数据更新了");
        map.put("title","有数据更新");
        map.put("extra","额外的");
        PushUtil.jpushAndroid(map);

        return ServerResponse.createBySuccess();
    }


}
