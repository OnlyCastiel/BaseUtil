package com.mfq.tree;


import java.util.*;

/**
 * 树的深度优先搜索-遍历
 * 树的广度优先搜索-遍历
 *
 */
public class TreeSerch {

    /**
     * 深度优先搜索
     * 具体情况中，需要区分先序遍历、中序遍历。后序遍历
     * @param node
     * @return
     */
    public List<TreeNode> DFSSerchTree(TreeNode node){
        List<TreeNode> nodeList = new ArrayList<>();
        DFS(node,nodeList);
        return nodeList;
    }

    public void DFS(TreeNode node,List<TreeNode> list){
        //读取当前节点
        if(node == null){
            return;
        }
        list.add(node);
        //深度遍历当前节点左节点
        if(node.left != null){
            DFS(node.left,list);
        }
        //深度遍历遍历右节点
        if(node.right != null){
            DFS(node.right,list);
        }
    }



    /**
     * 广度优先搜索
     * @param node
     * @return
     */
    public List<TreeNode> BFSSerchTree(TreeNode node){
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        List<TreeNode> nodeList = new ArrayList<>();
        nodeQueue.add(node);
        while (!nodeQueue.isEmpty()){
            TreeNode pop = nodeQueue.remove();
            nodeList.add(pop);
            if(pop.left != null){
                nodeQueue.add(pop.left);
            }
            if(pop.right != null){
                nodeQueue.add(pop.right);
            }
        }
        return nodeList;
    }

    /**
     * 先根遍历二叉树
     */
    private void firstRootSerch(TreeNode node,List<TreeNode> nodeList){
        if(node == null){
            return;
        }
        nodeList.add(node);
        firstRootSerch(node.left,nodeList);
        firstRootSerch(node.right,nodeList);
    }

    /**
     * 中根遍历二叉树
     */
    private void midRootSerch(TreeNode node,List<TreeNode> nodeList){
        if(node == null){
            return;
        }
        midRootSerch(node.left,nodeList);
        nodeList.add(node);
        midRootSerch(node.right,nodeList);
    }


    /**
     * 后根遍历二叉树（逆波兰式）
     */
    private void lastRootSerch(TreeNode node,List<TreeNode> nodeList){
        if(node == null){
            return;
        }
        lastRootSerch(node.left,nodeList);
        lastRootSerch(node.right,nodeList);
        nodeList.add(node);
    }



    /**
     * 改造获取最大深度
     * @param node
     * @param deep
     * @return
     */
    public int DFSdeep(TreeNode node,int deep){
        //读取当前节点
        if(node == null){
            return deep;
        }
        //左右取更大值
        return Math.max(DFSdeep(node.left, deep + 1),DFSdeep(node.right,deep + 1));
    }


    /**
     * 验证二叉搜索树
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     *
     * 有效 二叉搜索树定义如下：
     *
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树
     *
     * 对于每个节点需要有两个判断：
     * 1、左子树的最大值（最右）小于该节点，该节点小于右子树的最小值（最左）
     * 2、左孩子小于该节点，该节点小于右孩子
     *
     *
     * 递归优化：isValidBST1
     * 讲上述两个条件合并为一个方法进行递归传递
     * 对于每个节点：
     * 1、其左子树，上界为该节点的值，下界限为Long.MIN_VALUE
     * 2、其右子树，下界为该节点的值，上界限为Long.MAX_VALUE
     * 3、对于当前值，应该大于下界限，小于上界限
     *
     * 遍历优化：isValidBST2
     * 搜索二叉树中序遍历情况下，得到的结果是有序的
     *
     * @param root
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        if(root.left != null){
            if(root.left.val > root.val){
                return false;
            }
            if(maxByWay(root.left,0) >= root.val){//递归找左子树最右
                return false;
            }
            //递归左
            if(!isValidBST(root.left)){
                return false;
            }
        }
        if(root.right != null){
            if(root.right.val < root.val){
                return false;
            }
            if(maxByWay(root.right,1) <= root.val){//右子树找最左
                return false;
            }
            //递归右
            if(!isValidBST(root.right)){
                return false;
            }
        }
        return true;
    }

    /**
     * 左子树找最大值，右子树找最小值
     * @param root 非空
     * @param way 0 左子树  1 右子树
     * @return
     */
    public int maxByWay(TreeNode root,int way){
        if(way == 0){//左子树找最右
            if(root.right != null){
                return maxByWay(root.right,0);
            }else {
                return root.val;
            }
        }
        if(way == 1){//右子树找最左
            if(root.left != null){
                return maxByWay(root.left,1);
            }else {
                return root.val;
            }
        }
        return root.val;
    }


