package com.train.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class shuzu {


    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * 输入: nums = [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     */


    /**
     * 方法一：双指针
     * 思路及解法: 不断把顺序第一个0和这个0后面第一个非0数换位
     * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
     * 注意到以下性质：
     * 左指针左边均为非零数；
     * 右指针左边直到左指针处均为零。
     * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
     * @param nums
     */
    public static void moveZero1(int[] nums){
        int left=0,right= 0;

        while (right < nums.length){
            if(nums[right] != 0){
                swap(nums,left,right);
                left ++;
            }
            right ++;
        }
    }





    /**
     * 寻找数组的中心索引
     * 给你一个整数数组 nums ，请计算数组的 中心下标 。
     * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
     * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
     * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
     *
     * 需要解决的核心问题，进行累加过程中，当左右两边数值不相等时，移动左边指针，还是右边指针
     * 思维转换：先计算总和，然后遍历计算left总和，如果left总和×2加当前值等于总和，就返回当前值~
     * @param nums
     */
    public static int findCenterIndex(int[] nums){
        int total = 0,leftSum = 0;
        for(int i=0;i<nums.length;i++){
            total += nums[i];
        }
        for(int i=0;i<nums.length;i++){
            if(leftSum * 2 + nums[i] == total){
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 请必须使用时间复杂度为 O(log n) 的算法。 ---二分插入的同时找目标值
     * @param nums
     */
    public static int findCenterIndex(int[] nums,int target){
        int left = 0 ,right = nums.length -1;
        int targetIndex;
        while (left <= right){
            targetIndex = (left + right) / 2;
            if(target == nums[targetIndex]){
                return targetIndex;
            }
            if(target > nums[targetIndex]){
                left = targetIndex + 1;
            }else {
                right = targetIndex - 1;
            }
        }
        return left;
    }


    /**
     *     以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     *     请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     *     示例 1：
     *
     *     输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     *     输出：[[1,6],[8,10],[15,18]]
     *     解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     *     示例 2：
     *
     *     输入：intervals = [[1,4],[4,5]]
     *     输出：[[1,5]]
     *     解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     *     思路：维护一根主轴，数组的每一个区间，在主轴上进行标记，最后输出主轴
     */
    private static int[][] mergeQuJian(int[][] intervals){
        //对数组以首部进行冒泡排序
        //冒泡，每次都从最底下开始冒，每一轮带上一个待排序字段的最大值
        int[] temp;
        for(int i = 0; i < intervals.length ; i++){//冒泡轮次
            for(int y = 0;y < intervals.length-i-1;y++){//每次冒泡待排序范围 0 ~ length-i-1
                if(intervals[y][0] > intervals[y+1][0]){
                    //前面的大，冒泡
                    temp = intervals[y];
                    intervals[y] = intervals[y+1];
                    intervals[y+1] = temp;
                }
            }
        }
        //合并,从第一个开始,i计为当前合入目的地
        int target = 0;
        for (int i=1;i<intervals.length;i++){
            //往target中合并，不能则为新待合入区间，赋值到 target,
            if(intervals[i][0] > intervals[target][1]){
                target++;
                intervals[target] = intervals[i];
            }else{
                //能合情况分为，是否需要替换target右区间
                if(intervals[target][1] < intervals[i][1]){
                    intervals[target][1] = intervals[i][1];
                }
            }
        }
        //截取0-target
        int[][] result = new int[target+1][2];
        for(int i=0;i<target+1;i++){
            result[i] = intervals[i];
        }
        return result;
    }

    private static int[][] mergeQuJian1(int[][] intervals){
        Arrays.sort(intervals,(a,b) -> a[0] - b[0]);
        List<int[]> result = new ArrayList<>();
        int target=0;
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0] > intervals[target][1]){//不能合
                result.add(intervals[target]);
                target ++;
                intervals[target] = intervals[i];
            }else if(intervals[target][1] < intervals[i][1]){
                intervals[target][1] = intervals[i][1];
            }
        }
        result.add(intervals[target]);//最后一个
        return result.toArray(new int[result.size()][2]);
    }


    /**
     * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
     * 不占用额外内存空间能否做到
     *
     *给定 matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     * 原地旋转输入矩阵，使其变为:(顺时针旋转)
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     *
     * 核心要点：四角交换，切分四块内容
     * @param
     */
    public static void rotate(int[][] matrix ){
        //对于每一个坐标 int[i][y] ,其值需要赋值到 int[y][N-i] ;
        //不使用新地址空间，要求在于被覆盖的值有新去处
        //需要旋转的内容为；
        int N = matrix.length-1;
        int temp = 0;
        for(int i =0 ; i < (N+1)/2 ; i++){//需要旋转的行数
            for(int y=0;y< (N+2)/2;y++ ){//需要旋转的列数
                //赋值4次，
                temp = matrix[i][y];
                matrix[i][y] = matrix[N-y][i];
                matrix[N-y][i] = matrix[N-i][N-y];
                matrix[N-i][N-y] = matrix[y][N-i];
                matrix[y][N-i] = temp;
            }
        }
    }

    /**
     * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
     *
     * 核心要点：
     * 1、根据0的位置，记录需要清零的行号和列好，
     * 2、不可以读到0就清，后续遍历会受到影响
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {
        int[] lineZero = new int[matrix.length]; //行0记录
        int[] colZero = new int[matrix[0].length]; //列0记录

        for(int i=0 ;i<matrix.length;i++){
            for(int y=0;y<matrix[0].length;y++){
                if(matrix[i][y] == 0){
                    lineZero[i] = 1;
                    colZero[y] = 1;
                }
            }
        }
        for(int i=0 ;i<matrix.length;i++){
            for(int y=0;y<matrix[0].length;y++){
                if(lineZero[i] > 0 || colZero[y] > 0){
                    matrix[i][y] =0;
                }
            }
        }
    }


    /**
     * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
     *
     * 核心要点：确认每个数组的起点，确认终点，确认边界
     */
    public static int[] findDiagonalOrder(int[][] matrix){
        int line = matrix.length;
        int col = matrix[0].length;
        int[] result = new int[line * col];
        boolean way = true;//初始方向向上
        for(int i=0,y=0,index =0;i<line && y< col;index++){
            result[index] = matrix[i][y];
            if(way){ //向上
                if(y==col-1){//抵达右边界
                    i++;
                    way=!way;
                    continue;
                }
                if(i==0){//抵达上边界
                    y++;
                    way=!way;
                    continue;
                }
                i--;y++; //正常向上
            }else{//向下
                if(i==line-1){//抵达下边界
                    y++;
                    way=!way;
                    continue;
                }
                if(y==0){//抵达左边界
                    i++;
                    way=!way;
                    continue;
                }
                i++;y--;//正常向下
            }
        }
        return result;
    }

    /**
     * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     *
     * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     *
     * 必须在不使用库的sort函数的情况下解决这个问题。
     *
     * 核心思路：双指针，一个从首部开始P0用来交换0，一个从尾部P2用来交换2
     * 循环终止 index < p2
     * @param nums
     */
    public static void sortColors(int[] nums) {
        int p0 = 0;
        int p2 = nums.length-1;
        for(int index =0;index<=p2;index++){
            while (nums[index] == 2 && index <= p2){//先换2,换到非2
                nums[index] = nums[p2];
                nums[p2] = 2;
                p2--;
            }
            if (nums[index] == 0){//再换0，便于把0换前面来，换到非0
                nums[index] = nums[p0];
                nums[p0] = 0;
                p0++;
            }
        }
    }


    /**
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     *
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * 核心思想：分治、快速选择、排序、堆（优先队列）
     *
     * 思路1：分K份，每份中遍历获取最大值，得到结果取最小值,遍历次数 n + k  ; 不可行：原因为每一组中可能存在多个比另外一组都大的值
     *
     * 正确思路：取前K个数，正序排序，遍历后续元素，比较第一个元素（最小值）如果比较小则跳过，否则放入排序序列中
     * @param
     */
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums,0,k);

        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1,n2) -> n1 - n2);
        heap.add(1);
        for(int i=k;i<nums.length;i++){
            if(nums[0] >= nums[i]){
                continue;
            }
            int index = 0;
            while (nums[index] < nums[i] && index < k){
                if(index > 0){
                    nums[index-1] = nums[index];
                }
                index ++;
            }
            nums[index-1] = nums[i];
        }
        return nums[0];
    }









    private static void swap(int[] nums,int x,int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }


    /**
     * 对于一个有正有负的整数数组，请找出总和最大的连续数列的和。
     *
     * 核心思路：遍历数组，对于以元素nums[i]为结尾的数组，其最大值f(i) = max(f(i-1)+nums[i],nums[i])
     * 此递推公式可以简化为，判断f(i-1)是否大于0，如果大于0，则直接加上nums[i],小于0，则直接使用nums[i]
     * 然后判断此时f(i) 是否大于在遍历过程中的maxNum;
     *
     * 升阶：如果要找出这个最大连续数列的范围，如何在递归中记录
     * 维护一个start=0,end=0；当max取值为f(i-1)+nums[i]时，end++;
     * 当取值为nums[i]是，start =i
     */

    private static int START_INDEX = 0;
    private static int END_INDEX = 0;

    private static int jisuanSum(int[] nums){
        int maxNum=0;
        int correntSum = 0;
        int start = 0;
        int end = 0;
        for(int i=0;i<nums.length;i++){
            if(correntSum > 0){
                end = i;
                correntSum += nums[i];
            }else{
                start = i;
                end = i;
                correntSum = nums[i];
            }
            if(correntSum > maxNum){
                START_INDEX = start;
                END_INDEX = end;
                maxNum = correntSum;
            }
        }
        return maxNum;
    }




    public static void main(String[] args) {
/*        int[][] matrix = {{5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}};
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));*/

/*        int[][] matrix = {{1, 1, 1},
                {1,0,1},
                {1, 1, 1}};
        setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));*/


/*        int[][] matrix = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        System.out.println(Arrays.toString(findDiagonalOrder(matrix)));*/

/*        int[]  a = {2,0,2,1,1,0};
        sortColors(a);
        System.out.println(Arrays.toString(a));*/

/*
        int[]  a = {-1,2,0};
        System.out.println(findKthLargest(a,2));*/

        int[] nums = {1,2,3,-6,1};
        System.out.println(jisuanSum(nums));
        System.out.println(START_INDEX);
        System.out.println(END_INDEX);
    }


}
