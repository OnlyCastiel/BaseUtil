package com.mfq.tree;


/**
 * 图的深度优先搜索-遍历
 * 图的广度优先搜索-遍历
 *
 * 无论树或者图，都可以通过领接矩阵进行表示；
 * 但需要区分树与图的特性；
 * 树：不存在环
 * 图：可能存在环，可能是非连同图
 *
 *
 */
public class TreeSerch {

    /**
     * 深度优先
     * 都可以借助队列队列实现遍历，讲待遍历的同一层放入队列中
     * 但在遍历过程中，对于图可能产生环的情况，需要判断是否已遍历过
     * @param tree
     * @return
     */
    public int[] DFSSerchTree(int[][] tree){

        return null;
    }



    /**
     * 广度优先
     * 都可以借助队列队列实现遍历，将待遍历下一层的节点放入队列中
     * 但在遍历过程中，对于图可能产生环的情况，需要判断是否已遍历过
     * @param tree
     * @return
     */
    public int[] BFSSerchTree(int[][] tree){

        return null;
    }

}
