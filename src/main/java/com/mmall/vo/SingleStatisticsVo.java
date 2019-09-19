package com.mmall.vo;

import java.math.BigDecimal;

/**
 * Created by jiangyuanyuan on 15/09/2019.
 */
public class SingleStatisticsVo implements Comparable<SingleStatisticsVo>{
    private String day;
    private int sum;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int count) {
        this.sum = count;
    }

    @Override
    public int compareTo(SingleStatisticsVo o) {
        return new BigDecimal(this.day).compareTo(new BigDecimal(o.day));
    }
}
