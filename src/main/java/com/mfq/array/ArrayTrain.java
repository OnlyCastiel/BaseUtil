package com.mfq.array;

import java.util.Arrays;

/**
 * 数组练习
 */
public class ArrayTrain {

    /**
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     *
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     *
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。
     * 为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     *
     * 思路：降低空间复杂度，复制所有0-m元素到n~m+n，即复制到后半段
     *
     * 思路2：降低时间复杂度情况，开辟新的一个长度为m+n的数组
     */

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //复制替换
        for(int i=m+n-1;i>=n;i--){
            nums1[i] = nums1[i-n];
        }
        //递归
        int i =n;
        int j =0;
        int index = 0;
        while (index < m+n){
            if(i<m+n && j<n){
                //比较
                if(nums1[i] < nums2[j]){
                    nums1[index] = nums1[i];
                    index++;
                    i++;
                }else{
                    nums1[index] = nums2[j];
                    index++;
                    j++;
                }
            }else{
                if(i<m+n){
                    for(;i<m+n;index++,i++){
                        nums1[index] = nums1[i];
                    }
                }
                if(j<n){
                    for(;j<n;index++,j++){
                        nums1[index] = nums2[j];
                    }
                }
            }
        }
    }

    /**
     * 第一个错误的版本
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
     * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     *
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     *
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version x是否在单元测试中出错。
     * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     *
     * 核心思路：二分查找确定范围.找转折
     * @param
     */
    public int firstBadVersion(int n) {
        return findFirstBad(0,n);
    }

    public int findFirstBad(int start,int end){
        if(start >= end){//定义出口
            return end;
        }
        //int mid = (start+end)/2;//此写法有可能溢出
        int mid = start + (end-start)/2;
        if(isBadVersion(mid)){
            return findFirstBad(start,mid);
        }else {
            return findFirstBad(mid+1,end);
        }
    }

    boolean isBadVersion(int n){
        if(n>=4){
            return true;
        }
        return false;
    }


    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 核心思路：
     * 由于每次可以爬1层或爬两层,那么当你站在n阶的时候，
     * 此时最后一次爬可能是1个或者2个台阶，而此刻总方案为 爬到n-1的总方案数 与 爬到n-2的总方案数
     * 那么对于爬楼层方案 F（n） = F（n-1） + F (n-2)
     * 效率问题：产生了重复计算
     *
     * 优化：改为非递归方法，借助数组
     *
     * @param args
     */
    public int climbStairs(int n) {
        //定义出口
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        return climbStairs(n-1)+climbStairs(n-2);
    }

    public int climbStairs1(int n) {
        int[] nums = new int[n+1];
        //定义出口
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        nums[0] = 0;
        nums[1] = 1;
        nums[2] = 2;
        for(int i=3;i<n+1;i++){
            nums[i] = nums[i-1] + nums[i-2];
        }
        return nums[n];
    }


    /**
     * 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * 贪心算法，对于每一天的股票价格，减去之前的最低价格，即为当前日期卖出的最大获利；
     * 最后所有日期的最大获利的最大值，即为最大利润
     * @param prices
     */
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int max = 0;
        for(int i=1;i<prices.length;i++){
            if(prices[i] > min && prices[i] - min > max){
                max = prices[i] - min;
            }
            if(min > prices[i]){
                min = prices[i];
            }
        }
        return max;
    }


    /**
     *
     * 最大子序和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 子数组 是数组中的一个连续部分。
     *
     * 通过分析观察！！！
     * 核心思路：假设以任意一个数字为结尾,此时最大值可能为正数，可能为负数
     * 当为正数时，以下一个数字为结尾的子数组则直接加上下一个值；
     * 如果为负数，则以下一个数字为结尾的最大子数组为其本身
     * @param nums
     */
    public int maxSubArray(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int max = nums[0];
        int sum = nums[0];
        for(int i = 1;i<nums.length;i++){
            if(sum > 0){
                sum += nums[i];
            }else{
                sum = nums[i];
            }
            if(sum > max){
               max = sum;
            }
        }
        return max;
    }

    /**
     * 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * 动态规划：穷举所有可能性，
     * 1、规则是若选择某一户偷，则其相邻的两户不能偷
     * 2、遍历数组，在每一户处，当其上一户没有偷时，可以选择
     * 状态转移方程
     * F(n) = Math.max(num[n]+F(n-2),F(n-1));
     * 问题，可能存在超时
     *
     * 正向思路优化：会发现对于偷到每一户，当前最大值只与前面一户的状态有关
     * 当前户偷 = 上一户不偷 + 当前户
     * 当前户不偷 = MAX（上一户不偷 , 上一户偷了）
     * @param nums
     */
    public int rob(int[] nums) {
        //记录每一户偷/没偷的最大值
        int max[][] = new int[nums.length][2];
        //初始化,第一户
        max[0][0] = 0;
        max[0][1] = nums[0];
        //从第一户开始
        for(int i =1;i<nums.length;i++){
            max[i][0] = Math.max(max[i-1][0],max[i-1][1]);
            max[i][1] = nums[i] + max[i-1][0];
        }
        return Math.max(max[nums.length-1][0],max[nums.length-1][1]);
    }

    /**
     * 上题进一步优化，空间优化
     * 对于状态保持来说，其实只用维持上一户的偷与不偷的最大值即可
     */
    public int rob1(int[] nums) {
        //初始化,第一户
        int noRob = 0;
        int rob = nums[0];
        int temp = 0;
        //从第一户开始
        for(int i =1;i<nums.length;i++){
            temp = noRob;
            noRob = Math.max(noRob,rob);
            rob = nums[i] + temp;
        }
        return Math.max(noRob,rob);
    }

    /**
     * 逆向状态转移--不是最优解，占用大量临时空间
     * @return
     */
    public int robTotal(int[] nums,int n){
        if(n<0){
            return 0;
        }
        return Math.max(nums[n]+robTotal(nums,n-2),robTotal(nums,n-1));
    }






    public static void main(String[] args) {
        ArrayTrain train = new ArrayTrain();
//        int[] num1 = {1,2,4,5,6,0};
//        int[] num2 = {3};
//        train.merge(num1,5,num2,1);
//        System.out.println(Arrays.toString(num1));

        int[] nums = {114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240};
        //int[] nums = {2,7,9,3,1};
        System.out.println(train.rob(nums));

    }
}
