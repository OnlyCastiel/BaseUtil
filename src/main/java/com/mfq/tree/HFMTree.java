package com.mfq.tree;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 哈夫曼数，二叉树，且使每个元素都在叶子节点上，
 * 使用0/1对每个分叉做标记时（左0右1），使得每个元素都有唯一编码，且不会与其他元素产生前缀匹配 (无前缀编码-> 最左子串)
 *
 * 性质1：哈夫曼编码是前缀编码
 * 性质2：哈夫曼编码是最优前缀编码。
 *
 *
 */
public class HFMTree {



    /**
     *  已知某系统在通信联络中只可能出现8种字符，
     *  其概率分别为0.05，0.29，0.07，0.08，0.14，0.23，0.03，0.11，
     *  试设计哈夫曼编码。
     *
     *  思路，8个字符，对应不同的权值，
     *  封装2*8长度的列表，
     *  列表每一个元素内容如下：拥有权重，拥有字符值，父节点index（初始化0），左子节点，右子节点
     *
     *  对每个元素生成哈夫曼编码，正向递归，获取全部长度，最后进行反转
     */
    public <T> List<HFMTreeElem<T>> getHFM(List<HFMTreeElem<T>> elemList){
        List<HFMTreeElem<T>> result = new ArrayList();
        result.add(new HFMTreeElem<>());
        result.addAll(elemList);
        int size = elemList.size();
        for(int i = 1; i<size ; i++){ //遍历次数  1 ~ size
            //获取 1~(i+size)范围内最小的两个元素，且未排入（parent=0），作为当前左右节点
            int end = i + size;
            int left = 0; //最小
            int right = 0; //第二小
            BigDecimal minValue = new BigDecimal("1"); //对应最小的值
            for (int y = 1; y < end ;y++ ){
                if(result.get(y).getParent() == 0){//可参与进行比较
                    BigDecimal probability = result.get(y).getProbability();
                    if(probability.compareTo(minValue) == -1){
                        //替换最小值left
                        right = left;
                        left = y;
                        minValue = probability;
                        continue;
                    }
                    if(right == 0 || probability.compareTo(result.get(right).getProbability()) == -1){
                        //替换次小right
                        right = y;
                    }
                    //其他情况直接结束
                }
            }
            //一次遍历完成，获取得到当前最小的两个未排入的元素，封装父节点,改变左右的parent;
            result.get(left).setParent(end);
            result.get(right).setParent(end);
            HFMTreeElem<T> endElem = new HFMTreeElem<>();
            endElem.setProbability(result.get(left).getProbability().add(result.get(right).getProbability()));
            endElem.setLeft(left);
            endElem.setRight(right);
            result.add(endElem);
        }
        return result;
    }


    public <T> void generateCode(List<HFMTreeElem<T>> elemList){
        for(int i = 1;i <= elemList.size() / 2; i++){
            HFMTreeElem<T> elem = elemList.get(i);
            if(elem.getElem() == null){ //循环指定范围后，此判断可以省略
                return;
            }
            StringBuffer codeStr = new StringBuffer();
            Integer index = i; //临时坐标
            while (elem.getParent() != 0){
                HFMTreeElem<T> parent = elemList.get(elem.getParent());
                if(parent.getLeft().equals(index)){
                    codeStr.append("0");
                }else{
                    codeStr.append("1");
                }
                index = elem.getParent();
                elem = parent;
            }
            elemList.get(i).setCodeStr(codeStr.reverse().toString());
        }
    }


    public static void main(String[] args) {
        List<HFMTreeElem<String>> elemList = new ArrayList(){{
            add(new HFMTreeElem<>("一",new BigDecimal("0.05")));
            add(new HFMTreeElem<>("二",new BigDecimal("0.29")));
            add(new HFMTreeElem<>("三",new BigDecimal("0.07")));
            add(new HFMTreeElem<>("四",new BigDecimal("0.08")));
            add(new HFMTreeElem<>("五",new BigDecimal("0.14")));
            add(new HFMTreeElem<>("六",new BigDecimal("0.23")));
            add(new HFMTreeElem<>("七",new BigDecimal("0.03")));
            add(new HFMTreeElem<>("八",new BigDecimal("0.11")));
        }};

        HFMTree hfmTree = new HFMTree();

        List<HFMTreeElem<String>> hfm = hfmTree.getHFM(elemList);
        hfmTree.generateCode(hfm);
        hfm.forEach(ele -> System.out.println(ele.toString()));

    }


}


