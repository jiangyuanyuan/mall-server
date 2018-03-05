package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by jiangyuanyuan on 6/12/17.
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    /*
        增加商品分类
     */
    @Override
    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null|| StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("添加商品参数错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);//分类可用

        int rowCount = categoryMapper.insert(category);
        if (rowCount>0){
            return ServerResponse.createBySuccessMessage("添加商品成功");
        }
        return ServerResponse.createByErrorMessage("添加商品失败");

    }
    /*
        设置商品分类
     */
    @Override
    public ServerResponse setCategoryName(Integer categoryId, String categoryName) {
        if (categoryId == null|| StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("更新商品参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKey(category);
        if(rowCount>0){
            return ServerResponse.createBySuccessMessage("更新商品成功");
        }
        return ServerResponse.createByErrorMessage("更新商品失败");
    }
    /*
        获取子节点的商品信息
     */
    @Override
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        List<Category> categoryList =categoryMapper.getChildrenParallelCategoryById(categoryId);
        if (CollectionUtils.isEmpty(categoryList)){
            logger.info("未找到分类的子分类");
        }
        return ServerResponse.createBySuccessMessageAndData("成功找到分类的子分类",categoryList);
    }

    /*
        递归获取商品信息
     */
    @Override
    public ServerResponse<List<Integer>> getDeepChildrenParallelCategory(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categoryId,categorySet);
        
        List<Integer> categoryList = Lists.newArrayList();

        if (categoryId !=null){
            for (Category category : categorySet) {
                categoryList.add(category.getId());
            }
        }
        return ServerResponse.createBySuccessMessageAndData("获取孩子子节点成功",categoryList);
    }

    private Set<Category> findChildCategory(Integer categoryId,Set<Category> categorySet){
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category == null){
            categorySet.add(category);
        }
        List<Category> categoryList = categoryMapper.getChildrenParallelCategoryById(categoryId);
        for (Category categoryItem : categoryList) {
            findChildCategory(categoryItem.getId(),categorySet);
        }
        
        return categorySet;

    }
}
