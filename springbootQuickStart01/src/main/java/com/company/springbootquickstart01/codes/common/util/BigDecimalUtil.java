package com.company.springbootquickstart01.codes.common.util;

import java.math.BigDecimal;

public class BigDecimalUtil {
    private BigDecimalUtil() {

    }
    //加
    public static Double add(double v1, double v2) {// v1 + v2
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal res = b1.add(b2);
        return res.doubleValue();
    }
    //减
    public static Double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal res = b1.subtract(b2);
        return res.doubleValue();
    }
    //乘
    public static Double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal res = b1.multiply(b2);
        return res.doubleValue();
    }
    //除
    public static Double div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        // 2 = 保留小数点后两位   ROUND_HALF_UP = 四舍五入
        BigDecimal res = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);// 应对除不尽的情况
        return res.doubleValue();
    }
}
