package com.base.se;

import org.opencv.core.Mat;

import java.util.Arrays;
import java.util.HashMap;

public class se1 {


    public static void main(String[] args) {

    }
    /**
     *     打印九九乘法表
     *     简单：将九九乘法表打印到控制台。
     */
    public static void print9_9(){
        for(int x =0;x<=9 ;x++){
            for(int y= 0 ; y<= x ;y++){
                System.out.print(x + "x" + y + "=" + x*y + " ");
            }
            System.out.println();
        }
    }


    /**
     *     求1000以内的水仙花数
     *     中等：打印1000以内所有满足水仙花的数，“水仙花数”是指一个三位数其各位数字的立方和等于该数本身，例如153是“水仙花数”，因为：153 = 1^3 + 5^3 + 3^3
     */
    public static void printShuiXian(){
        for(int i=0 ; i<1000 ; i++){
            char[] intChar = (i+"").toCharArray();
            int sum = 0;
            for(char num : intChar){//字符1转数字1需要减去48；
                sum += ((num - 48) * (num - 48) * (num - 48));
            }
            if(sum == i){
                System.out.println(i);
            }
        }
    }


    /**
     *     青蛙跳台阶问题
     *     困难：一共有n个台阶，一只青蛙每次只能跳一阶或是两阶，那么一共有多少种跳到顶端的方案？
     *     例如n=2，那么一共有两种方案，一次性跳两阶或是每次跳一阶。
     *
     *     核心思路：递归解决，跳N台阶级台阶的问题  f(n)=f(n-1)+F(n-1);  f(2) =2 、f(1)=1 、f(0) = 0;
     */
    public static int qingwawaijie(int n){
        if(n > 2){
            return qingwawaijie(n-1) + qingwawaijie(n-2);
        }
        if(n == 2){
            return 2;
        }
        if(n == 1){
            return 1;
        }
        if(n == 0){
            return 0;
        }
        return 0;
    }


    /**
     * 冒泡，每次冒出一个最大数，到对应位置
     * 不断使无序数组部分中的最大值，到无序数组的首位
     * @param input
     */

    /**
     * 插入排序：选择第一个数，当只有一个数时，他是有序的，然后每次选择一个数据，插入已排序数组的对应位置
     * @param input
     */

    /**
     * 选择排序：每次选择当前数组的
     * @param input
     */


    /**
     * 0/1背包问题（回溯法、剪枝/动态规划优化）
     * 给定 n件物品，每一个物品的重量为 w[n]，每个物品的价值为 v[n]。
     * 现挑选物品放入背包中，假定背包能承受的最大重量为 capacity，求装入物品的最大价值是多少?
     *
     *
     * 回溯法核心思路：递归思路
     * 对于拿第x件物品时，此时耗费空间y，总价值应该为,拿或者不拿中的价值最大值
     * 初始值，x=0,y=0
     * 拿得动情况：V(x,y) = max(V(x+1,y+w[x])+v[x],V(x+1,y))
     * 拿不动情况：V(x,y) = max(V(x+1,y+w[x])
     * 一直回溯到 x=n-1
     *
     * 思路：0/1背包问题，数学角度唯有穷举法
     * 算法角度为通过递归的方式max值，探寻了所有组合的分支，
     * @param
     */
    public static int countPackage(){
        int[] w = {2, 3, 4, 5};
        int[] v = {3, 4, 5, 6};
        int capacity = 8;
        //return countPackageDesc(w.length -1,capacity,w,v);
        return countPackageAsc(0,0,w,v,capacity);
    }

    /**
     * 反溯递归
     * @param x 当前回溯物品
     * @param y 当前剩余空间
     * @param w 重量数组
     * @param v 价值数组
     * @return
     */
    public static int countPackageDesc(int x,int y,int[] w,int[] v){
        if(x >= 0){
            if(y >= w[x]){//可以拿，在 不拿 与 拿 之间取max
                return Math.max(countPackageDesc(x-1,y,w,v),//不拿
                        countPackageDesc(x-1,y-w[x],w,v) + v[x]); //拿
            }else{//拿不动，空间不足
                return countPackageDesc(x-1,y,w,v);
            }
        }else{//结束了，选择完了
            return 0;
        }
    }


    /**
     * 正溯递归
     * @param index 当前拿的
     * @param weight 当前总重量
     * @param w
     * @param v
     * @return
     */
    public static int countPackageAsc(int index,int weight,int[] w,int[] v,int capacity){
        if(index == w.length){
            //最后一个拿完了
            return 0;
        }else{
            if(capacity >= weight + w[index]){//拿得动
                //不拿与拿之间取max
                return Math.max(countPackageAsc(index+1,weight + w[index],w,v,capacity)+v[index],
                        countPackageAsc(index+1,weight,w,v,capacity));
            }else{
                //拿不动，是否要直接结束，否，可能后面一个拿得动
                return countPackageAsc(index+1,weight,w,v,capacity);
            }
        }
    }


    // 将字母转换成数字
    public static void letterToNum(String input) {
        for (byte b : input.getBytes()) {
            System.out.print(b - 96);
        }
    }

    // 将数字转换成字母
    public static void numToLetter(String input) {
        for (byte b : input.getBytes()) {
            System.out.print((char) (b + 96));
        }
    }
}
