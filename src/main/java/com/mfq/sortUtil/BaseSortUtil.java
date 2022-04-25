package com.mfq.sortUtil;

import com.mfq.domain.DataSort;

import java.util.Arrays;

public class BaseSortUtil {

    public static void main(String[] args) {
        DataSort[] data = { new DataSort(9, ""), new DataSort(-16, ""),
                new DataSort(21, "*"), new DataSort(23, ""),
                new DataSort(-30, ""), new DataSort(-49, ""),
                new DataSort(21, ""), new DataSort(30, "*"),
                new DataSort(30, "")};

//		sort4(data);
//		sort5(data);
        System.out.println("����6");
        binaryInsertSort(data);
        //二分插入排序
//		binaryInsertSort(data);

        //冒泡排序
//		BubbleSort(data);

        //桶排序
//		BucketSort(data, -49, 31);

        //ֱ插入排序
//		InsertSort(data);

        //归并排序
//		MergeSort(data);
    }

    public static void sort2(DataSort[] data){
        int arrayLength=data.length;
        for(int i=1;i<arrayLength;i++){
            DataSort temp = data[i];
            if(data[i].compareTo(data[i-1])<0){
                int j=i-1;
                for(;j>=0 && data[j].compareTo(temp)>0;j--){
                    data[j+1]=data[j];
                }
                data[j+1]=temp;
            }
        }
        System.out.println(java.util.Arrays.toString(data));
    }




    /*
     * 二分插入排序
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从二分之一的位置开始比较
     * 如果该元素（已排序）大于新元素，则取前半段再重新取二分之一，否则取后半段二分之一的位置进行比较
     * 重复步骤3，直到 mid>high，即找到最终坐标值
     * 将新元素插入到该位置后
     * 重复步骤2~5
     */
    public static void binaryInsertSort(DataSort[] data){
        System.out.println(Arrays.toString(data));
        System.out.println("排序开始");
        for(int i=1;i<data.length;i++){
            DataSort tmp = data[i];
            int low =0;
            int high=i-1;
            while (low<=high){
                int mid =(low+high)/2;
                if(tmp.compareTo(data[mid])>0){
                    low=mid+1;
                }else{
                    high=mid-1;
                }
            }
            for(int j=i;j>low;j--){
                data[j]=data[j-1];
            }
            data[low]=tmp;
            System.out.println(Arrays.toString(data));
        }
        System.out.println("排序结束");
        System.out.println(Arrays.toString(data));
    }



    /**
     *冒泡排序：
     * 第一次冒泡，将数组中的最大值，冒泡到对应位置；
     * 第二次冒泡，将数组中的第二大值，冒泡到对应位置
     */
    public static void BubbleSort(DataSort[] data){
        System.out.println("冒泡排序开始：");
        System.out.println(java.util.Arrays.toString(data));
        for(int i=0;i<data.length-1;i++){
            for(int j=0;j<data.length-1-i;j++){
                if(data[j].compareTo(data[j+1])>0){
                    DataSort tmp = data[j+1];
                    data[j+1]=data[j];
                    data[j]=tmp;
                }
            }
            System.out.println(java.util.Arrays.toString(data));
        }
        System.out.println("冒泡排序结束：");
        System.out.println(java.util.Arrays.toString(data));
    }

    /**
     * 桶排序
     * 将阵列分到有限数量的桶子里。每个桶子再个别排序（有可能再使用别的排序算法或是以递回方式继续使用桶排序进行排序）。
     * 桶排序是鸽巢排序的一种归纳结果。当要被排序的阵列内的数值是均匀分配的时候，桶排序使用线性时间（Θ（n））。
     * 但桶排序并不是 比较排序，他不受到 O(n log n) 下限的影响。
     */
    public static void BucketSort(DataSort[] data,int min,int max){
        System.out.println("桶排序开始：");
        System.out.println(java.util.Arrays.toString(data));
        int arrayLength= data.length;
        int[] bucket=new int[max-min];
        DataSort[] temp = new DataSort[arrayLength];
        System.arraycopy(data, 0, temp, 0, arrayLength);
        for(int i=0;i<arrayLength;i++){
            bucket[data[i].data-min]++;
        }
        for(int i=1;i<max-min;i++){
            bucket[i]=bucket[i]+bucket[i-1];
        }
        for(int k=0;k<arrayLength;k++){
            data[--bucket[temp[k].data-min]]=temp[k];
        }
        //������ѭ����ͬ
//		for (int k = arrayLength - 1; k >= 0; k--) {
//			data[--bucket[temp[k].data - min]] = temp[k];
//		}
        System.out.println("桶排序结束：");
        System.out.println(java.util.Arrays.toString(data));
    }

    /*
     * ֱ�Ӳ������򣬴ӵڶ���Ԫ�ؿ�ʼ����ǰ��Ԫ�ر�Ϊ����
     * ��֮���Ԫ��ֱ����ǰ����Ԫ�رȽϣ��ҵ���Ԫ��Ӧ���ڵ�λ�ã������ȥ
     */
    public static void InsertSort(DataSort[] data){
        System.out.println("插入排序开始：");
        System.out.println(java.util.Arrays.toString(data));
        int arrayLength=data.length;
        for (int i=1;i<arrayLength;i++){
            DataSort temp = data[i];
            if(temp.compareTo(data[i-1])<=0){
                int j=i-1;
                for(;j>=0 && data[j].compareTo(temp)>0;j--){
                    data[j+1]=data[j];//�˴�ѭ���ж�ʹ��temp,ԭ������data[j+1]=data[j]��ı�data[i]��ֵ
                }
                data[j+1]=temp;
            }
        }
        System.out.println("插入排序结束：");
        System.out.println(java.util.Arrays.toString(data));
    }


