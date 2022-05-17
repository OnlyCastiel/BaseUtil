package com.train.bianchengzhimei;

/**
 * 烙饼翻转问题，自行撰写调试，程序流程图
 * https://www.processon.com/diagraming/61ebb50063768967fffdbda8
 */
public class CFlapjackSorting1 {

    private int[] m_p_Array;//原饼堆，记录原饼堆是为了记录饼的翻饼顺序
    private int maxSort; //翻饼次数上界限,动态记录最小次数
    private int[] sortArrayStep; //当前翻饼步骤，长度最多为2*n
    private int[] sortArrayStepFinal; //翻饼最终步骤
    private int[] sortArrayResult; //当前翻饼结果


    private void init(int[] p_Array){
        maxSort = p_Array.length * 2 - 2;
        sortArrayStep = new int[maxSort];
        sortArrayStepFinal = new int[maxSort];
        sortArrayResult = new int[p_Array.length];
        for(int i=0;i<p_Array.length; i++){
            m_p_Array[i] = p_Array[i];//缓存原始饼堆，后续输出时，可以根据此来输出每一次的翻转过程
            sortArrayResult[i] = p_Array[i];//初始化操作饼堆
        }
    }

    /**
     * 核心递归方法
     *
     * 出口：已经有序:
     * 判断排序次数是否超过maxSort，如果没超过则替换当前翻饼序列
     *
     * 逻辑处理：
     * 在每一个位置 n ，都进行反转，然后递归每一个位置 n;
     * 实现树的深度优先搜索，返回后，反转回来，尝试另外一个分支；
     *
     *
     * @param step 当前递归层级
     */
    private void reserch(int step){
        if(isSorted(sortArrayResult)){
            //判断排序次数是否超过maxSort，如果没超过则替换当前翻饼序列
            //sortArray 翻饼序列已经保存在这个数组中
            if(step < maxSort){
                for(int i=0;i<sortArrayStep.length;i++){
                    sortArrayStepFinal[i] = sortArrayStep[i];
                }
                maxSort = step;
            }
            return;
        }
        int remainTimes = lastMinSortNum(sortArrayResult);
        if(step + remainTimes >= maxSort){
            return;
        }
        //从0-1开始，对每一摞子饼进行反转
        for(int i = 1; i<m_p_Array.length; i++){
            if(step > 0 && i == sortArrayStep[step-1]){
                continue;//优化思路，连续两次翻转不可翻转同一段区间，如果翻转区间相同则为无效反转，即相邻的翻转操作，必定为翻转不同的区间
            }
            reverse(i);
            sortArrayStep[step] = i;
            reserch(step + 1);//递归第二层
            sortArrayStep[step] = 0;
            reverse(i);//反转回来，再下一次循环搜索另外一颗子树
        }

    }

    //反转饼堆
    private void reverse(int n){
        int temp;
        for(int i = 0,j=n; i<j ; i++,j--){
            temp = sortArrayResult[j];
            sortArrayResult[j] = sortArrayResult[i];
            sortArrayResult[i] = temp;
        }

    }


    //堆当前至少还需要翻多少次，为当前相邻饼中，需要分开的间隔数
    private int lastMinSortNum(int[] sortArrayResult){
        int count = 0;
        for(int i =0;i<sortArrayResult.length-1;i++){
            int num = sortArrayResult[i] - sortArrayResult[i + 1];
            if(num != 1 && num != -1){
                count ++;
            }
        }
        return count;
    }


    //判断是否已经有序
    private boolean isSorted(int[] sortArrayResult){
        for(int i=0;i<sortArrayResult.length - 1;i++){
            if(sortArrayResult[i] > sortArrayResult[i+1]){
                return false;
            }
        }
        return true;
    }


    //输出翻饼步骤
    private void output(){
        for(int i=0;i<maxSort;i++ ){
            System.out.println(sortArrayStepFinal[i]);
        }
    }

    //运行主方法
    private void run(int[] p_Array){
        init(p_Array);
        reserch(0);
    }

    public static void main(String[] args) {
        CFlapjackSorting1 sorting = new CFlapjackSorting1();
        int[] pCakeArray = new int[]{3,2,1,5,4};
        sorting.run(pCakeArray);
        sorting.output();

    }
}
