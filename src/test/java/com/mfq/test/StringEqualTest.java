package com.mfq.test;

public class StringEqualTest {
    public static void main(String[] args) {

/*        1.直接定义字符串变量的时候赋值，如果表达式右边只有字符串常量，那么就是把变量存放在常量池里面。

　　　　2.new出来的字符串是存放在堆里面。

　　　　3.对字符串进行拼接操作，也就是做"+"运算的时候，分2中情况：

　　　　　　i.表达式右边是纯字符串常量，那么存放在栈里面。

　　　　　　ii.表达式右边如果存在字符串引用，也就是字符串对象的句柄，那么就存放在堆里面。*/
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());
    }

}
