package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Category;
import com.mmall.pojo.Product;
import com.mmall.service.ICategoryService;
import com.mmall.service.IProductService;
import com.mmall.util.DateTimeUtil;
import com.mmall.util.PropertiesUtil;
import com.mmall.vo.ProductDetailVo;
import com.mmall.vo.ProductListVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangyuanyuan on 10/12/17.
 */
@Service("iProductService")
public class ProductServiceImpl implements IProductService{
    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ICategoryService iCategoryService;

    /*
        保存 or 更新  商品
     */
    @Override
    public ServerResponse saveOrUpdateProduct(Product product) {
        if (product!=null){
            if(StringUtils.isNotBlank(product.getSubImages())){
                String[] subImageArray = product.getSubImages().split(",");
                if(subImageArray.length > 0){
                    product.setMainImage(subImageArray[0]);
                }
            }

            if (product.getId()==null){
                int rowCount = productMapper.insert(product);
                if (rowCount>0){
                    ServerResponse.createBySuccessMessage("加入商品成功");
                }else {
                    ServerResponse.createByErrorMessage("加入商品失败");
                }
            }else {
                int rowCount = productMapper.updateByPrimaryKey(product);
                if (rowCount>0){
                    ServerResponse.createBySuccessMessage("更新商品成功");
                }else {
                    ServerResponse.createByErrorMessage("更新商品失败");
                }
            }
        }

        return ServerResponse.createByErrorMessage("商品参数不正确");
    }
    /*
        修改商品销售状态
     */
    @Override
    public ServerResponse setSaleStatus(Integer productId, Integer status) {
        if (productId == null || status == null) {
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if (rowCount>0){
            return ServerResponse.createBySuccessMessage("修改商品状态成功");
        }
        return ServerResponse.createByErrorMessage("修改商品状态失败");
    }

    /*
        获取商品详情
     */
    @Override
    public ServerResponse getManageDetail(Integer productId) {
        if (productId == null) {
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null){
            return ServerResponse.createByErrorMessage("商品已经下架");
        }

        ProductDetailVo productDetailVo = assembleProductDetailVo(product);
        return ServerResponse.createBySuccessMessageAndData("获取商品详情成功",productDetailVo);
    }



    private ProductDetailVo assembleProductDetailVo(Product product) {
        ProductDetailVo productDetailVo =  new ProductDetailVo();
        productDetailVo.setId(product.getId());
        productDetailVo.setSubtitle(product.getSubtitle());
        productDetailVo.setPrice(product.getPrice());
        productDetailVo.setSubImages(product.getSubImages());
        productDetailVo.setMainImage(product.getMainImage());
        productDetailVo.setCategoryId(product.getCategoryId());
        productDetailVo.setDetail(product.getDetail());
        productDetailVo.setName(product.getName());
        productDetailVo.setStock(product.getStock());
        productDetailVo.setStatus(product.getStatus());

        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://img.happymmall.com/"));

        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if(category == null){
            productDetailVo.setParentCategoryId(0);//默认根节点
        }else{
            productDetailVo.setParentCategoryId(category.getParentId());
        }

        productDetailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreateTime()));
        productDetailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getUpdateTime()));
        return productDetailVo;
    }
    /*
        获取商品的list列表
     */
    @Override
    public ServerResponse getProductList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<Product> productList = productMapper.selectList();

        List<ProductListVo> productListVoList = Lists.newArrayList();

        for (Product product : productList) {
            ProductListVo productListVo = assembleProductListVo(product);
            productListVoList.add(productListVo);
        }
        PageInfo pageResult = new PageInfo(productList);
        pageResult.setList(productListVoList);


        return ServerResponse.createBySuccessMessageAndData("商品列表获取成功",pageResult);
    }


    private ProductListVo assembleProductListVo(Product product) {
        ProductListVo productListVo = new ProductListVo();
        productListVo.setId(product.getId());
        productListVo.setCategoryId(product.getCategoryId());
        productListVo.setMainImage(product.getMainImage());
        productListVo.setName(product.getName());
        productListVo.setPrice(product.getPrice());
        productListVo.setStatus(product.getStatus());
        productListVo.setSubtitle(product.getSubtitle());
        productListVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://img.happymmall.com/"));

        return productListVo;
    }
    /*
        模糊查询的list
     */
    @Override
    public ServerResponse serarchProductList(String productName, Integer productId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        if (StringUtils.isNotBlank(productName)){
            productName = new StringBuilder().append("%").append(productName).append("%").toString();
        }

        List<Product> productList = productMapper.selectByNameAndProductId(productName,productId);

        List<ProductListVo> productListVoList = Lists.newArrayList();

        for (Product product : productList) {
            ProductListVo productListVo = assembleProductListVo(product);
            productListVoList.add(productListVo);
        }
        PageInfo pageResult = new PageInfo(productList);
        pageResult.setList(productListVoList);

        return ServerResponse.createBySuccessMessageAndData("模糊商品列表获取成功",pageResult);
    }
    /*
        前台获取商品详情
     */
    @Override
    public ServerResponse getDetail(Integer productId) {

        if (productId == null) {
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null){
            return ServerResponse.createByErrorMessage("商品已经下架");
        }
        if(product.getStatus() != Const.ProductStatusEnum.ON_SALE.getCode()){
            return ServerResponse.createBySuccessMessage("商品已经下架或者删除");
        }
        ProductDetailVo productDetailVo = assembleProductDetailVo(product);
        return ServerResponse.createBySuccessMessageAndData("获取商品详情成功",productDetailVo);
    }
    /*
        模糊查询机器人的  通过关键字  分类ID
     */
    @Override
    public ServerResponse getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy) {
        if (StringUtils.isBlank(keyword)&&categoryId == null){
            return ServerResponse.createByErrorCodeAndMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        List<Integer> categoryIdList = Lists.newArrayList();

        //在keyword为空的情况下  通过categoryID 来查一个分类出来  为空的情况
        if (categoryId !=null){
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            if(category == null){
                PageHelper.startPage(pageNum,pageSize);
                List<ProductListVo> productListVoList = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(productListVoList);
                return ServerResponse.createBySuccessMessageAndData("查询成功",pageInfo);
            }
            categoryIdList = iCategoryService.getDeepChildrenParallelCategory(categoryId).getData();
        }
        //查询
        if (keyword != null) {
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }

        //设置排序的规则
        if (StringUtils.isNotBlank(orderBy)){
            if (Const.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)){
                PageHelper.orderBy(orderBy.replace('_',' '));
            }
        }

        PageHelper.startPage(pageNum,pageSize);

        List<Product> productList = productMapper.selectByNameAndCategoryIds(StringUtils.isBlank(keyword)?null:keyword,categoryIdList.size()==0?null:categoryIdList);
        List<ProductListVo> productListVoList = Lists.newArrayList();
        for (Product product : productList) {
            ProductListVo productListVo = assembleProductListVo(product);
            productListVoList.add(productListVo);
        }
        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVoList);

        return ServerResponse.createBySuccessMessageAndData("模糊查询成功",pageInfo);
    }

}
