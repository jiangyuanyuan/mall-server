package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ShippingMapper;
import com.mmall.pojo.Shipping;
import com.mmall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jiangyuanyuan on 26/12/17.
 */
@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService{
    @Autowired
    ShippingMapper shippingMapper;

    /**
     *
     * 新建地址
     *
     * @param userId
     * @param shipping
     * @return
     */
    @Override
    public ServerResponse add(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.insert(shipping);
        if (rowCount>0){
            Map map = Maps.newHashMap();
            map.put("shippingId",shipping.getId());
            return ServerResponse.createBySuccessMessageAndData("新建地址成功",map);
        }
        return ServerResponse.createByErrorMessage("新建地址失败");
    }

    /**
     *
     * 删除地址
     *
     * @param userId
     * @param shippingId
     * @return
     */
    @Override
    public ServerResponse del(Integer userId,Integer shippingId) {
        //一定要防止横向越权
        int rowCount = shippingMapper.deleteByShippingIdUserId(userId,shippingId);
        if (rowCount>0){
            return ServerResponse.createBySuccessMessage("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }

    /**
     *
     * 更新地址
     *
     * @param userId
     * @param shipping
     * @return
     */
    @Override
    public ServerResponse update(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);//横向越权
        int rowCount = shippingMapper.updateByShipping(shipping);
        if (rowCount>0){
            return ServerResponse.createBySuccessMessage("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }

    /**
     *
     * 查询地址详情
     *
     * @param userId
     * @param shippingId
     * @return
     */
    @Override
    public ServerResponse select(Integer userId, Integer shippingId) {
        //反正横向越权
        Shipping shipping = shippingMapper.selectByShippingIdUserId(userId,shippingId);
        if (shipping == null){
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }
        return ServerResponse.createBySuccessMessageAndData("查询地址成功",shipping);
    }

    /**
     *  地址列表
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse list(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createBySuccessMessageAndData("查询成功",pageInfo);
    }


}
