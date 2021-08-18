package com.train.subject;


import java.util.Arrays;

/**
 * 课本数据结构练习题
 */
public class DataStructureBook {

    //第一章上机实验题
    /**
     * 第一章第一题：输出所有小于等于n(n为一个大于2的正数)的素数。要求：
     * 1、每行输出10个素数
     * 2、尽可能采用较优的算法
     */
    public static void printSuShu(int n){
        int num = 0;//外层控制10个一组
        for(int i=2;i<n;i++){
            boolean flag = true;
            //判断该值是否为素数
            for(int j=2;j<i;j++){
                if(i%j == 0){
                    flag = false;
                    break;
                }
            }
            //输出素数，每行10个
            if(flag){
                num++;
                System.out.printf("%d ",i);
                //System.out.print(i);
                if(num%10 == 0) System.out.println();
            }
        }
    }


    /**
     * 第一章第二题：计算任意输入的正整数的各位数字之和，并分析算法的时间复杂度
     */
    public static void printNumberSum(int n){
        String.valueOf(n);
        int sum = 0;
        if(n/10 == 0){
            sum = n;
        }else{
            while (n / 10 > 0 && n >= 10){
                sum += n%10;
                n = n / 10;
            }
            sum += n;
        }
        System.out.println(sum);
    }


    /**
     * 第一章第三题：判断一个字符串是否为回文（正读反读一样）
     */
    public static boolean isCircleStr(String str){
        boolean flag = true;
        for(int i = 0 ;i< str.length()/2 ; i ++){
            if(str.charAt(i) != str.charAt(str.length() - i-1)){
                flag = false;
                break;
            }
        }
        return flag;
    }


    /**
     * 第二章：例子2.3
     * 假设一个数组a[],需要剔除其中值等于x的元素
     * 要求时间复杂度为 O(n),空间复杂度为 O(1)
     *
     * 有两种思路：1/2
     */
    public static int[] removeArrayValue1(int a[],int x){
        int k=0;//记录每个元素结果位置
        for(int i=0;i<a.length;i++){
            if(a[i] != x){
                //所有不等于x得元素，且坐标不同，才需要赋值
                a[k] = a[i];
                //但只要不等于，k的位置便需要增加
                k++;
            }
            //等于情况下，k不动，等待被替换，此时i++继续遍历
        }
        return Arrays.copyOf(a, k);
    }

    public static int[] removeArrayValue2(int a[],int x){
        int k=0;//记录每个元素应当进行的位移位置
        for(int i=0;i<a.length;i++){
            //每个元素先按照当前位置进行位移
            a[i-k] = a[i];
            if(a[i] == x){//如果元素命中，则k增加
                k++;
            }
        }
        return Arrays.copyOf(a, a.length-k);
    }


    /**
     * 第二章：例2.4
     * 有一个顺序表L，假设以第一个元素为基准，
     * 将所有比它小的元素放在它之前，所有比它大的元素放在它之后
     *
     * 两种思路 1/2
     */
    public static int[] changePosition1(int a[]){
        int j=1,k=a.length-1,tmp;//j为首位，k为末尾,找到对应的值进行交换
        while (j<k){
            while (a[j] <= a[0]){
                j++;
            }
            while (a[k] >= a[0]){
                k--;
            }
            //换位
            tmp = a[j];
            a[j] = a[k];
            a[k] = tmp;
            j++;
            k--;
        }
        tmp = a[0];
        a[0] = a[j-1];
        a[j-1] = tmp;
        return a;
    }

    //先找一个小值放首位，再找一个大值覆盖刚才的小值位置
    public static int[] changePosition2(int a[]){
        int tmp = a[0],i=0,j=a.length-1;
        while(i<j){
            while(i<j && a[j] >= tmp){//先从后往前找j
                j--;
            }
            a[i] = a[j];
            while (i<j && a[i] <= tmp){//再从前往后找一个
                i++;
            }
            a[j] = a[i];
        }
        a[j] = tmp;
        return a;
    }


    public static void main(String[] args) {
        //DataStructureBook.printSuShu(10086);
        //DataStructureBook.printNumberSum(60086);
        //System.out.println(DataStructureBook.isCircleStr("一棵树棵一"));

/*        int a[] = new int[]{1,2,3,4,2,2,3,4,3,2,4};
        a = DataStructureBook.removeArrayValue2(a,2);
        for(int i =0 ; i < a.length ;i++){
            System.out.printf("%d ",a[i]);
        }*/

        int a[] = new int[]{5,1,2,6,7,4,3,9,10};
        a = DataStructureBook.changePosition2(a);
        for(int i =0 ; i < a.length ;i++){
            System.out.printf("%d ",a[i]);
        }
    }

}