    public boolean isValidBST1(TreeNode root) {
        return isValidBST(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root,long min,long max){
        if(root == null){
            return true;
        }
        if(root.val <= min || root.val >= max){
            return false;
        }
        return (isValidBST(root.left,min,root.val) && isValidBST(root.right,root.val,max));
    }


    /**
     * 对称二叉树
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     *
     * 思路：进行遍历，看是否对称，中序遍历---
     * 存在问题，当[1,2,2,2,null,2] 如下情况，空值会导致错误判断为对称
     * 补救：对每个节点，同时比较左子树右子树的值
     *
     * 递归思路：对于每一个节点，
     * 1、比较左孩子与有孩子；
     * 2、比较左孩子的左孩子与右孩子的右孩子，  比较左孩子的右孩子与右孩子的左孩子
     *
     * @param root
     */
    public boolean isSymmetric(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        midRootSerch(root,nodeList);
        for(int i = 0 ;i<nodeList.size() /2;i++){
            TreeNode left = nodeList.get(i);
            TreeNode right = nodeList.get(nodeList.size() - i -1);
            if(left.val != right.val){//值需要相等
                return false;
            }
            //左子树需要相等于右子树
            if(left.left == null && right.right == null){
            }else{
                if(left.left == null || right.right == null || left.left.val != right.right.val){
                    return false;
                }
            }
            //右子树需要相等左子树
            if(left.right == null && right.left == null){
            }else{
                if(left.right == null || right.left == null || left.right.val != right.left.val){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 递归思路：实测内存消耗更多，但更简洁
     * 1、比较左孩子与有孩子；
     * 2、比较左孩子的左孩子与右孩子的右孩子，  比较左孩子的右孩子与右孩子的左孩子
     * @param root
     */
    public boolean isSymmetric1(TreeNode root) {
        if(root == null){
            return true;
        }
        return isSymmetricCompare(root.left,root.right);
    }

    public boolean isSymmetricCompare(TreeNode left,TreeNode right){
        //都为空
        if(left == null && right == null){
            return true;
        }
        //比较值
        if(left == null || right == null || left.val != right.val){
            return false;
        }
        return isSymmetricCompare(left.left,right.right) && isSymmetricCompare(left.right,right.left);
    }


    /**
     * 二叉树的层序遍历
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     *
     * 思路1：递归，层级，，对于每个节点先左后右
     * 思路2：与思路1类似，BFS，广度优先遍历。通过队列进行缓存未完成广度遍历的节点
     *
     * @param root
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        levelSerch(root,resultList,0);
        return resultList;
    }

    public void levelSerch(TreeNode root,List<List<Integer>> resultList,int level){
        if(root == null){
            return;
        }
        List<Integer> list = null;
        if(level+1 > resultList.size()){
            list = new ArrayList<>();
            resultList.add(list);
        }else{
            list = resultList.get(level);
        }
        list.add(root.val);
        //递归添加左子树节点
        levelSerch(root.left,resultList,level+1);
        //递归添加右
        levelSerch(root.right,resultList,level+1);
    }

    /**
     * 将有序数组转换为二叉搜索树
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     *
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     *
     *
     * 思路：
     * 1、递归方式，每次取数组的中间值，作为当前节点
     * 2、左边数组作为左子树，右边数组作为右子树
     * 3、按照这种思路，左右两边高度差满足不超过1的要求
     * @param nums
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return bulidAvlTreeNode(nums,0,nums.length-1);
    }

    public TreeNode bulidAvlTreeNode(int[] nums,int start,int end){
        if(start > end){//出口判定
            return null;
        }
        int mid = (start+end)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = bulidAvlTreeNode(nums,start,mid-1);
        node.right = bulidAvlTreeNode(nums,mid+1,end);
        return node;
    }



    public static void main(String[] args) {
        TreeSerch treeSerch = new TreeSerch();
        //构造一个二叉树
//        TreeNode A = new TreeNode(1);
//        TreeNode B = new TreeNode(2);
//        TreeNode C = new TreeNode(3);
//        TreeNode D = new TreeNode(4);
//        TreeNode E = new TreeNode(5);
//
//        A.left = B;
//        A.right = C;
//        B.left = D;
//        B.right = E;
//
//
//        List<TreeNode> nodeList = treeSerch.DFSSerchTree(A);
//        nodeList.forEach(treeNode -> {
//            System.out.print(treeNode.val);
//        });
//        System.out.println();
//        List<TreeNode> nodeList1 = treeSerch.BFSSerchTree(A);
//        nodeList1.forEach(treeNode -> {
//            System.out.print(treeNode.val);
//        });
//
//        System.out.println(treeSerch.DFSdeep(A,0));


/*        TreeNode A = new TreeNode(2);
        TreeNode B = new TreeNode(1);
        TreeNode C = new TreeNode(3);

        A.left = B;
        A.right = C;

        System.out.println(treeSerch.isValidBST(A));
        System.out.println(treeSerch.isValidBST1(A));*/


        TreeNode A = new TreeNode(1);
        TreeNode B = new TreeNode(2);
        TreeNode C = new TreeNode(2);
        TreeNode D = new TreeNode(3);
        TreeNode E = new TreeNode(4);
        TreeNode F = new TreeNode(4);
        TreeNode G = new TreeNode(3);

        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        C.left = F;
        C.right = G;

        //System.out.println(treeSerch.isSymmetric(A));
        List<List<Integer>> lists = treeSerch.levelOrder(A);
        lists.forEach(list->{
            list.forEach(elem->{
                System.out.print(elem);
            });
            System.out.println();
        });



        int[] nums = {1,2,3,4,5};
        TreeNode treeNode = treeSerch.sortedArrayToBST(nums);
        System.out.println(treeNode);
    }

}
