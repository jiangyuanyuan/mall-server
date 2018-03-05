package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;

/**
 * Created by jiangyuanyuan on 10/12/17.
 */
public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse setSaleStatus(Integer productId, Integer status);

    ServerResponse getManageDetail(Integer productId);

    ServerResponse getProductList(int pageNum, int pageSize);

    ServerResponse serarchProductList(String productName, Integer productId, int pageNum, int pageSize);

    ServerResponse getDetail(Integer productId);

    ServerResponse getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);
}
