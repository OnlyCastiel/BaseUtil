package com.train.leetcode;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 字符串练习
 */
public class StringTest {

    /**
     * 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     * 遍历字符串，比较字符串的前X位，记录X,之后X只会越来越小
     * @param strs
     */
    public static String longestCommonPrefix(String[] strs) {
        char[] baseStr = strs[0].toCharArray();
        int maxCommon =baseStr.length;
        for(int i=1;i<strs.length;i++){
            char[] compartStr = strs[i].toCharArray();
            int index = 0;
            while(index < baseStr.length && index < compartStr.length
            && baseStr[index] == compartStr[index]){
                index ++;
            }
            if(maxCommon > index){
                maxCommon = index;
            }
            if(maxCommon == 0){//如果在尚未遍历完所有的字符串时，最长公共前缀已经是空串，则最长公共前缀一定是空串
                break;
            }
        }
        return new String(baseStr).substring(0,maxCommon);
    }

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 长度最起码为2
     * 关键问题：如何找到回文起点，与回文终点----pass  确定回文中心
     * 双指针：从index=1号元素开始，遍历index左右两边，判断是否相等；
     *  两种：1-以字符为中心，奇数字符对称  2-以空为中心，偶数字符对称
     *
     *  优解=中心扩散法：奇数扩散，偶数扩散；
     *
     * @param s
     */
    public static String longestPalindrome(String s) {
        int maxEnd = 0;
        int minStart = 0;
        for(float index = 0.5f;index < s.length()-1;index+=0.5){
            int start = 0,end = 0;
            //x减去0.5向下取整，y+1向下取整
            for(int x = (int) (index -0.5),y=(int) (index + 1) ; x>=0 && y< s.length(); x--,y++){
                if(s.charAt(x) == s.charAt(y)){
                    end = y;
                    start = x;
                }else{
                    break;
                }
            }
            if(end - start > maxEnd - minStart ){
                minStart = start;
                maxEnd = end;
            }
        }
        return s.substring(minStart,maxEnd + 1);
    }


