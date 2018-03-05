package com.mmall.service;

import com.mmall.common.ServerResponse;

import java.util.List;

/**
 * Created by jiangyuanyuan on 6/12/17.
 */
public interface ICategoryService {

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse setCategoryName(Integer categoryId, String categoryName);

    ServerResponse getChildrenParallelCategory(Integer categoryId);

    ServerResponse<List<Integer>> getDeepChildrenParallelCategory(Integer categoryId);
}
