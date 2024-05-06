package com.mfq.tree;


/**
 * B+树
 * B+树是一种B-树的变形树，更适合用于文件索引系统。严格来讲，它已不符合第5章中定义的树了。
 *
 * 1．B+树和B-树的差异
 * 一棵m阶的B+树和m阶的B-树的差异在于：
 * （1）有n棵子树的结点中含有n个关键字；
 * （2）所有的叶子结点中包含了全部关键字的信息（B-树非叶子节点也可能匹配），以及指向含这些关键字记录的指针，且叶子结点本身依关键字的大小自小而大顺序链接；
 * （3）所有的非终端结点可以看成是索引部分，结点中仅含有其子树（根结点）中的最大（或最小）关键字。
 */
public class BTree {
}