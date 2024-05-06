package com.train.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumTrain {

    /**
     * 给你一个整数 n ，找出从 1 到 n 各个整数的 Fizz Buzz 表示，并用字符串数组 answer（下标从 1 开始）返回结果，其中：
     *
     * answer[i] == "FizzBuzz" 如果 i 同时是 3 和 5 的倍数。
     * answer[i] == "Fizz" 如果 i 是 3 的倍数。
     * answer[i] == "Buzz" 如果 i 是 5 的倍数。
     * answer[i] == i （以字符串形式）如果上述条件全不满足。
     */
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for(int i=0; i<n ;i++){
            if((i+1) % 15 == 0){
                result.add("FizzBuzz");
                continue;
            }
            if((i+1) % 3 == 0){
                result.add("Fizz");
                continue;
            }
            if((i+1) % 5 == 0){
                result.add("Buzz");
                continue;
            }
            result.add(i+1+"");
        }
        return result;
    }

    /**
     * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
     * 质数是指在大于1的自然数中，除了1和它本身以外不再有其他因数的自然数
     *
     * 核心要点：判断一个数为质数如何进行优化算法
     *
     * 方法二：埃氏筛
     * 枚举没有考虑到数与数的关联性，因此难以再继续优化时间复杂度。接下来我们介绍一个常见的算法，该算法由希腊数学家厄拉多塞（\rm EratosthenesEratosthenes）提出，称为厄拉多塞筛法，简称埃氏筛。
     *
     * 我们考虑这样一个事实：如果 xx 是质数，那么大于 xx 的 xx 的倍数 2x,3x,… 一定不是质数，因此我们可以从这里入手。
     *
     * 我们设 isPrime[i] 表示数 ii 是不是质数，如果是质数则为 11，否则为 00。从小到大遍历每个数，如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身），即 00，这样在运行结束的时候我们即能知道质数的个数。
     *
     * 这种方法的正确性是比较显然的：这种方法显然不会将质数标记成合数；另一方面，当从小到大遍历到数 xx 时，倘若它是合数，则它一定是某个小于 xx 的质数 yy 的整数倍，故根据此方法的步骤，我们在遍历到 yy 时，就一定会在此时将 xx 标记为 \textit{isPrime}[x]=0isPrime[x]=0。因此，这种方法也不会将合数标记为质数。
     *
     * 当然这里还可以继续优化，对于一个质数 xx，如果按上文说的我们从 2x2x 开始标记其实是冗余的，应该直接从 x\cdot xx⋅x 开始标记，因为 2x,3x,\ldots2x,3x,… 这些数一定在 xx 之前就被其他数的倍数标记过了，例如 22 的所有倍数，33 的所有倍数等。
     *
     *
     * 优化思想：讲所有数字标记为质数，然后从2开始，对于其倍数，标记为合数（非质数）
     */
    public int countPrimes(int n) {
        int count =0;
        //保存合数
        boolean[] isPrimes = new boolean[n];
        //默认都为质数
        Arrays.fill(isPrimes,true);
        for(int i=2;i<n;i++){
            if(isPrimes[i]){
                count++;
                //标记这个数的所有倍数为合数，即false
                int x = i;
                while ((long) x*i < n){
                    isPrimes[x*i] = false;
                    x++;
                }
            }
        }
        return count;
    }

    /**
     * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
     *
     * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
     *
     *
     * 原始思路：循环判断3的x次方是否等于
     * 如果这么想，肯定就被淘汰了
     *
     * 优化思路：
     * 题中n的范围是-2^31 <= n <= 2^31 - 1，而在这个范围内3的最大幂是1162261467，
     * 在比他大就超过int表示的范围了，我们直接用它对n求余即可，过求余的结果是0，说明n是3的幂次方

     * @param n
     */
    public boolean isPowerOfThree(int n) {
        if(n > 0 && 1162261467 % n == 0){
            return true;
        }
        return false;
    }

    /**
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个罗马数字，将其转换成整数。
     *
     *
     * 优化：
     * 处理组合情况,对于所有组合情况转换为另外一个未使用过的字符，并设置对应值
     *
     * 通过数组方式，定义
     * int['M'] = 1000
     * int['D'] = 500
     * 等等
     *
     * 获取时，通过字符直接获取
     *
     *
     * @param s
     */
    public int romanToInt(String s) {
        int sum = 0;
        int[] nums = new int[256];
        nums['I'] = 1;
        nums['V'] = 5;
        nums['X'] = 10;
        nums['L'] = 50;
        nums['C'] = 100;
        nums['D'] = 500;
        nums['M'] = 1000;

        nums['a'] = 4;
        nums['b'] = 9;
        nums['c'] = 40;
        nums['d'] = 90;
        nums['e'] = 400;
        nums['f'] = 900;

        s = s.replaceAll("IV","a").replaceAll("IX","b")
                .replaceAll("XL","c").replaceAll("XC","d")
                .replaceAll("CD","e").replaceAll("CM","f");
        for(char i : s.toCharArray()){
            sum += nums[i];
        }
        return sum;
    }

    public int romanToInt1(String s) {
        int sum = 0;
        //先处理特殊情况 IV/IX/XL/XC/CD/CM
        if(s.contains("IV")){
            s = s.replaceAll("IV","");
            sum += 4;
        }
        if(s.contains("IX")){
            s = s.replaceAll("IX","");
            sum += 9;
        }
        if(s.contains("XL")){
            s = s.replaceAll("XL","");
            sum += 40;
        }
        if(s.contains("XC")){
            s = s.replaceAll("XC","");
            sum += 90;
        }
        if(s.contains("CD")){
            s = s.replaceAll("CD","");
            sum += 400;
        }
        if(s.contains("CM")){
            s = s.replaceAll("CM","");
            sum += 900;
        }
        char[] chars = s.toCharArray();
        for(char i : chars){
            switch (i){
                case 'I':
                    sum += 1;
                    break;
                case 'V':
                    sum += 5;
                    break;
                case 'X':
                    sum += 10;
                    break;
                case 'L':
                    sum += 50;
                    break;
                case 'C':
                    sum += 100;
                    break;
                case 'D':
                    sum += 500;
                    break;
                case 'M':
                    sum += 1000;
                    break;
            }
        }
        return sum;
    }








    public static void main(String[] args) {



        //numTrain.romanToInt("MCMXCIV");
//        System.out.println(numTrain.countPrimes(499979));;

    }
}
