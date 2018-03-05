package com.mmall.service;

import com.mmall.common.ServerResponse;

/**
 * Created by jiangyuanyuan on 20/12/17.
 */
public interface ICartService {
    ServerResponse list(Integer id);

    ServerResponse add(Integer id, Integer productId, Integer count);

    ServerResponse update(Integer id, Integer productId, Integer count);

    ServerResponse delete(Integer id, String productIds);

    ServerResponse selectAllOrUnselectAll(Integer userId, Integer productId,Integer type);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
