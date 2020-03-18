package com.mmall.controller.portal;


import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.common.TokenCache;
import com.mmall.pojo.LocalIdDto;
import com.mmall.pojo.LoginDto;
import com.mmall.pojo.ResetDto;
import com.mmall.pojo.UserVo;
import com.mmall.service.IYqService;
import com.mmall.util.MD5Util;
import com.mmall.util.PushUtil;
import com.mmall.vo.SearchDto;
import com.mmall.vo.SingleStatisticsDto;
import org.apache.commons.lang.StringUtils;
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
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+token+"USERNAME",response.getData().getUserDto().getAcct()+"");
            response.getData().setToken(token);
        }
        return response;
    }



        /*
        登录状态下重置密码
     */
    @RequestMapping(value = "resetPassword.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(@RequestBody ResetDto resetDto, @RequestHeader("Authorization") String token){
        String localId = TokenCache.getKey(TokenCache.TOKEN_PREFIX+token);
        String acct = TokenCache.getKey(TokenCache.TOKEN_PREFIX+token+"USERNAME");
        if (StringUtils.isBlank(localId)||StringUtils.isBlank(acct)) {
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return  iYqService.resetPassword(resetDto.getNewPassword(),resetDto.getOldPassword(),acct,token);
    }


    @RequestMapping(value = "getBelowLocals.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getBelowLocals(@RequestBody LocalIdDto localIdDto,@RequestHeader("Authorization") String token) {
        String localId = TokenCache.getKey(TokenCache.TOKEN_PREFIX+token);
        if (StringUtils.isBlank(localId)) {
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
        if (StringUtils.isBlank(localId)) {
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
        if (StringUtils.isBlank(localId)) {
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }


        return iYqService.getListByTimeV2(singleStatisticsDto.getTimeNumber(), singleStatisticsDto.getSortType(),singleStatisticsDto.getId(),singleStatisticsDto.getType());

    }

    ;

    @RequestMapping(value = "getList.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getList(@RequestBody SearchDto alarmMsgDto, @RequestHeader("Authorization") String token) {
        String localId = TokenCache.getKey(TokenCache.TOKEN_PREFIX+token);
//        UserInfo user = (UserInfo) session.getAttribute(Const.CURRENT_USER);
        if (StringUtils.isBlank(localId)) {
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }


        if (alarmMsgDto.getStartTime()==null||alarmMsgDto.getStartTime()==""||alarmMsgDto.getEndTime()==null||alarmMsgDto.getEndTime()==""){
            return iYqService.getListV2(alarmMsgDto.getSortType(), alarmMsgDto.getPageNum(), alarmMsgDto.getPageSize(),alarmMsgDto.getId(),alarmMsgDto.getType());
        }else {
            return iYqService.searchV2(alarmMsgDto.getSortType(), alarmMsgDto.getPageNum(), alarmMsgDto.getPageSize(), alarmMsgDto.getStartTime(), alarmMsgDto.getEndTime(),alarmMsgDto.getId(),alarmMsgDto.getType());
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
        PushUtil.getInstance().sendToRegistrationId("1111111",
                "外出检测",
                "有一条外出消息",
                "有一条外出消息",
                "");

        return ServerResponse.createBySuccess();
    }


}
