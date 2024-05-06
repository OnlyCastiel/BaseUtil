package com.train.swordoffer;


/**
 * 数组练习
 */
public class ArrayTrain {


    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     *
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     *
     * 时间复杂度:O(n)
     * 空间复杂度:O(n)
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        if(s == null){
            return s;
        }
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<chars.length;i++){
            if(chars[i] == ' '){
                sb.append("%20");
            }else{
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
}
