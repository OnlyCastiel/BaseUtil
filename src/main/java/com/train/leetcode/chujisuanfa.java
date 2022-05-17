package com.train.leetcode;

import java.math.BigDecimal;
import java.util.*;

public class chujisuanfa {


    /**
     * 买卖股票的最佳时机 II
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     *
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     *
     * 返回 你能获得的 最大 利润 。
     *
     * 核心要点：获取曲线中所有递增段
     * 相关标签:贪心/数组/动态规划
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int sum = 0;
/*        int temp = prices[0];
        //默认状态递增: 问题，当曲线无转折时，将出错
        boolean flag = true;
        for(int i = 1;i<prices.length ;i++){
            //判断转折，递增变递减计算sum,
            if(flag && prices[i] < prices[i-1]){
                sum += prices[i-1] - temp;
                flag = false;
                continue;
            }
            //递减变递增记录temp
            if(!flag && prices[i] > prices[i-1]){
                temp = prices[i-1];
                flag = true;
            }
            //其他情况跳过
        }*/

        //优化调整思路，只要增加，则计入sum
        for(int i = 1; i< prices.length;i++){
            if(prices[i] > prices[i-1]){
                sum += prices[i] - prices[i-1];
            }
        }
        return sum;
    }


    /**
     * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     *
     * 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
     * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
     *
     * [0,k-1] 范围内，依次元素移动K， temp = num[i+k]  num[i+k] = num[i];
     * 从K位开始，读取到新数组
     * O(1) : 循环右移1位，移动K轮
     * 数组反转：0~K进行再度反转，K~end进行再度反转
     */
    public void rotate(int[] nums, int k) {
    }


    /**
     * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；
     * 如果数组中每个元素互不相同，返回 false 。
     *
     * O（n2）思路：判断任意数字不重复--对每一个元素，比较它之前的所有，遇见相同则返回true
     *
     *
     */
    public boolean containsDuplicate(int[] nums) {
        //遍历实现
        /*for(int i = 1;i< nums.length ;i++){
            for(int j=0;j<i;j++){
                if(nums[j] == nums[i]){
                    return true;
                }
            }
        }
        return false;*/

        //哈希表实现
/*        HashSet<Integer> set = new HashSet<>();
        for(int i =0;i<nums.length;i++){
            if(set.contains(nums[i])){
                return true;
            }else{
                set.add(nums[i]);
            }
        }*/

        //排序实现
        Arrays.sort(nums);
        for(int i = 1;i<nums.length ;i++){
            if(nums[i] == nums[i-1]){
                return true;
            }
        }
        return false;
    }


    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 排序加一次遍历，也无法实现，线性时间复杂度
     *
     * 核心要点：位运算，
     * 使用异或运算，将所有值进行异或
     * 异或运算，相异为真，相同为假，所以 a^a = 0 ;0^a = a
     * 因为异或运算 满足交换律 a^b^a = a^a^b = b 所以数组经过异或运算，单独的值就剩下了
     *
     *
     * 升阶：当如果有两个元素仅出现一次，如何快速找到这两个数
     */
    public int singleNumber(int[] nums) {
/*        Arrays.sort(nums);
        for(int i = 0;i<nums.length;i+=2){
            if(i+1 == nums.length || nums[i+1] != nums[i]){
                return nums[i];
            }
        }
        return -1;*/

        //位运算-异或
        int result = 0;
        for(int num :nums){
            result = result ^ num;
        }
        return result;
    }

    /**
     * 升阶：当如果有两个元素仅出现一次，其他都出现两次，如何快速找到这两个数
     *
     * 对于这种现象,假设这两个元素为A、B,则定有 A ^ B != 0
     * 最终所有元素取异或得到的结果，为  A ^ B ;但此时无法找到A，B的值
     *
     * 不过根据 A ^ B 的结果，我们可以知道这两个值在二进制某一位上不同，一个为1，一个为0
     *  找到一位为1,构造一个 100000的二进制数，通过该数与原数组取 & ，分为两类
     * 对于所有的元素，可以分为两组，一组在此位上为1，一组在此位上为0； A，B分别在这两组中，
     * 则，最这两组数据各自取  ^ 运算，即可得到最终结果
     */

    public static void singleNumber1(int[] nums) {
        //位运算
        int result = 0;
        for(int num :nums){
            result = result ^ num;
        }
        String s = Integer.toBinaryString(result);
        int length = s.length();
        int index = s.indexOf("1");
        //找到一位为1,构造一个 100000的二进制，通过改数与原数组取 & ，分为两类
        //这一位距离末位长度
        int x = 1 << length - index - 1;

        int[] classOne = new int[nums.length];
        int[] classTwo = new int[nums.length];
        int oneIndex = 0;
        int twoIndex = 0;
        for(int num : nums){
            if ((num & x) > 0){
                classOne[oneIndex] = num;
                oneIndex ++;
            }else{
                classTwo[twoIndex] = num;
                twoIndex ++;
            }
        }
        //B元素
        result = 0;
        for(int num:classOne){
            result = result ^ num;
        }
        System.out.println(result);
        //B元素
        result = 0;
        for(int num:classTwo){
            result = result ^ num;
        }
        System.out.println(result);
    }



    /**
     *     给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，
     *     应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
     *
     *
     * 相关标签:数组/哈希表/双指针/二分查找/排序
     *
     * 进阶追问：
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？ --双指针
     * 如果 nums1 的大小比 nums2 小，哪种方法更优？ --哈希表
     * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？ --nums1哈希表
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
/*        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> result = new ArrayList<>();

        int i=0;
        int j=0;
        int len1 = nums1.length;
        int len2 = nums2.length;
        while (i < len1 && j < len2){
            if(nums1[i] == nums2[j]){
                result.add(nums1[i]);
                i++;
                j++;
            }else{
                //不同情况比较大小，判断某一个移位
                if(nums1[i] < nums2[j]){
                    i++;
                }else{
                    j++;
                }
            }
        }
        return result.stream().mapToInt(elem->elem).toArray();*/


        // nums1 的大小比 nums2 小，哪种方法更优？ --哈希表
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i =0;i<nums1.length ;i++){
            if(map.containsKey(nums1[i])){
                map.replace(nums1[i],map.get(nums1[i]) +1);
            }else{
                map.put(nums1[i],1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i =0;i< nums2.length ;i++){
            if(map.containsKey(nums2[i])){
                Integer count = map.get(nums2[i]);
                if(count == null) continue;
                if(count > 1){
                    map.replace(nums2[i],map.get(nums2[i]) -1);
                }else{
                    map.remove(nums2[i]);
                }
                result.add(nums2[i]);
            }
        }
        return result.stream().mapToInt(elem->elem).toArray();
    }


    /**
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     *
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     *
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * @param args
     */
    public static int[] plusOne(int[] digits) {
        int length = digits.length;
        for(int i=length-1;i>=0;i--){
            if(digits[i]!=9){
                digits[i]++;
                return digits;
            }else{
                digits[i] = 0;
            }
        }
        int temp[] = new int[length+1];
        temp[0] = 1;
        return temp;
    }


    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int index = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0){
                nums[index] = nums[i];
                index ++;
            }
        }
        for(int i=index;i<nums.length;i++){
            nums[i]=0;
        }
    }

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     *
     * 你可以按任意顺序返回答案。
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
/*        Map<Integer,Integer> map= new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            Integer locate = map.get(target - nums[i]);
            if(locate != null && locate.intValue() != i){
                return new int[]{i,locate};
            }
        }
        return nums;*/

        //优化，判断不存在后再放入map
        Map<Integer,Integer> map= new HashMap<>();
        for(int i =0;i<nums.length;i++){
            if(map.get(target - nums[i]) != null){
                return new int[]{map.get(target - nums[i]),i};
            }else{
                map.put(nums[i],i);
            }
        }
        return new int[]{0,0};
    }

    /**
     * 两数问题之和升阶，三数之和，
     *
     * 核心思路：复杂问题分解为可解的简单问题
     * 1、遍历数组，固定元素A，对数组中剩余元素计算，两数之和为target-A的两个元素
     * @param args
     */


    /**
     * 逆序总数:对于1个数组，求其中逆序总数
     *
     * 什么是逆序数: 在一个排列中,如果一对数的前后位置与大小顺序相反,即前面的数大于后面的数,那么它们就称为一个逆序。
     *
     * 方法一：双层遍历，比较大小
     * 方法二：采用归并排序的改良版求取逆序数，归并过程中，维护逆序数值
     */
    //方法一：双层遍历，时间复杂度 O（n*n）
    private int getNiXuNum(int[] nums){
        int count = 0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i] > nums[j]){
                    count ++;
                }
            }
        }
        return count;
    }

    /**
     * 方法二：归并排序
     *
     */
    private static int getNiXuNum(int[] nums,int start,int end){
        //递归出口
        if(start == end){
            return 0;
        }
        if(start == end - 1){//调整顺序，为1
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            return 1;
        }
        int mid = (start + end)/2;
        int numA = getNiXuNum(nums,start,mid);
        int numB = getNiXuNum(nums,mid + 1, end);
        //对两段序列进行归并，计算归并中的逆序值
        int countAB = 0;
        for(int i = mid+1;i<=end;i++){//用mid+1，到end段，比较前半段，获取逆序值
            while (nums[i] > nums[start]){
                start ++;
            }
            countAB += mid - start + 1;
        }
        return numA + numB + countAB;
    }



    /**
     *     请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     *
     *     数字 1-9 在每一行只能出现一次。
     *     数字 1-9 在每一列只能出现一次。
     *     数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     */
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    columns[j][index]++;
                    subboxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }



    /**
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     *
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     *
     * 假设环境不允许存储 64 位整数（有符号或无符号）。---不允许用整数比较大小
     */
    public static int reverse(int x) {
        String s = String.valueOf(x);
        String result = null;
        String compare = null;
        if(x < 0){//正负号
            result = "-" + new StringBuffer(s.substring(1)).reverse().toString();
            compare = String.valueOf(Integer.MIN_VALUE);
        }else{
            result = new StringBuffer(s).reverse().toString();
            compare = String.valueOf(Integer.MAX_VALUE);
        }
        //判断越界，字符串比较,比较长度
        if(result.length() >= compare.length() && result.compareTo(compare) > 0){
            return 0;
        }else{
            return Integer.valueOf(result);
        }
    }

    /**
     * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
     *
     * 核心要点：
     * 1、返回索引则要求不可排序
     * 2、不重复，从此元素往后找，没有相同元素，找到第一个
     *
     * 可以使用额外空间：找第一个：需要结果有序（原字符串顺序）；判断不重复：需要遍历所有
     * 1、hashMap计数，然后遍历原字符串，取到第一个value=1的返回index
     * 2、使用数组，数组index=char[i],再遍历原字符串，获取到第一个value=1的返回原数组index
     *
     * 不可使用额外空间：遍历字符，每一个字符比较后续所有字符,可能出现问题，当一个重复字符的第二个，比较后续字符是不重复出现的
     * 可使用，lastIndexOf 是否相等 indexOf
     * @param
     */
    public static int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        int[] charNum = new int[256];//ascii范围，如果字符范围更大，需要调整数组长度支持
        for(char a : chars){
            charNum[a] ++;
        }
        for(int i=0;i<chars.length ;i++){
            if(charNum[chars[i]] == 1){
                return i;
            }
        }
        return -1;
    }


    /**
     * 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     *
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     *
     * 核心要点：通过sNUM = int[256]  tNUM = int[256] 存储每个字符出现的次数，长度需要根据S/T的字符范围；
     * 比较两个数组，当出现不同，即非异位词
     * @param args
     */
    public boolean isAnagram(String s, String t) {
        int[] Snum = new int[256];
        int[] Tnum = new int[256];
        char[] Schar = s.toCharArray();
        char[] Tchar = t.toCharArray();
        for(char ss : Schar){
            Snum[ss] ++;
        }
        for(char tt : Tchar){
            Tnum[tt] ++;
        }
        for(int i=0;i<256;i++){
            if(Snum[i] != Tnum[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 核心要点：
     * 1、只考虑字母和数字字符，遍历生成新字符串、双指针，从后往前，从前往后
     * 2、正则匹配后，反转比较
     * 3、双指针，Character.isLetterOrDigit() 跳过无需比较的字符
     *
     * @param args
     */
    public boolean isPalindrome(String s) {
        if(s == null){
            return false;
        }
        StringBuilder sb = new StringBuilder();
        for(char a : s.toLowerCase().toCharArray()){
            if((a >= '0' && a <= '9') || (a >= 'a' && a <= 'z')){
                sb.append(a);
            }
        }
        s = sb.toString();
        if(s.length() <= 1){
            return true;
        }
        for(int i =0;i<s.length()/2 ;i++){
            if(s.charAt(i) != s.charAt(s.length() - i -1)){
                return false;
            }
        }
        return true;
    }


    /**
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     *
     * 函数 myAtoi(string s) 的算法如下：
     *
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * 注意：
     *
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     *
     * 核心要点，1、正则匹配 2、根据步骤一步步做
     * @param args
     */
    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        int index = 0;
        // 先去除空格
        while (index < length && chars[index] == ' '){
            index++;
        }
        // 极端情况 "  " 和""
        if(index >= length){
            return 0;
        }
        // 再判断符号
        int sign =  1;
        if(chars[index] == '-' || chars[index] == '+'){
            if(chars[index] == '-'){
                sign = -1;
            }
            index++;
        }
        int result = 0;
        int temp = 0;
        while (index < length){
            int num = chars[index] - '0';
            if(num > 9 || num < 0){
                break;
            }
            temp = result;
            result = result * 10 + num;
            // 越界后，数值和期望数值发生变化，取余再除10获取原始值，比对判断
            if(result/10 !=temp){
                if(sign > 0){
                    return Integer.MAX_VALUE;
                }else {
                    return Integer.MIN_VALUE;
                }
            }
            index++;
        }
        return result*sign;
    }


    /**
     *     给定一个正整数 n ，输出外观数列的第 n 项。
     *
     *             「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
     *
     *     你可以将其视作是由递归公式定义的数字字符串序列：
     *
     *     countAndSay(1) = "1"
     *     countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
     *     前五项如下：
     *
     *             1.     1
     *             2.     11
     *             3.     21
     *             4.     1211
     *             5.     111221
     *     第一项是数字 1
     *     描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
     *     描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
     *     描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
     *     描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
     *     要 描述 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组都由连续的最多 相同字符 组成。
     *     然后对于每个组，先描述字符的数量，然后描述字符，形成一个描述组。要将描述转换为数字字符串，先将每组中的字符数量用数字替换，再将所有描述组连接起来。
     * @param args
     */
    public static String countAndSay(int n) {
        //核心要点，分类递归
        //countAndSay(n) = countAndSay(countAndSay(n-1))
        String result = "1";
        if(n == 1){
            return result;
        }else{
            return countAndSay(countAndSay(n-1));
        }
    }

    //核心要点：非递归，循环调用x次
    public static String countAndSay(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        int num = -1;
        int count = 0;
        for(int i =0;i<chars.length ;i++){
            if(chars[i] != num){//值变化
                if(count > 0){
                    sb.append(count).append(num-'0');
                }
                num = chars[i];
                count = 1;
            }else{
                count ++;
            }
        }
        //最后一次
        sb.append(count).append(num-'0');
        return sb.toString();
    }


    /**
     * 数组奇偶排序
     *
     * 一个数组，奇数偶数分别排序，奇数在前段，偶数在后段
     *
     * 维护两个指针，一个从前往后是奇数位置，一个从后往前是偶数位置
     * 最终，mid指向偶数的开头
     * 对数组的两段进行分别排序
     * @param args
     */
    private static void sortJO(int[] nums){
        int length = nums.length;
        int ji = 0;
        int ou = length-1;
        while (ji < ou){
            while (nums[ou] % 2 == 0){
                ou --;
            }
            while (nums[ji] % 2 == 1){
                ji --;
            }
            //交换,规避全奇数或偶数
            if(ji < length && ou >= 0){
                int temp = nums[ou];
                nums[ou] = nums[ji];
                nums[ji] = temp;
                ji++;
                ou--;
            }
        }
        System.out.println(ji);


    }


    public static void main(String[] args) {
        for(int i=1;i>0;){

        }

/*        int[] nums = new int[]{6,5,4,3,2,1};
        int niXuNum = getNiXuNum(nums, 0, nums.length - 1);
        System.out.println(niXuNum);*/

/*        int[] nums = new int[]{2,2,6,1,2,2,3,3};
        singleNumber1(nums);*/

/*        int[] nums = new int[]{2,4,6,1,3,4,6};
        sortJO(nums);
        System.out.println(Arrays.toString(nums));*/


//        int[] intersect = intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
//        System.out.println(Arrays.toString(intersect));
//        System.out.println(countAndSay(4));
//        System.out.println(firstUniqChar("aabb"));

       // plusOne(new int[]{1,2,3});
//        System.out.println('5' - '0');
//        System.out.println(1 << ('5' - '0'));
//        twoSum(new int[]{2,1,4},6);
    }














}