package com.train.subject;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 简单计算器实现
 * 运算符支持 + - * / ( )
 */
public class DoMath {


    //对于字符串表达式exp，进行遍历，数字直接入postExp,运算符比较优先级：大于入op,其余情况出栈入postExp,)遇见(则出栈抵消
    public static int doMathByExp(String exp){
        return 0;
    }


    //根据postExp做计算
    private static int mathByPostExp(String postExp){

        return 0;
    }

    //获取postExp结果
    private static String converToPostExp(String exp){
        //栈控制
        Stack<String> postExp = new Stack<>();
        //算数符号栈
        Stack<String> opExp = new Stack<>();
        //遍历，提取字符与算数符号，算数符号直接进栈
        //正则表达式提取数字
        //正则表达式提取运算符
        

        return null;
    }






    /**
     * 比较右运算符与左运算符的优先级,必须大于才能进栈
     * @param rightChar 右运算符
     * @param leftChar 左运算符
     * @return -1优先级较小，需要出栈； 0优先级相等，需要出栈； 1优先级比较大，进栈
     */
    private static int compare(char rightChar,char leftChar){
        return rightpri(rightChar).compareTo(leftpri(leftChar));
    }

    /**
     * 获取左表达式的优先级
     * @param op
     * @return
     */
    private static Integer leftpri(char op){
        return LEFT_PRI_MAP.get(op);
    }

    //左运算符，即栈顶运算法，待出栈运算符
    private static final Map<Character,Integer> LEFT_PRI_MAP= new HashMap<Character,Integer>(){
        {
            put('=',0);
            put('(',1);
            put('+',3);
            put('-',3);
            put('*',5);
            put('\\',5);
            put(')',6);
        }
    };

    /**
     * 获取右表达式的优先级
     * @param op
     * @return
     */
    private static Integer rightpri(char op){
        return RIGHT_PRI_MAP.get(op);
    }

    //右运算符，待进栈运算符
    private static final Map<Character,Integer> RIGHT_PRI_MAP= new HashMap<Character,Integer>(){
        {
            put('=',0);
            put('(',6);
            put('+',2);
            put('-',2);
            put('*',4);
            put('\\',4);
            put(')',1);
        }
    };

}
