package com.train.leetcode;

import java.util.*;

public class BookTrain {


    /**
     * 十进制数N和其他d进制数的转换是计算机实现计算的基本问题，
     * 其解决方法很多，其中一个简单算法基于下列原理：
     * N=(N div d) × d+N mod d（其中，div为整除运算，mod为求余运算）
     */
    public static String changeTenTo(int num , int jinzhi){
        List<String> result= new ArrayList<>();
        while (num >= jinzhi){
            result.add((num % jinzhi) + "");
            num = num / jinzhi;
        }
        result.add(num + "");
        Collections.reverse(result);
        return String.join("",result);
    }

    /**
     * 汉诺塔问题
     * 假设有3个分别命名为A、B和C的塔座，在塔座A上插有n个直径大小各不相同，
     * 依小到大编号为1，2，…，n的圆盘（如图3.8所示）。
     * 现要求将塔座A上的n个圆盘移至塔座C上，并仍按同样顺序叠排，圆盘移动时必须遵循下列规则：
     *
     *（1）每次只能移动一个圆盘；
     *（2）圆盘可以插在A、B和C中的任一塔座上；
     *（3）任何时刻都不能将一个较大的圆盘压在较小的圆盘之上。
     * @param
     */
    public static void moveHanoi(List<String> hanoi,char from,char dest,char temp){ //移动num个圆盘从A到C
        if(hanoi.size() == 1){
            System.out.println("从"+from+"柱移动第"+hanoi.get(0)+"盘，到"+dest+"柱子上");

            return;//移动最后一个到C
        }else{
            //三步操作，
            //1、以C为过渡，将A上的n-1移动到B上
            moveHanoi(hanoi.subList(0,hanoi.size()-1),from,temp,dest);
            //2、移动n到C上
            System.out.println("从"+from+"柱移动第"+hanoi.get(hanoi.size()-1)+"盘，到"+dest+"柱子上");
            //3、以A为过度，将B上的盘子移动n-1到C上
            moveHanoi(hanoi.subList(0,hanoi.size()-1),temp,dest,from);
        }
    }


    /**
     * 栈实现字符串表达式计算
     *
     * 一个栈保存运算符：运算符特性：- + * % / ( )  可通过穷举法进行正则匹配
     * 一个栈保存操作数： -xxx.xxx 正则匹配
     *
     * 扫描表达式，读入第一个字符ch，如果表达式没有扫描完毕至“#”或OPTR的栈顶元素不为“#”时，则循环执行以下操作：
     *  ·若ch不是运算符，则压入OPND栈，读入下一字符ch；
     *  ·若ch是运算符，则根据OPTR的栈顶元素和ch的优先级比较结果，做不同的处理：
     *
     * 若是小于，则ch压入OPTR栈，读入下一字符ch；
     * 若是大于，则弹出OPTR栈顶的运算符，从OPND栈弹出两个数，进行相应运算，结果压入OPND栈；
     * 若是等于，则OPTR的栈顶元素是“(”且ch是“)”，这时弹出OPTR栈顶的“(”，相当于括号匹配成功，然后读入下一字符ch。
     *
     * @param args
     */
    public static Double caculateString(){

        return null;
    }


    public static void main(String[] args) {
        List<String> hanoi = new ArrayList<>();
        hanoi.add("1");
        hanoi.add("2");
        hanoi.add("3");
        moveHanoi(hanoi,'A','C','B');

        //System.out.println(changeTenTo(1348,8));
    }
}
