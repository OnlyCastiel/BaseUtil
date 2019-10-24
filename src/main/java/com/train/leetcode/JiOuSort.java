package com.train.leetcode;


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

    public static void main(String[] args) {
        int[] a =new int[]{9,7,13,5,6,2,8,1,1,3,3,12,14,13,15};
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]);
            System.out.print(",");
        }
        System.out.println();
        sort(a);
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]);
            System.out.print(",");
        }
    }

    /**
     * 解题思路：利用插入排序算法，对奇偶子序列分别插入排序。
     * 用两个变量把整个数组分割为三个部分，第一个部分为奇数有序子序列，第二部分为偶数有序子序列，第三部分为未排序子序列。
     * 使用两个变量进行分割，mid 指向偶数有序子序列的一个元素，end 指向未排序子序列
     * （等待一个个插入前面的奇数有序子序列或偶数有序子序列）。
     * _________________________________________________________
     |   奇数有序子序列   |   偶数有序子序列   |  未排序子序列      |
     _________________________________________________________
                          |                    |
                         mid                  end
     */

}
