package com.mmall.dao;

import com.mmall.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<Cart> selectCartByUserId(Integer userId);

    List<Cart> selectByUserIdCheckStatus(Integer userId);

    Cart selectByUserIdAndProductID(@Param("userId") Integer userId, @Param("productId")Integer productId);

    void deleteByUserIdProductIds(List<String> productList);

    void updateCheckStatusByUserId(@Param("userId") Integer userId, @Param("productId")Integer productId,@Param("type") Integer type);

    Integer selectCartProductCount(Integer userId);
}