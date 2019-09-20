package com.mmall.controller.portal;

import com.mmall.common.ServerResponse;
import com.mmall.service.IAlarmMsgService;
import com.mmall.vo.AlarmMsgDto;
import com.mmall.vo.SingleStatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jiangyuanyuan on 22/11/17.
 */
@Controller
@RequestMapping("/alarmMsg/")
public class AlarmMsgController {
    @Autowired
    private IAlarmMsgService iAlarmMsgService;


    @RequestMapping(value = "getList.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getList(@RequestBody AlarmMsgDto alarmMsgDto) {
        return iAlarmMsgService.getList(alarmMsgDto.getSortType(), alarmMsgDto.getPageNum(), alarmMsgDto.getPageSize());
    }

    @RequestMapping(value = "getListByHour.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getListByHour(@RequestBody SingleStatisticsDto singleStatisticsDto) {
        return iAlarmMsgService.getListByHour(singleStatisticsDto.getTimeNumber(), singleStatisticsDto.getSortType());
    }

    @RequestMapping(value = "getListByTime.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getListByTime(@RequestBody SingleStatisticsDto singleStatisticsDto) {
        return iAlarmMsgService.getListByTime(singleStatisticsDto.getTimeNumber(), singleStatisticsDto.getSortType());
    }


    @RequestMapping(value = "getListByMonth.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getListByMonth(@RequestBody SingleStatisticsDto singleStatisticsDto) {
        return iAlarmMsgService.getListByMonth(singleStatisticsDto.getTimeNumber(), singleStatisticsDto.getSortType());
    }

    @RequestMapping(value = "getListByYear.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getListByYear(@RequestBody SingleStatisticsDto singleStatisticsDto) {
        return iAlarmMsgService.getListByYear(singleStatisticsDto.getTimeNumber(), singleStatisticsDto.getSortType());
    }


}
