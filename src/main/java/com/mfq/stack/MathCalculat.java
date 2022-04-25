package com.mfq.stack;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 表达式求值
 *
 * 任何一个表达式都是由操作数（operand）、运算符（operator）和界限符（delimiter）组成的，统称它们为单词。
 * 一般地，操作数既可以是常数，也可以是被说明为变量或常量的标识符；运算符可以分为算术运算符、关系运算符和逻辑运算符3类；
 * 基本界限符有左右括号和表达式结束符等。为了叙述的简洁，在此仅讨论简单算术表达式的求值问题，这种表达式只含加、减、乘、除4种运算符。读者不难将它推广到更一般的表达式上。
 *
 * 下面把运算符和界限符统称为算符。我们知道，算术四则运算遵循以下3条规则：
 * （1）先乘除，后加减；
 * （2）从左算到右；
 * （3）先括号内，后括号外。
 *
 * 根据上述3条运算规则，在运算的每一步中，任意两个相继出现的算符θ1和θ2之间的优先关系，至多是下面3种关系之一：
 * θ1 < θ2 θ1的优先权低于θ2
 * θ1 = θ2 θ1的优先权等于θ2
 * θ1 > θ2 θ1的优先权高于θ2
 *
 * 由规则（1），先进行乘除运算，后进行加减运算，所以有“+”<“*”；“+”<“/”；“*”>“+”；“/”>“+”等。
 * 由规则（2），运算遵循左结合性，当两个运算符相同时，先出现的运算符优先级高，所以有“+”>“+”；“−”>“−”；“*”>“*”；“/”>“/”。
 * 由规则（3），括号内的优先级高，+、−、*和/为θ1时的优先性均低于“（”但高于“）”。
 *
 * 表中的“(”=“)”表示当左右括号相遇时，括号内的运算已经完成。为了便于实现，假设每个表达式均以“#”开始，以“#”结束。
 * 所以“#”=“#”表示整个表达式求值完毕。“)”与“(”、“#”与“)”以及“(”与“#”之间无优先关系，
 * 这是因为表达式中不允许它们相继出现，一旦遇到这种情况，则可以认为出现了语法错误。在下面的讨论中，我们暂假定所输入的表达式不会出现语法错误。
 *
 * 【算法步骤】
 * ① 初始化OPTR栈和OPND栈，将表达式起始符“#”压入OPTR栈。
 * ② 扫描表达式，读入第一个字符ch，如果表达式没有扫描完毕至“#”或OPTR的栈顶元素不为“#”时，则循环执行以下操作：
 *      ·若ch不是运算符，则压入OPND栈，读入下一字符ch；
 *      ·若ch是运算符，则根据OPTR的栈顶元素和ch的优先级比较结果，做不同的处理：
 *          若是小于，则ch压入OPTR栈，读入下一字符ch；
 *          若是大于，则弹出OPTR栈顶的运算符，从OPND栈弹出两个数，进行相应运算，结果压入OPND栈；
 *          若是等于，则OPTR的栈顶元素是“(”且ch是“)”，这时弹出OPTR栈顶的“(”，相当于括号匹配成功，然后读入下一字符ch。
 * ③ OPND栈顶元素即为表达式求值结果，返回此元素。
 */
public class MathCalculat {

    //运算符集合
    public static List<String> mathOptr = new ArrayList<String>(){{
        add("+");
        add("-");
        add("*");
        add("/");
        add("(");
        add(")");
        add("#");
    }};


    //给每个运算符与所有运算符的优先级关系，维护为数组，比较时，根据index取值
    //大于为1，等于为0，小于为-1,-2代表比较出错（不允许相继出现） ,例如(与#
    //从左到右运算，同优先级的，左边高
    public static int[][] priority= {
            {1,1,-1,-1,-1,1,1},
            {1,1,-1,-1,-1,1,1},
            {1,1,1,1,-1,1,1},
            {1,1,1,1,-1,1,1},
            {-1,-1,-1,-1,-1,0,-2},
            {1,1,1,1,-2,1,1},
            {-1,-1,-1,-1,-1,-2,0}
    };

    /**
     * 比较栈顶运算符与当前运算符与
     * 由表达式读取顺序可知，栈顶运算符一定在当前运算符左边
     * @param local
     * @param top
     * @return
     */
    public static int compare(String top,String local){
        return priority[mathOptr.indexOf(top)][mathOptr.indexOf(local)];
    }


    public static String doMath(String calcuStr) throws Exception{
        //非运算符（数字）
        Stack<String> opnd = new Stack<>();
        //运算符
        Stack<String> optr = new Stack<>();
        String[] s = calcuStr.split(" ");
        for(int i = 0 ; i < s.length ; i++){
            String tmpStr = s[i];
            if(mathOptr.contains(tmpStr)){//运算符
                //与栈顶元素比较
                if(!optr.isEmpty()){
                    switch (compare(optr.peek(),tmpStr)){
                        case 1: //栈顶大，弹出进行运算，结果入栈
                            String s1 = doMathAndPush(opnd, optr.pop());
                            opnd.push(s1);
                            //tmpStr 需要再次与栈顶进行比较,与i++抵消
                            i--;
                            break;
                        case 0: //等于情况，弹出,tmpStr无需入栈
                            optr.pop();
                            break;
                        case -1: //栈顶小，运算符入栈
                            optr.push(tmpStr);
                            break;
                        case -2:
                            throw new Exception("表达式格式不正确");
                    }
                }else{//首位是运算符直接入栈
                    optr.push(tmpStr);
                }
            }else{//非运算符
                opnd.push(tmpStr);
            }
        }
        return opnd.pop();
    }

    //弹出两个数字进行计算
    public static String doMathAndPush(Stack<String> opnd,String optr){
        Integer b = Integer.valueOf(opnd.pop());
        Integer a = Integer.valueOf(opnd.pop());
        Integer result = 0;
        switch (optr){// + - * /
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
        }
        return String.valueOf(result);
    }




    public static void main(String[] args) throws Exception {

        String calcuStr = "# ( 1 + 2 ) * 3 + 4 * ( 5 + 6 ) #";
        String s = doMath(calcuStr);
        System.out.println(s);
    }
}
