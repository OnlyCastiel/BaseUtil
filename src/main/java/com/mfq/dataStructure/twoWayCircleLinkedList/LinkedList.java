package com.mfq.dataStructure.twoWayCircleLinkedList;


public class LinkedList {

    private int size;

    private CircleNode first;

    public LinkedList() {
        this.size = 0;
    }

    public void add(CircleNode node){
        //为0则插入到末尾
        add(node,0);
    }

    public void add(CircleNode node,int index){
        if(this.size == 0){
            this.first=node;
            node.setFront(node);
            node.setNext(node);
            this.size++;
            return;
        }
        CircleNode front = this.first;
        for(int i=0;i<index;i++){
            front = first.getNext();
        }
        //插入对应位置
        node.setNext(front);
        node.setFront(front.getFront());
        front.getFront().setNext(node);
        front.setFront(node);
        this.size++;
    }

    /**
     * 双向循环链表，输入一只值，则移位输出26个英文字母
     * @param args
     */
    public static void main(String[] args) {
        int move = -3;
        char[] a = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        LinkedList linkedList = new LinkedList();
        for(int i=0;i<a.length;i++){
            CircleNode node = new CircleNode(a[i],null,null);
            linkedList.add(node);
        }
        //移位操作
        if(move >= 0){
            for(int i=0;i<move;i++){
                linkedList.first=linkedList.first.getNext();
            }
        }else{
            for(int i=0;i>move;i--){
                linkedList.first=linkedList.first.getFront();
            }
        }
        //输出
        CircleNode cur = linkedList.first;
        while(cur.getNext()!=linkedList.first){
            System.out.print(cur.getEle()+",");
            cur = cur.getNext();
        }
    }
}
