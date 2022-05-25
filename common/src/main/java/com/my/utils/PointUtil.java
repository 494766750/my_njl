package com.my.utils;

/**
 * 2022/5/25
 * NJL
 */
public class PointUtil {
    /**
     * 计算点 是否在一个固定点的半径范围内
     * @2016年10月20日
     * @param a  经度1 已知
     * @param b   纬度1 已知
     * @param x  经度2
     * @param y  纬度2
     * @param r  半径(米)比较的半径距离
     * @return  object[0]是否在已知的范围了
     */
    public static Object[] getManyPoint(double a,double b,double x,double y,double r){
        Object[] obj = new Object[2];
        double pk = 180 / 3.14169;
        double a1 = a/pk;
        double a2 = b/pk;
        double b1 = x/pk;
        double b2 = y/pk;
        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = 6366000 * Math.acos(t1 + t2 + t3);
        if(r >= tt){
            obj[0] = true;
        }else{
            obj[0] = false;
        }
        obj[1] = tt;
        return obj;
    }
    
    public static void main(String[] args) {
        double a = 115.034545;
        double b = 35.525944;
        double x = 115.034544;
        double y = 34.525733;
        double r = 200;
        Object[] obj = getManyPoint(a,b,x,y,r);
        System.out.println("---->"+obj);
    }
}
