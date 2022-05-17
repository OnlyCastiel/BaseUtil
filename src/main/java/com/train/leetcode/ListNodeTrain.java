package com.train.leetcode;

public class ListNodeTrain {


    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */





    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        //ListNode node5 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        //node4.next = node5;
        Solution solution = new Solution();
        solution.isPalindrome(node1);

    }
}

class Solution {
    /**
     * 删除链表的倒数第N个节点
     *
     * 双指针，fast先走n步，然后每走一布slow也走一步，
     * 维护一个node为要删除的节点头一个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        ListNode remove = head;
        int size = 1;
        int index = -1;
        while(node.next != null){
            if(size >= n){
                index ++;
                if(index > 0){
                    remove = remove.next;
                }
            }
            size ++;
            node = node.next;
        }
        if(size == 1){
            return null;
        }
        if(index == -1){//第一个的情况
            return head.next;
        }else{//其他情况
            remove.next = remove.next.next;
        }
        return head;
    }


    /**
     * 反转连表
     * 核心要点1、双指针解法  2、堆栈解法
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        //新链表
        ListNode newHead = null;
        ListNode temp = null;
        while (head != null) {
            //先保存访问的节点的下一个节点，保存起来
            //留着下一步访问的
//            temp = head.next;
//            //每次访问的原链表节点都会成为新链表的头结点，
//            //其实就是把新链表挂到访问的原链表节点的
//            //后面就行了
//            head.next = newHead;
//            //更新新链表
//            newHead = head;
//            //重新赋值，继续访问
//            head = temp;

            temp = head;
            head = head.next;
            temp.next = newHead;
            newHead = temp;
        }
        //返回新链表
        return newHead;
    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。
     * 新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * 核心要点：方法1、双指针  方法2、递归
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//        ListNode head = new ListNode();
//        ListNode temp = head;
//        while (list1 != null && list2 != null){
//            if(list1.val > list2.val){
//                temp.next = list2;
//                list2 = list2.next;
//            }else{
//                temp.next = list1;
//                list1 = list1.next;
//            }
//            temp = temp.next;
//        }
//        if (list1 != null){
//            temp.next = list1;
//        }
//        if (list2 != null){
//            temp.next = list2;
//        }
//        return head.next;

        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    /**
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     *
     * 正向遍历记录值，值反转后，比较相等:
     *
     * 升阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     * 思路：快慢指针，慢指针指向中间节点；反转slow后半部节点
     *
     * 栈
     * 递归
     * 链表
     * 双指针
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next; //走两步
            slow = slow.next; //走一步
        }
        //可能链表的长度会导致slow-fast相差一位，反转之后到末尾，只要保证起点相同即可，不影响比较
        slow = reverseList(slow);
        fast = head;
        while (fast != null && slow != null){
            if(fast.val != slow.val){
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;


        /*StringBuilder sb = new StringBuilder();
        while (head!=null){
            sb.append(head.val);
        }
        if(sb.toString() == sb.reverse().toString()){
            return true;
        }
        return false;*/
    }


    /**
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     *
     * 能用 O(1)（即，常量）内存解决此问题吗？
     * 思路：
     * 1、遍历到一个节点，判断是否已遍历过；否定，时间复杂度为 n*n
     * 2、优化思路，双指针，快慢指针，当快指针能够追上慢指针，代表有环；快每次2步，慢每次1步，故每次缩短距离为1，有环一定会碰见; 没环则遍历完成后一定会结束
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
