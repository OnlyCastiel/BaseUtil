package com.train.swordoffer;

import java.util.HashMap;

/**
 * 各类题目
 */
public class Solution {

    /**
     * 有限状态自动机
     *
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     *
     * 数值（按顺序）可以分成以下几个部分：
     *
     * 若干空格
     * 一个 小数 或者 整数
     * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
     * 若干空格
     * 小数（按顺序）可以分成以下几个部分：
     *
     * （可选）一个符号字符（'+' 或 '-'）
     * 下述格式之一：
     * 至少一位数字，后面跟着一个点 '.'
     * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
     * 一个点 '.' ，后面跟着至少一位数字
     * 整数（按顺序）可以分成以下几个部分：
     *
     * （可选）一个符号字符（'+' 或 '-'）
     * 至少一位数字
     * 部分数值列举如下：
     *
     * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
     * 部分非数值列举如下：
     *
     * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
     * @param s
     * @return
     */

    /**
     * 状态自动机-转移方程，在X状态下，经过E符号，可以进入Y状态
     *
     * 字符一共有五种 ' '/'s'/'d'/'.'/'e'分别代表空格、正负号、数字、小数点、幂符号.其他为非法字符
     *
     * 状态一共有9中
     * 0 起始的空格
     * 1 幂符号之前的正负号
     * 2 小数点前数字
     * 3 小数点以及小数点之后的数字
     * 4 当小数点前为空格时，小数点、小数点后的数字（注意：此时要求小数点后必须有数字）
     * 5 幂
     * 6 幂后符号
     * 7 幂后数字
     * 8 幂后空格
     *
     * TODO 如何注明可结束,到字符串末尾时，判断当前状态是否为可结束状态，获取’$‘的值是否为-1
     */
    HashMap<Integer,HashMap<Character,Integer>> stateMap = new HashMap<Integer,HashMap<Character,Integer>>(){{
        // 状态0 可通过空格去0、通过正负号去1、通过数字去2、通过小数点去4
        put(0,new HashMap<Character,Integer>(){{
            put(' ',0);
            put('s',1);
            put('d',2);
            put('.',4);
        }});
        //状态1 通过数字去2、通过小数点去4
        put(1,new HashMap<Character,Integer>(){{
            put('d',2);
            put('.',4);
        }});
        //状态2 通过数字去2、通过小数点去3、通过幂符号去5 （可结束）
        put(2,new HashMap<Character,Integer>(){{
            put('d',2);
            put('.',3);
            put('e',5);
            put(' ',8);
            put('$',-1);
        }});
        //状态3 通过数字去3、幂符号去5 （可结束）
        put(3,new HashMap<Character,Integer>(){{
            put('d',3);
            put('e',5);
            put(' ',8);
            put('$',-1);
        }});
        //状态4 通过数字去3
        put(4,new HashMap<Character,Integer>(){{
            put('d',3);
        }});
        //状态5 通过正负号去6、通过数字去7
        put(5,new HashMap<Character,Integer>(){{
            put('s',6);
            put('d',7);
        }});
        //状态6 通过数字去7
        put(6,new HashMap<Character,Integer>(){{
            put('d',7);
        }});
        //状态7 通过数字去7 通过空格去8 （可结束）
        put(7,new HashMap<Character,Integer>(){{
            put('d',7);
            put(' ',8);
            put('$',-1);
        }});
        //状态8 通过空格去8 （可结束）
        put(8,new HashMap<Character,Integer>(){{
            put(' ',8);
            put('$',-1);
        }});
    }};


    public char getCharType(char elem){
        if(elem == ' ' || elem == '.'){
            return elem;
        }
        if(elem >= '0' && elem <= '9'){
            return 'd';
        }
        if(elem == '+' || elem == '-'){
            return 's';
        }
        if(elem == 'e' || elem == 'E'){
            return 'e';
        }
        return 0;
    }

    public boolean isNumber(String s) {
        char[] chars = s.toCharArray();
        Integer state = 0;
        for (char elem : chars){
            if(state == null){
                return false;
            }
            char charType = getCharType(elem);
            if(charType == 0){
               return false;
            }
            state = stateMap.get(state).get(charType);
        }
        if(state != null && stateMap.get(state).get('$') != null && stateMap.get(state).get('$') == -1){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        /**
         * 部分数值列举如下：
         *
         * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
         * 部分非数值列举如下：
         *
         * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
         */
        Solution solution = new Solution();
        boolean flag = solution.isNumber("3.");
        System.out.println(flag);
    }
}