    /**
     * 给你一个字符串 s ，颠倒字符串中 单词 的顺序。
     *
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     *
     * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
     *
     * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
     * @param s
     */
    public static String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        s = s.trim();
        String[] s1 = s.split(" ");
        for(int i = s1.length -1 ;i >=0 ; i--){
            if(s1[i].trim().length() > 0){
                result.append(s1[i].trim()).append(" ");
            }
        }
        return result.toString().trim();
    }


    /**
     * 实现 strStr()
     * 实现 strStr() 函数。
     * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
     *
     * 说明：
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
     *
     * 核心要点： 字符串匹配算法：KMP
     * Knuth–Morris–Pratt（KMP）算法是一种改进的字符串匹配算法，它的核心是利用匹配失败后的信息，尽量减少模式串与主串的匹配次数以达到快速匹配的目的

     * @param source 源字符串
     * @param target 目的字符串
     */

    /**
     * AATPGAAY
     * 第一轮： i=1,j=0  -> j=1 p[1]=1;
     * 第二轮  i=2,j=1  -> j=0
     * @param needle
     * @return
     */
    private static int[] nextBuilder(String needle) {
        int m = needle.length();
        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        return pi;
    }

    public int strStr(String text, String pattern) {
        if(text == null || pattern == null){
            return -1;
        }
        if(pattern.length() == 0){
            return 0;
        }
        // 首先得到前缀表
        // 前缀表是一个一维数组，数组的下标表示遍历pattern的指针位置(j),数组的值表示当pattern的指针j和text的指针i对应的字符内容不同 时(坏字符),j指针需要移动
        // 到pattern的位置
        int[] next = getNext(pattern);
        // 定义两个指针，i用来遍历text，j用来遍历pattern
        int i = 0;
        int j = 0;
        char[] charsT = text.toCharArray();
        char[] charsP = pattern.toCharArray();
        while(j < charsP.length && i < charsT.length){
            // 当指针i和j位置上的元素不匹配时，需要将j指针通过next数组移动到指定位置上
            if(charsT[i] != charsP[j]){
                j = next[j];
                // 如果j等于-1，说明j指针已经pattern的最前面了，并且已经没有共有字符串了，直接将j指针和i指针同时往后移动一位j++
                // 这里就能体现将next[0]设置成-1的巧妙了，这样j++，i++的时候就意味着把text当做一个全新的字符串，除去了[0,i)之前的元素，j=0也从pattern
                // 的起始位置开始匹配
                if(j == -1){
                    i++;
                    j++;
                }
            }else{
                //如果匹配上，那就正常进行下一个字符的匹配
                i++;
                j++;
            }
        }
        // 循环结束后，如果j和pattern长度相同，说明全部匹配完了，也就是在text中找到了第一次出现pattern的位置，这时候i指针已经到了pattern字符位置的最后一个字符的位置
        // 起始位置需要减掉pattern的长度
        if(j == charsP.length){
            return i - j;
        }
        return -1;
    }

    public static int[] getNext(String pattern){
        // 先求出字符串的前缀表
        char[] charArr = pattern.toCharArray();
        int[] next = new int[charArr.length];
        // 因为字符串的第一个元素没有前后缀，所以共有最大字符串长度为0
        next[0] = 0;
        int len = 0;
        int i = 1;
        /*
            i   str          next[i]
            0   A            0
            1   AB           0
            2   ABA          1
            3   ABAB         2
            4   ABABC        0
            5   ABABCA       1
            6   ABABCAB      2
            7   ABABCABA     3
            8   ABABCABAA    1
        */
        while (i < charArr.length){
            // 1.举例：比如这次进来的字符串是上面的AB,此时上一次的共有字符串长度是len=0(因为上一次就一个A字符，没有共有字符串，当然是0)，
            // 要想判断这次共有字符串长度会不会加1，只需要判断这次的字符串AB比上次字符串A多出来的字符(也就是B)是不是和上次共有字符串长度位置上的字符相等
            // 也就是charArr[1(i)] == charArr[0(len)]?，这里是不等，所以不能加1
            // 2.比如这次进来的是ABA，上一次是AB，那么多出来的这个A和上次AB的共有字符串长度位置(len=0)上的字符是否相等，显然charArr[0] == charArr[2]，所以len++;
            // 3.再比如：这次进来的是ABAB,上一次是ABA,上一次的共有字符串长度是len=1，判断这次多出来的字符B是不是和charArr[1]相等，显然相等，len++;
            if(charArr[i] == charArr[len]){
                len++;
                next[i] = len;
                i++;
            }else{
                // 如果不相等，说明这次多出来的这个字符并没有让共有字符串的长度加1，而且，可能还会直接影响到上一次的共有字符串长度
                // 这里的做法是：因为多出来一个字符，而且charArr[i] != charArr[len]，那这次已经不能拿上一次共有字符串位置上的字符来做比较了，必须拿上上一次的结果
                // 比如：这次进来的是ABABC,上一次是ABAB，上一次共有字符串是AB,len=2,那charArr[2(len)]是A，和这次的多出来的C已经不一样了，那上次的len已经不能作为判断依据了，
                // 必须拿上上一次的len,于是i不变，也就是说下一轮循环还是ABABC，但是len要拿上上一轮的长度，也就是AB这个字符串共有字符串的长度值，len=1，
                // 此时charArr[1(len)]是B，还是和C不相同，说明这次的len还是不能作为判断，于是i继续不变，下一轮还是ABABC，len拿A的共有字符串长度值，len=0，
                // 此时charArr[0(len)]是A，还是和C不相同，说明这次的len还是不能作为判断，理论上还得去那更早一次的len值，但是这时候有个临界情况，因为已经拿到第一次进来的len了，
                // len拿不到更早一次的值了，或者说到这已经没有共有字符串了，说明这次加多出来的字符C。彻底让这个字符串ABABC没有了共有字符串，也就是len=0，可以放心的将这一轮字符串
                // 的共有字符串长度设为0了，这轮len值设置完毕，i++，进行下一轮设置
                if(len > 0){
                    len = next[len-1];
                }else{
                    next[i] = len;
                    i++;
                }
            }
        }
        // 到此，前缀表已经设置完毕，但是有个问题，就是next[0]和next[1]的位置一直都是0，为了后续使用的方便，需要将""和只有一个字符的字符串共有前缀区别开，
        // 而且，对共有字符串来说，前缀表的最后一项就是字符串本身的共有字符串长度，这个在实际使用的时候没有意义，所以直接将整个前缀表往后平移一位，空出来的
        // next[0]赋值为-1
        for (int j = next.length  -1; j > 0; j--) {
            next[j] = next[j-1];
        }
        next[0] = -1;
//        for (int m = 0; m < next.length; m++) {
//            System.out.print(next[m] + "");
//        }
        return next;
    }

    public static int[] getNext1(String str){
        int[] next = new int[str.length()];
        int len = 0;//定义公共前缀长度
        next[0] = 0;//遍历子串，从1开始，0一定为0
        for(int i=1 ;i<str.length();){ //不写i++，不一定每次i会增长，有可能到某个子串时，需要匹配多次
            //等于情况，next[i]的值等于当前len+1，
            //每次比较时，只用比较当前是否相当，如果相等，next[i] = next[i-1] + 1,而此时 len = next[i-1]
            if(str.charAt(i) == str.charAt(len)){
                len++;
                next[i] = len;
                i++;
            }else{//不等于情况
                if(len > 0){//前面尚存公共子串,那就往前推(获取next[len-1])，即获取当前最大公共子串，前缀的最大公共子串，让str.charAt(i)与str.charAt(next[len-1]) 比较
                    len = next[len-1];
                }else{
                    next[i] = 0;
                    i++;
                }
            }
        }
        //坐标增加一，每一次比较未匹配上，取得应该是初当前位之前的最大公共子串，
        for(int i = str.length() -1;i >0;i--){
            next[i] = next[i-1];
        }
        //第一位比较不等时，没有移位必要，应当源字符串后移一位,此处标记 -1
        next[0] = -1;
        return next;
    }

    public static int indexOf(String strSource,String strTarget){
        if(strTarget == null){
            return -1;
        }
        if(strTarget.length() == 0){
            return 0;
        }
        int sourceLength = strSource.length();
        int targetLength = strTarget.length();
        if(sourceLength < targetLength){
            return -1;
        }
        //构建next[]
        int[] next = getNext1(strTarget);
        for(int i=0,y = 0;i< sourceLength;){
            //遍历比较target
            while (strSource.charAt(i) == strTarget.charAt(y)){
                if(y == targetLength -1){
                    return i-y;
                }
                i++;
                y++;
                if(i == sourceLength){
                    return -1;
                }
            }
            //不等时获取y对应的回归位置
            y=next[y];
            if(y == -1){//当为-1，首位不等，代表source指针右移，y从0开始
                i++;
                y = 0;
            }
        }
        return -1;
    }


    /**
     * 病毒感染检测
     *
     * 因为患者的DNA和病毒DNA均是由一些字母组成的字符串序列，
     * 要检测某种病毒DNA序列是否在患者的DNA序列中出现过，实际上就是字符串的模式匹配问题。
     * 可以利用BF算法，也可以利用更高效的KMP算法。
     * 但与一般的模式匹配问题不同的是，此案例中病毒的DNA序列是环状的，这样需要对传统的BF算法或KMP算法进行改进。
     * @param args
     */


    /**
     * 写一个算法统计在输入字符串中各个不同字符出现的频度并将结果存入文件（字符串中的合法字符为A～Z这26个字母和0～9这10个数字）。
     *
     * // A-Z 65-90  a-z 97-122  0-9 48-57
     * @param
     */
    public static int[] getCharNum(String str){
        int[] result = new int[122];
        for(char i : str.toCharArray()){
            result[i] ++;
        }
        return result;
    }

    public static void getCharNum(){
        int[] charNum = getCharNum("123213ACVSTSDF");
        for(int i=0;i<=122;i++){
            if(charNum[i] == 0){
                continue;
            }
            char n = (char) i;
            if(48 <= i && i<= 57){
                System.out.println(n+"个数为"+charNum[i]);
            }
            if(65 <= i && i<= 90){
                System.out.println(n+"个数为"+charNum[i]);
            }
            if(97 <= i && i<= 122){
                System.out.println(n+"个数为"+charNum[i]);
            }
        }
    }

    /**
     * 写一个递归算法来实现字符串逆序存储。
     * @param
     */
    public static String reverse(String str){
        if(str.length() == 1){
            return str;
        }else{
            return reverse(str.substring(1))+str.charAt(0);
        }
    }





    public static void main(String[] args) {

        System.out.println(Collections.singleton(123));

/*        String[] strs = {"cir","car"};
        System.out.println(longestCommonPrefix(strs));*/

/*        System.out.println(longestPalindrome("aacabdkacaa"));*/

        /*System.out.println(reverseWords("a good   example"));*/

/*        System.out.println("".length());
        //[-1, 0, 1, 0, 0, 0, 1, 2]
        System.out.println(Arrays.toString(getNext1("AATPGAAY")));*/

        System.out.println(indexOf("mississippi","issipi"));
    }
}
