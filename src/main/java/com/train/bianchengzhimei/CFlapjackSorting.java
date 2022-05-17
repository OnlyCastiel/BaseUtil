package com.train.bianchengzhimei;

public class CFlapjackSorting {
    private int m_nCake;    //烙饼的个数
    private int[] m_CakeArray;  //烙饼的信息数组，也就是初始数组的输入数
    private int m_nMaxSwap;     //最大的交换次数
    private int[] m_SwapArray;  //存储交换位置信息的数组
    private int[] m_ReverseCakeArray;   //当时翻转烙饼信息数组
    private int[] m_ReverseCakeArraySwap;  //当时存储交换位置信息的数组
    private int m_nSearch;      //搜索次数

    public CFlapjackSorting() {
        m_nCake = 0;
        m_nMaxSwap = 0;
    }

    /**
     *
     * @param pCakeArray  输入的烙饼信息
     * @param nCake       数组的长度
     */
    public void init(int[] pCakeArray, int nCake) {
        assert (pCakeArray != null);
        m_nCake = nCake;
        m_CakeArray = new int[m_nCake];
        assert (m_CakeArray != null);
        //初始化烙饼数组
        for (int i = 0; i < m_nCake; i++) {
            m_CakeArray[i] = pCakeArray[i];
        }

        m_nMaxSwap = upBound(m_nCake);
        m_SwapArray = new int[m_nMaxSwap + 1];
        assert (m_SwapArray != null);
        m_ReverseCakeArray = new int[m_nCake];
        for (int i = 0; i < m_nCake; i++) {
            m_ReverseCakeArray[i] = m_CakeArray[i];
        }

        m_ReverseCakeArraySwap = new int[m_nMaxSwap+1];
    }

    /**
     * 最大上界：可以翻转的最大次数
     * @param nCake 烙饼的个数
     * @return
     */
    public int upBound(int nCake) {
        return 2 * nCake;
    }

    /**
     * 最小下界
     * @param pCakeArray
     * @param nCake
     * @return
     */
    int LowerBound(int[] pCakeArray, int nCake) {
        int t, lower = 0;
        for (int i = 1; i < nCake; i++) {
            t = pCakeArray[i] - pCakeArray[i - 1];
            if ((t == 1) || (t == -1)) {

            } else {
                lower++;
            }
        }
        return lower;
    }

    /**
     * 搜索函数
     * @param step  第几步
     */
    public void search(int step) {
        int minEstimate; //最小交换次数估计
        m_nSearch++;
        minEstimate = LowerBound(m_ReverseCakeArray, m_nCake);
        if (step + minEstimate > m_nMaxSwap)
            return;
        //判读是否排好序
        if (isSorted(m_ReverseCakeArray, m_nCake)) {
            //如果排好序了，而且翻转次数小于最大翻转次数。否则终止搜索
            if (step < m_nMaxSwap) {
                m_nMaxSwap = step;
                for (int i = 0; i < m_nMaxSwap; i++) {
                    m_SwapArray[i] = m_ReverseCakeArraySwap[i];
                }
            }
            return;
        }
        //进行翻转
        for (int i = 1; i < m_nCake; i++) {
            revert(0, i);
            m_ReverseCakeArraySwap[step] = i;  //记录每次翻转时翻转的位置信息
            search(step + 1);
            revert(0, i);
        }
    }

    /**
     * 判断是否排好序
     * @param pCakeArray
     * @param nCake
     * @return
     */
    public boolean isSorted(int[] pCakeArray, int nCake) {
        for (int i = 1; i < nCake; i++) {
            if (pCakeArray[i] < pCakeArray[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public void revert(int nBegin, int nEnd) {
        int t;
        for (int i = nBegin, j = nEnd; i < j; i++, j--) {
            t = m_ReverseCakeArray[i];
            m_ReverseCakeArray[i] = m_ReverseCakeArray[j];
            m_ReverseCakeArray[j] = t;
        }
    }

    /**
     * 输出翻转的位置信息
     * 搜索的次数
     * 翻转的次数
     * 翻转的每一步翻转情况
     */
    public void output() {
        for (int i = 0; i < m_nMaxSwap; i++) {
            System.out.printf("%d", m_SwapArray[i]);
        }

        System.out.printf("\n|Search Times|:%d\n", m_nSearch);
        System.out.printf("Total swap times = %d\n", m_nMaxSwap);

        perReverseArrayOutput(m_CakeArray,m_SwapArray,m_nCake,m_nMaxSwap);
    }
    /**
     * 通过记录每次翻转位置的数组，输入每次翻转的数组
     * 显示每一步的翻转结果
     * @param cakeArray  最初要排序的数组
     * @param swapArray  记录每次翻转时翻转的位置
     *@param nCake  数组的数量
     * @param maxSwap 翻转的次数
     */
    public static void perReverseArrayOutput(int[] cakeArray,int[] swapArray,int nCake,int maxSwap){
        System.out.printf("一共翻转%d次\n", maxSwap);
        int t;
        for(int i=0;i<maxSwap;i++) {
            System.out.printf("翻转0~%d烙饼结果: ",swapArray[i]);
            for (int x = 0,y = swapArray[i]; x < y; x++, y--) {

                t = cakeArray[x];
                cakeArray[x] = cakeArray[y];
                cakeArray[y] = t;
            }

            for(int k=0;k<nCake;k++){
                System.out.printf("%d ",cakeArray[k]);
            }
            System.out.print("\n");
        }


    }

    public void run(int[] pCakeArray,int nCake){
        init(pCakeArray,nCake);
        m_nSearch=0;
        search(0);
    }

    public static void main(String[] args) {
        CFlapjackSorting sorting = new CFlapjackSorting();
        int[] pCakeArray = new int[]{3,2,1,5,4};
        sorting.run(pCakeArray,pCakeArray.length);
        sorting.output();

    }



}