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
        //�۰�����
//		binaryInsertSort(data);

        //ð������
//		BubbleSort(data);

        //Ͱ����
//		BucketSort(data, -49, 31);

        //ֱ�Ӳ�������
//		InsertSort(data);

        //�鲢����
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
     * ͨ��low��high�趨һ��������У����ϵĽ��µ�Ԫ�ز�����������
     * ����ʱ�������۰룬���Ҹ�Ԫ����Ӧ���ڵ�λ�ã����󽫸�λ�ü���֮�������Ԫ�غ���
     * ��󣬽���Ԫ�ز����Ӧλ��
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



    /*
     * ð������ͨ������ѭ����ÿһ���ڲ�ѭ�����Ὣ��ǰlength-1-i������Ԫ����ȡ��length-1-iλ��
     * ���ѭ������length�ι��󣬸�����Ϊ��������
     */
    public static void BubbleSort(DataSort[] data){
        System.out.println("ð������֮ǰ");
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
        System.out.println("ð���������");
        System.out.println(java.util.Arrays.toString(data));
    }

    /*
     * Ͱ����
     * ����Ҫ��������У��������ֵ���Լ���Сֵ
     * ����һ��ֵ��max-min���ȵ����飬������ÿһ��λ�ü�¼��ֵ���ֵĴ���
     * ��������ÿ��ֵ����Ϊǰ��Ԫ��ֵ֮�ͣ���Ϊ��ֵӦ�����ֵ�λ��
     */
    public static void BucketSort(DataSort[] data,int min,int max){
        System.out.println("Ͱ����֮ǰ");
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
        System.out.println("Ͱ����֮��");
        System.out.println(java.util.Arrays.toString(data));
    }

    /*
     * ֱ�Ӳ������򣬴ӵڶ���Ԫ�ؿ�ʼ����ǰ��Ԫ�ر�Ϊ����
     * ��֮���Ԫ��ֱ����ǰ����Ԫ�رȽϣ��ҵ���Ԫ��Ӧ���ڵ�λ�ã������ȥ
     */
    public static void InsertSort(DataSort[] data){
        System.out.println("��ʼֱ�Ӳ�������");
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
        System.out.println("����ֱ�Ӳ�������");
        System.out.println(java.util.Arrays.toString(data));
    }


    /*
     * �鲢����
     * ���õݹ鷽ʽ����ԭ���鲻���з�Ϊ��ͬ���ȵ����飬ֻ�������з֣�ÿ������ֻ��һ��Ԫ�أ�
     * �ٽ��������鲢������鲢Ǯ��������������鲢��������Ȼ����
     */
    public static void MergeSort(DataSort[] data){
        System.out.println("�鲢����ǰ");
        System.out.println(java.util.Arrays.toString(data));
        DataSort(data,0,data.length-1);
        System.out.println("�鲢�����");
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
