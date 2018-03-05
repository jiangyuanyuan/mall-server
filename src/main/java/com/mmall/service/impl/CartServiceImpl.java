package com.mmall.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CartMapper;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Cart;
import com.mmall.pojo.Product;
import com.mmall.service.ICartService;
import com.mmall.util.BigDecimalUtil;
import com.mmall.util.PropertiesUtil;
import com.mmall.vo.CartProductVo;
import com.mmall.vo.CartVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jiangyuanyuan on 20/12/17.
 */
@Service("iCartService")
public class CartServiceImpl implements ICartService{

    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;

    /*
        购物车的所以商品
     */
    @Override
    public ServerResponse list(Integer userId) {
        CartVo cartVo = getCartVoLimit(userId);
        return ServerResponse.createBySuccessMessageAndData("",cartVo);
    }

    /*
        添加购物车商品
     */
    @Override
    public ServerResponse add(Integer userId, Integer productId, Integer count) {
        if (userId == null && productId == null && count == null){
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.selectByUserIdAndProductID(userId,productId);
        if (cart == null){
            Cart cartItem = new Cart();
            cartItem.setUserId(userId);
            cartItem.setChecked(Const.Cart.CHECKED);
            cartItem.setQuantity(count);
            cartItem.setProductId(productId);
            cartMapper.insert(cartItem);
        }else {
            //更新已有购物车的数量
            cart.setQuantity(count+cart.getQuantity());
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        return this.list(userId);
    }
    /*
        更新购物车
     */
    @Override
    public ServerResponse update(Integer userId, Integer productId, Integer count) {
        if(productId == null || count == null){
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.selectByUserIdAndProductID(userId,productId);
        if(cart != null){
            cart.setQuantity(count);
        }
        cartMapper.updateByPrimaryKey(cart);
        return this.list(userId);
    }
    /*
        删除购物车商品
     */
    @Override
    public ServerResponse delete(Integer userId, String productIds) {
        List<String> productList = Splitter.on(",").splitToList(productIds);
        if (CollectionUtils.isEmpty(productList)){
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        cartMapper.deleteByUserIdProductIds(productList);
        return this.list(userId);
    }

    /**
     *   全选   反选
     *
     * @param type
     * @param userId
     * @return
     */
    @Override
    public ServerResponse selectAllOrUnselectAll(Integer userId, Integer productId,Integer type) {
        if (userId == null){
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        cartMapper.updateCheckStatusByUserId(userId,productId,type);
        return this.list(userId);
    }

    /**
     * 查询购物车数量
     * @param userId
     * @return
     */
    public ServerResponse<Integer> getCartProductCount(Integer userId){
        if(userId == null){
            return ServerResponse.createBySuccessMessageAndData("购物车数量",0);
        }
        return ServerResponse.createBySuccessMessageAndData("购物车数量",cartMapper.selectCartProductCount(userId));
    }


    private CartVo getCartVoLimit(Integer userId) {
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);

        CartVo cartVo = new CartVo();
        List<CartProductVo> cartProductVoList = Lists.newArrayList();

        BigDecimal cartTotalPrice = new BigDecimal("0");
        //定义一个购物车所有商品的总价
        for (Cart cartItem : cartList) {
            CartProductVo cartProductVo = new CartProductVo();

            cartProductVo.setId(cartItem.getId());
            cartProductVo.setUserId(cartItem.getUserId());
            cartProductVo.setProductId(cartItem.getProductId());

            Product product = productMapper.selectByPrimaryKey(cartItem.getProductId());
            if (product != null) {
                cartProductVo.setProductMainImage(product.getMainImage());
                cartProductVo.setProductName(product.getName());
                cartProductVo.setProductPrice(product.getPrice());
                cartProductVo.setProductSubtitle(product.getSubtitle());
                cartProductVo.setProductStock(product.getStock());
                cartProductVo.setProductStatus(product.getStatus());
                cartProductVo.setProductChecked(cartItem.getChecked());

                //限制数量
                int buyLimitCount = 0;
                if (product.getStock() > cartItem.getQuantity()){
                    buyLimitCount = cartItem.getQuantity();
                    cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_SUCCESS);
                }else {
                    buyLimitCount = product.getStock();
                    cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_FAIL);
                    Cart cartForQuantity = new Cart();
                    cartForQuantity.setId(cartItem.getId());
                    cartForQuantity.setQuantity(buyLimitCount);
                    cartMapper.updateByPrimaryKeySelective(cartForQuantity);
                }

                cartProductVo.setQuantity(buyLimitCount);
                //单个商品的总价
                cartProductVo.setProductTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue(),cartProductVo.getQuantity().doubleValue()));
            }
            //总商品总价
            if (cartProductVo.getProductChecked() == Const.Cart.CHECKED){
                cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(),cartProductVo.getProductTotalPrice().doubleValue());
            }
            cartProductVoList.add(cartProductVo);

        }
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setAllChecked(this.getAllChecked(userId));
        cartVo.setCartProductVoList(cartProductVoList);
        cartVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));
        return cartVo;
    }

    public Boolean getAllChecked(Integer userId) {
        if (userId == null){
            return false;
        }
        return cartMapper.selectByUserIdCheckStatus(userId).size() == 0;
    }
}
