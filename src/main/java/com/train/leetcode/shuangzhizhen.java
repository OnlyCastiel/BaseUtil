package com.train.leetcode;

import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class shuangzhizhen {

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     *
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * @param s
     */
    public static void reverseString(char[] s) {
        char temp;
        for(int i=0;i<s.length-i-1;i++){
            temp = s[s.length-i-1];
            s[s.length-i-1] = s[i];
            s[i] = temp;
        }
    }

    /**
     * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
     *
     * 返回该 最大总和 。
     *
     * 解题思路：进行排序，排序完成后，依次两两组队
     * @param nums
     */
    public static int arrayPairSum(int[] nums) {
        //冒泡排序,每一次遍历都从0开始，到length-i-1结束，一共进过length次遍历
        int temp;
        for(int i =0;i<nums.length ;i++){
            for(int y=0;y<nums.length - i -1;y++){
                if(nums[y] > nums[y+1]){
                    temp = nums[y+1];
                    nums[y+1] = nums[y];
                    nums[y] = temp;
                }
            }
        }
        //或者直接排序，方法会根据数据规模选择合适的排序算法
        //Arrays.sort(nums);

        //排序完毕，进行二分，直接获取数据的总和
        int sum =0;
        for(int i=0;i<nums.length;i+=2){
            sum+=nums[i];
        }
        return sum;
    }

    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * @param args
     */
    public static int removeElement(int[] nums, int val) {
        int index = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != val){
                nums[index] = nums[i];
                index ++;
            }
        }
        return index;
    }

    /**
     * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
     *
     * 核心要点，快慢指针
     * @param
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int slow;
        for(int i=0;i<nums.length;i++){
            while (i<nums.length && nums[i] ==1){
                slow = i;
                while (i<nums.length && nums[i] == 1 ){
                    i++;
                }
                max = Math.max(max,i-slow);
            }
        }
        return max;
    }

    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     *
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     *
     * 核心要点：滑动窗口，快指针滑动到总和大于target后，慢指针右移动，但凡可以移动，代表有遗弃的价值；
     * 一直到总和小于target时，记录min值，为fast-slow+1; 然后重新移动fast指针，依次往复
     * @param
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int min = nums.length + 1;
        int sum = 0;
        int slow =0;
        int fast =0;
        while (fast<nums.length){
            sum += nums[fast];
            if(sum >= target){
                while (sum >= target && slow <= fast){
                    //移动慢指针遗弃
                    sum -= nums[slow];
                    slow++;
                }
                //移动到小于target
                min = Math.min(min,fast-slow+2);
            }
            //fast位置右移
            fast ++;
        }
        if(min == nums.length + 1){
            return 0;
        }
        return min;
    }


    /**
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     *
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     * @param numRows
     * @return
     */
    public static  List<List<Integer>> generate(int numRows) {
        if(numRows <=0) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0;i<numRows;i++){
            List<Integer> rowList = new ArrayList<>();
            for(int len=0;len<i+1;len++){
                //计算父位置，slow[len-1],slow[len]
                if(len==0 || len == i){
                    rowList.add(1);
                }else{
                    rowList.add(result.get(i-1).get(len-1) + result.get(i-1).get(len));
                }
            }
            result.add(rowList);
        }
        return result;
    }

    /**
     * 给定一个非负整数 numRows，生成「杨辉三角」的第 numRows 行。
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     *
     * 关键核心：优化空间复杂度到 O(rowIndex)
     *
     * n行最多有n+1个元素（从 0 开始编号）
     * 第 n 行的第 m 个数（从 0 开始编号）可表示为可以被表示为组合数 C(n,m)，
     * c(n,m) = c(n,m-1) * (n-m+1)/m
     * @param rowIndex
     * @return
     */
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }

    public static String reverseWords(String s) {
        String[] s1 = s.split(" ");
        for(int i=0;i< s1.length ;i++){
            char[] chars = s1[i].toCharArray();
            reverseString(chars);
            s1[i] = String.valueOf(chars);
        }
        return String.join(" ",s1);
    }


    /**
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     *
     * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     *
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     *
     * 核心要点：时间复杂度限定了使用2分搜索，那么在二分后找到降序V行部分，即为最小值所在区间，并不断缩小范围
     * @param nums
     */
    public static int findMin(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        while (start < end){
            int mid = (start+end)/2;
            if(nums[mid] > nums[end]){//找降序，最小值一定在非升队列中
                start = mid + 1; //此时mid可以不要
            }else{
                end =  mid;
            }
        }
        return nums[start];
    }


    /**
     * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
     *
     * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
     *
     * 将最终结果插入 nums 的前 k 个位置后返回 k 。
     *
     * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * 思考方式： 升序决定，上一个元素相同即为相同，无需判断多次
     * @param nums
     */
    public static int removeDuplicates(int[] nums) {
        int slow = 0;//真实
        int fast =1;
        for (;fast < nums.length;fast++){
            if(nums[slow] != nums[fast]){
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow;
    }

    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * 核心思路：比较之前两个数，
     */
    public static int removeDuplicates2(int[] nums) {
        int slow = 1;//真实
        int fast =2;
        for (;fast < nums.length;fast++){
            if(nums[slow-1] != nums[fast]){
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow+1;
    }

    /**
     * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     * 你所设计的解决方案必须只使用常量级的额外空间
     *
     * 核心思路：双指针，二分查找
     *
     * 优化后核心思路：双指针， 一首一尾；
     * 1、当首尾之和小于目标值（没有），返回-1
     * 2、当首尾之和小于目标值，需要增大，首部右移
     * 3、当首尾之和大于目标值，需要见效，尾部左移
     *
     * @param
     */
    public static int[] twoSum(int[] numbers, int target) {
        int[] result = {-1,-1};
        int slow =0,fast =numbers.length-1;
        int sum;
        while (slow< fast){
            sum = numbers[slow] + numbers[fast];
            if(target == sum){
                result[0] = slow+1;
                result[1] = fast+1;
                return result;
            }
            if(target > sum){
                slow++;
            }else{
                fast--;
            }
        }
        return result;
    }

    /**
     * 有序数组进行二分查找
     * @param nums
     * @param from
     * @param end
     * @param target
     * @return
     */
    public static int findNum(int[] nums,int from,int end,int target){
        while (from <= end) {
            int mid = (from + end) / 2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[mid] < target){
                from = mid +1;
            }else{
                end = mid+1;
            }
        }
        return -1;
    }




    public static void main(String[] args) {
/*        char[] s = {'H','a','n','n','a','h'};
        reverseString(s);
        System.out.println(Arrays.toString(s));*/

        /*System.out.println(arrayPairSum(new int[]{6,2,6,5,1,2}));*/

/*        System.out.println(generate(5));*/


        /*System.out.println(findMin(new int[]{11,13,15,17}));*/

/*        int[] a = {1,1,2};
        removeDuplicates(a);
        System.out.println(Arrays.toString(a));*/


        int[] a = {2,7,11,15};
        System.out.println(findNum(a,0,a.length-1,11));
    }

}
