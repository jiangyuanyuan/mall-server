package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by jiangyuanyuan on 23/11/17.
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String CHECK_USERNAME = "check_username";
    public static final String CHECK_EMAIL = "check_username";

    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }
    public interface Role{
        int ADMINISTRATOR = 0;
        int GENERAL_USER = 1;
    }

    public interface Cart{
        int CHECKED = 1;//即购物车选中状态
        int UN_CHECKED = 0;//购物车中未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    public enum ProductStatusEnum{
        ON_SALE(1,"在线");
        private int code;
        private String value;
        ProductStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }
}
