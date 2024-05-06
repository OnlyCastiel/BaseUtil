package com.train.leetcode;


import java.util.Arrays;

/**
 * 长度为n的数组进行升序排序，奇数偶数分别排序，奇数在前，偶数在后；
 * 例如：9,7,13,5,6,2,8  排序结果应为：5,7,9,13,2,6,8
 */
public class JiOuSort {

    public static void sort(int[] a){
        int length = a.length;
        int mid = 0;//有序队列，偶数首位坐标
        int end = 0;//当前筛选对象的坐标

        for(;end < length ; end++){
            int pos = 0;
            if(a[end]%2 != 0){//奇数
                //从0-mid之中，遍历获取位置pos,默认这个值最大(当找不到时);
                pos = mid;
                for(int i=0;i<mid;i++){
                    if(a[i] > a[end]){//真实位置没比较出来时，则为mid位
                        pos = i;
                        break;
                    }
                }
                //如果为奇数，分解坐标需要移1位
                mid ++;
            }else{//偶数
                //从mid-end之间，遍历获取位置pos,默认这个值最大(当找不到时);
                pos = end;
                for(int i=mid;i<end;i++){
                    if(a[i] > a[end]){
                        pos = i;
                        break;
                    }
                }
            }
            if(end!=pos){
                //需要先记录end值
                int curNum = a[end];
                //移位,将[pos，end)范围内的所有值后移一位,一定要从最后一位开始移，避免值覆盖
                for(int i=end;i>pos;i--){
                    a[i]=a[i-1];
                }
                //在a[pos]处插入a[end]值；
                a[pos] = curNum;
            }
        }
    }



    /**
     * 解题思路：利用插入排序算法，对奇偶子序列分别插入排序。
     * 用两个变量把整个数组分割为三个部分，第一个部分为奇数有序子序列，第二部分为偶数有序子序列，第三部分为未排序子序列。
     * 使用两个变量进行分割，mid 指向偶数有序子序列的第一个元素，end 指向未排序子序列
     * （等待一个个插入前面的奇数有序子序列或偶数有序子序列）。
     * _________________________________________________________
     |   奇数有序子序列   |   偶数有序子序列   |  未排序子序列      |
     _________________________________________________________
                          |                    |
                         mid                  end
     */
    public static void jiouSort(int[] nums){
        int mid=0;
        //int end=0;
        //遍历数组
        for(int i=0;i<nums.length;i++){
            //判断是奇数还是偶数
            int index = 0;
            int temp = nums[i];
            if(nums[i] % 2 == 0){//偶数
                //偶数插入到[mid,end],找index即可
                index = i;
                for(int j = mid;j<=i;j++){
                    if(nums[i] <= nums[j]){
                        index = j;
                        break;
                    }
                }
            }else{
                //奇数需要插入到[0~mid)范围内，找index即可，mid++
                index = mid;//假设最大
                mid++;
                for(int j = 0;j<mid;j++){
                    if(nums[i] <= nums[j]){
                        index = j;
                        break;
                    }
                }
            }
            //目标index之后所有位依次移动一位到end; [mid~end]
            for(int v = i;v > index;v--){
                nums[v] = nums[v-1];
            }
            //复制i的值temp到index
            nums[index] = temp;
        }
    }


    /**
     * 奇偶排序，一个数组包含奇数偶数，要求进行正序排序，最后奇数在前半部分，偶数在后半部分
     * @param nums
     */
    public static void jiouSort1(int[] nums){
        int ji=0;
        int ou=nums.length-1;
        while (ji<ou){
            //从前往后找偶数
            while (ji < nums.length && nums[ji] % 2 == 1){
                ji++;
            }
            //从后往前找奇数
            while (ji <= ou && nums[ou] % 2 == 0){
                ou--;
            }
            //交换
            if(ji < nums.length && ou >= 0){
                nums[ji] = nums[ji] + nums[ou];
                nums[ou] = nums[ji] - nums[ou];
                nums[ji] = nums[ji] - nums[ou];
            }
        }
        //最后ji的位置即为奇数最后一位（经过换位了）
        Arrays.sort(nums,0,ji);
        if(ji<nums.length){
            Arrays.sort(nums,ji+1,nums.length);
        }
    }



    public static void main(String[] args) {
        int[] nums = {1,3,5};
        jiouSort(nums);
        System.out.println(Arrays.toString(nums));


/*        int[] a =new int[]{9,7,13,5,6,2,8,1,1,3,3,12,14,13,15};
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]);
            System.out.print(",");
        }
        System.out.println();
        sort(a);
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]);
            System.out.print(",");
        }*/
    }
}
