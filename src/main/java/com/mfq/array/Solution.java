package com.mfq.array;


import java.util.*;
import java.util.stream.Collectors;

/**
 *打乱数组
 *给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。打乱后，数组的所有排列应该是 等可能 的。
 *
 *实现 Solution class:
 *
 *Solution(int[] nums) 使用整数数组 nums 初始化对象
 *int[] reset() 重设数组到它的初始状态并返回
 *int[] shuffle() 返回数组随机打乱后的结果
 */
public class Solution {


    public int[] firstNum;

    /**
     * 使用整数数组 nums 初始化对象
     * @param nums
     */
    public Solution(int[] nums) {
        firstNum = nums;
    }

    /**
     * 重设数组到它的初始状态并返回
     * @return
     */
    public int[] reset() {
        return firstNum;
    }

    /**
     * 返回数组随机打乱后的结果
     *
     * 思路：所有打乱的可能性都是均衡的，本身也作为一种打乱，返回取其一
     * 问题：如何获取所有打乱的结果
     *
     * 从数组中随机取一个元素作为第一个，再随机取一个作为第二个，，，依次类推直至取完
     *
     * Fisher-Yates洗牌算法：打乱的含义是：等概率的产生所有的排列情况，即：每个元素出现在每个位置的概率相等。因此，算法的步骤为：
     * 循环n次，在第i次循环中（0≤i<n）：
     *
     * 在[i,n)中随机抽取一个下标j；
     * 将第i个元素与第j个元素交换
     *
     * 优化：便于取随机数，对于每一个元素，与[0,i]进行交换,可以推导每个元素出现再每个位置的概率依旧是1/n
     *
     *
     *
     * @return
     */
    public int[] shuffle() {
        int[] nums = firstNum.clone();
        Random random = new Random();
        for(int i=1;i<firstNum.length;i++){
            int j = random.nextInt(i+1);
            if(i!=j){
                nums[i] ^= nums[j];
                nums[j] ^= nums[i];
                nums[i] ^= nums[j];
            }
        }
        return nums;
    }

    public void swap(int[] nums,int i,int j){
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }

    public void swap1(int[] nums,int i,int j){
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution solution = new Solution(nums);
        int[] shuffle = solution.shuffle();
        System.out.println(Arrays.toString(shuffle));

    }

}