    /*
     * �鲢����
     * ���õݹ鷽ʽ����ԭ���鲻���з�Ϊ��ͬ���ȵ����飬ֻ�������з֣�ÿ������ֻ��һ��Ԫ�أ�
     * �ٽ��������鲢������鲢Ǯ��������������鲢��������Ȼ����
     */
    public static void MergeSort(DataSort[] data){
        System.out.println("归并排序开始：");
        System.out.println(java.util.Arrays.toString(data));
        DataSort(data,0,data.length-1);
        System.out.println("归并排序结束：");
        System.out.println(java.util.Arrays.toString(data));
    }
    public static void DataSort(DataSort[] data,int left,int right){
        if(left<right){
            int center = (left+right)/2;
            DataSort(data,left,center);
            DataSort(data,center+1,right);

            DataMerge(data,left,center,right);
        }
    }
    public static void DataMerge(DataSort[] data,int left,int center,int right){
        int mid= center+1;
        int third =left;
        int tmp =left;
        DataSort[] tempSort = new DataSort[data.length];
        while(left<=center && mid<=right){
            if(data[left].compareTo(data[mid])>0){
                tempSort[third++]=data[mid++];
            }else{
                tempSort[third++]=data[left++];
            }
        }
        while(left<=center){
            tempSort[third++]=data[left++];
        }
        while(mid<=right){
            tempSort[third++]=data[mid++];
        }
        while(tmp<=right){
            data[tmp]=tempSort[tmp++];
        }
    }

    //�۰��������
    public static void sort1(DataSort[] data){
        System.out.println(java.util.Arrays.toString(data));
        int arrayLength = data.length;
        for(int i=1;i<arrayLength;i++){
            int low =0;
            int high =i-1;
            DataSort temp = data[i];
            while(low <= high){
                int mid = (low +high)/2;
                if(data[mid].compareTo(temp)>0){
                    high=mid-1;
                }else{
                    low=mid +1;
                }
            }
            //����д��������ֵ�ĸ���
//			for(int j=low;j<i;j++){
//				data[j]=data[j+1];
//			}

            //��ȷд��
//			for (int j = i; j > low; j--) {
//				data[j] = data[j - 1];
//			}
            //������д��Ч����ͬ
            System.arraycopy(data, low, data, low+1, i-low);

            data[low]=temp;
            System.out.println(java.util.Arrays.toString(data));
        }
        System.out.println(java.util.Arrays.toString(data));
    }


    //ֱ�Ӳ�������
    public static void sort3(DataSort[] data){
        int arrayLength= data.length;
        for(int i=1;i<arrayLength;i++){
            DataSort temp = data[i];
            if(temp.compareTo(data[i-1])<0){
                int j=i-1;
                while(j>=0 && temp.compareTo(data[j])<0){
                    data[j+1]=data[j];
                    j--;
                }
                data[j+1]=temp;
            }
        }
        System.out.println(java.util.Arrays.toString(data));
    }
    //�۰��������
    public static void sort4(DataSort[] data){
        int arrayLength= data.length;
        for(int i=1;i<arrayLength;i++){
            int low=0;
            int high=i-1;
            DataSort temp = data[i];
            while(low<=high){
                int mid =(low+high)/2;
                if(temp.compareTo(data[mid])>0){
                    low=mid+1;
                }else{
                    high=mid-1;
                }
            }
            for(int j=i;j>low;j--){
                data[j]=data[j-1];
            }
            data[low]=temp;
        }
        System.out.println(java.util.Arrays.toString(data));
    }
    //ð�������㷨
    public static void sort5(DataSort[] data){
        int arrayLength = data.length;
        for(int i=0;i<arrayLength;i++){
            for(int j=0;j<arrayLength-i-1;j++){
                if(data[j].compareTo(data[j+1])>0){
                    //����λ��
                    DataSort temp = data[j+1];
                    data[j+1]=data[j];
                    data[j]=temp;
                }
            }
        }
        System.out.println(java.util.Arrays.toString(data));
    }
    //�鲢����
    public static void sort6(DataSort[] data){
        mergeSort(data,0,data.length-1);
        System.out.println(java.util.Arrays.toString(data));
    }
    public static void mergeSort(DataSort[] data, int left,int right){
        if(left<right){
            int center=(left+right)/2;
            mergeSort(data,left,center);
            mergeSort(data,center+1,right);
            merge(data,left,right,center);
        }
    }
    public static void merge(DataSort[] data,int left,int right,int center){
        DataSort[] tempArr=new DataSort[data.length];
        int temp=left;
        int mid=center+1;
        int i=left;
        while(left<=center && mid<=right){
            if(data[left].compareTo(data[mid])<0){
                tempArr[temp++]=data[left++];
            }else{
                tempArr[temp++]=data[mid++];
            }
        }
        if(left<=center){
            tempArr[temp++]=data[left++];
        }
        if(mid<=right){
            tempArr[temp++]=data[mid++];
        }
        while(i<=right){
            data[i]=tempArr[i++];
        }
    }
}
