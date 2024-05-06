package com.train.swordoffer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 *
 */
public class LinkedListTrain {



    public ListNode reverseList(ListNode head) {
        ListNode temp = head;
        ListNode listNode = reverseList1(head);
        temp.next = null;
        return listNode;
    }

    public ListNode reverseList1(ListNode node){
        //出口
        if(node.next == null){
            return node;
        }
        reverseList(node.next).next = node;
        return node;
    }




    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     *
     * 思路1：递归调用
     * 思路2：暂存后反序
     * 优化思路2：无法避免的两次遍历，那么一次遍历确定空间，一次遍历进行赋值
     *
     * 比较：递归调用一样占用了程序计数器的空间，这种花费实际上比直接开辟内存空间使用的内存的更多；
     * 比起基础数据类型，尽量避免使用封装类型，封装类型会耗费更多内存空间；2倍以上；
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        ListNode node = head;
        int size = 0;
        while (node != null){
            size ++;
            node = node.next;
        }
        int[] nums = new int[size];
        while (head != null){
            nums[--size] = head.val;
            head = head.next;
        }
        return nums;
    }



}


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
