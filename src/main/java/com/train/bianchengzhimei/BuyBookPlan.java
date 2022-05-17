package com.train.bianchengzhimei;

import com.sun.deploy.ui.AboutDialog;

import java.util.Arrays;

/**
 * 在节假日的时候，书店一般都会做促销活动。由于《哈利波特》系列相当畅销，店长决定通过促销活动来回馈读者。
 * 上柜的《哈利波特》平装本系列中，一共有五卷。假设每一卷单独销售均需8欧元。
 * 如果读者一次购买不同的两卷，就可以扣除5%的费用，三卷则更多。假设具体折扣的情况如下。
 *
 * 2卷  5%
 * 3卷  10%
 * 4卷  20%
 * 5卷  25%
 *
 * 在一份订单中，根据购买的卷数及本数，就会出现可以应用不同折扣规则的情况。
 * 但是，一本书只会应用一个折扣规则。比如，读者一共买了两本卷一，一本卷二。
 * 那么，可以享受到5%的折扣。另外一本卷一则不能享受折扣。如果有多种折扣，希望计算出的总额尽可能的低。
 *
 *
 * 动态规划思路：罗列所有选择的可能
 * 对于购买5本书，分别为v1、v2、v3、v4、v5本情况，且存在v1 > v2 > v3 > v4 > v5 (每本价格相同，故排序)
 * 存在以下递推公式
 * F（v1,v2,v3,v4,v5） = MIN{
 *     F(v1-1,v2-1,v3-1,v4-1,v5-1), (v5>0)
 *     F(v1-1,v2-1,v3-1,v4-1,v5),  (v4>0)
 *     F(v1-1,v2-1,v3-1,v4,v5),  (v3>0)
 *     F(v1-1,v2-1,v3,v4,v5),  (v2>0)
 *
 * }
 * F（v1,v2,v3,v4,v5） = F(v1-1,v2,v3,v4,v5)  (v1>0,v2,v3,v4,v5=0)
 * 递推公式中，要持续维护 v1 > v2 > v3 > v4 > v5
 *
 *
 */
public class BuyBookPlan {

    public double caculateTotalSum(int v1,int v2,int v3,int v4,int v5){
        //排序
        int[] books = {v1,v2,v3,v4,v5};
        int temp;
        for(int i=0;i<books.length;i++){
            for(int j=1;j<books.length - i;j++){
                if(books[j-1] < books[j]){
                    temp = books[j-1];
                    books[j-1] = books[j];
                    books[j] = temp;
                }
            }
        }
        v1=books[0];
        v2=books[1];
        v3=books[2];
        v4=books[3];
        v5=books[4];

        //分支
        if(v2 == 0){
            return 8 * books[0];
        }
        if(v3 == 0){//1本单独买  2本组合买
            return min(8 + caculateTotalSum(v1-1,v2,v3,v4,v5),
                    8*2*(1-0.05)+caculateTotalSum(v1-1,v2-1,v3,v4,v5));
        }
        if(v4 == 0){//1本单独买  2本组合买 3本组合买
            return min(8 + caculateTotalSum(v1-1,v2,v3,v4,v5),
                    8*2*(1-0.05)+caculateTotalSum(v1-1,v2-1,v3,v4,v5),
                    8*3*(1-0.1)+caculateTotalSum(v1-1,v2-1,v3-1,v4,v5));
        }
        if(v5 == 0){//1本单独买  2本组合买 3本组合买 4本组合买
            return min(8 + caculateTotalSum(v1-1,v2,v3,v4,v5),
                    8*2*(1-0.05)+caculateTotalSum(v1-1,v2-1,v3,v4,v5),
                    8*3*(1-0.1)+caculateTotalSum(v1-1,v2-1,v3-1,v4,v5),
                    8*4*(1-0.2)+caculateTotalSum(v1-1,v2-1,v3-1,v4-1,v5));
        }
        //1本单独买  2本组合买 3本组合买 4本组合买 5本组合买
        return min(8 + caculateTotalSum(v1-1,v2,v3,v4,v5),
                8*2*(1-0.05)+caculateTotalSum(v1-1,v2-1,v3,v4,v5),
                8*3*(1-0.1)+caculateTotalSum(v1-1,v2-1,v3-1,v4,v5),
                8*4*(1-0.2)+caculateTotalSum(v1-1,v2-1,v3-1,v4-1,v5),
                8*5*(1-0.25)+caculateTotalSum(v1-1,v2-1,v3-1,v4-1,v5-1));
    }

    public double min(double... args){
        double temp = Double.MAX_VALUE;
        for(double value : args){
            temp = Math.min(temp,value);
        }
        return temp;
    }


    public static void main(String[] args) {
        BuyBookPlan bookPlan = new BuyBookPlan();
        double v = bookPlan.caculateTotalSum(2, 2, 2, 1, 1);
        System.out.println(v);
    }

}
