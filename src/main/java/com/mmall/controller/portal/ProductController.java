//package com.mmall.controller.portal;
//
//import com.mmall.common.ServerResponse;
//import com.mmall.service.IProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//
///**
// * Created by jiangyuanyuan on 14/12/17.
// */
//
//@Controller
//@RequestMapping("/product/")
//public class ProductController {
//
//    @Autowired
//    IProductService iProductService;
//
//    @RequestMapping("detail.do")
//    @ResponseBody
//    public ServerResponse getDetail(Integer productId){
//
//        return iProductService.getDetail(productId);
//    }
//
//    @RequestMapping("list.do")
//    @ResponseBody
//    public ServerResponse getList(@RequestParam(value = "keyword",required = false)String keyword,
//                                  @RequestParam(value = "categoryId",required = false)Integer categoryId,
//                                  @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
//                                  @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
//                                  @RequestParam(value = "orderBy",defaultValue = "")String orderBy){
//
//        return iProductService.getProductByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);
//    }
//}
