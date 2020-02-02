package com.mfq.dataStructure.circleLinkedList;


import java.util.Random;

public class CircleNodeList {

    private CircleNode first;

    private int size;

    public CircleNodeList(){
        super();
    }

    public int size(){
        return this.size;
    }


    public CircleNodeList(int size) {
        if(size <= 1){
            System.out.println("环必须大于1");
        }
        this.size = size;
        first = new CircleNode(null,null);
        CircleNode ele = null;
        CircleNode curr = first;
        for(int i=0;i<size;i++){
            ele = new CircleNode(null,null);
            curr.setNext(ele);
        }
        ele.setNext(first);
    }

    public void add(CircleNode ele){
        if(this.first == null){
            this.first = ele;
            this.size =1;
            return;
        }
        CircleNode curr = first;
        for(int i=0;i<size-1;i++){
            curr = curr.getNext();
        }
        curr.setNext(ele);
        ele.setNext(this.first);
        size++;
    }

    public CircleNode get(int index){
        if(index < 0 || index >= size){
            System.out.println("下标越界!");
        }
        CircleNode curr = first;
        for(int i=0;i<=index;i++){
            if(i==index){
                return curr;
            }
            curr = curr.getNext();
        }
        return null;
    }

    public void remove(int index){
        //当index>size时，按照环反复循环
        index = index > size ? index/size : index;
        if(index < 0){
            System.out.println("下标越界!");
        }
        CircleNode curr = first;
        CircleNode frontNode = null;
        //当去除第0个时，需要找到最后一个，便于构成新环
        index = index == 0 ? size:index;
        for(int i=0;i==index;i++){
            if(i<index){
                //遍历
                frontNode = curr;
                curr = curr.getNext();
            }else{
                //删除
                frontNode.setNext(curr.getNext());
                curr = null;
                size--;
                return;
            }
        }
    }



    /**
     * 约瑟夫环问题：
     * 39 个犹太人与Josephus及他的朋友躲到一个洞中，39个犹太人决定宁愿死也不要被敌人抓到，
     * 于是决定了一个自杀方式，41个人排成一个圆圈，由第1个人开始报数，每报数到第3人该人就必须自杀，
     * 然后再由下一个重新报数，直到所有人都自杀身亡为止。
     */
/*    public static void main(String[] args) {
        int size = 41;
        CircleNodeList nodeList = new CircleNodeList();
        for(int i=1;i<=size;i++){
            CircleNode ele = new CircleNode(i,null);
            nodeList.add(ele);
        }


        CircleNode front = nodeList.get(0);
        CircleNode curr = front.getNext();
        for(int i=2;front.getNext().getNext()!=front;i++){
            if(i%3==0){
                //删除当前节点curr
                front.setNext(curr.getNext());
                System.out.print(curr.getEle()+"->");
            }else{
                //删除节点时，front游标不移动
                front = front.getNext();
            }
            curr = curr.getNext();
        }
        System.out.println("剩余两人为"+front.getEle() +"和"+front.getNext().getEle());
        System.out.println("弹出结束");
    }*/

    /**
     * 编号为1~N的N个人按照顺时针方向围坐一圈，每一个人持有一个密码（正整数，可以随机输入）
     * 开始人选择一个正整数作为报数上限M，从第一个人按照顺时针方向自1报数，报到M则停止报数，且报M人出列
     * 同时将他所持有的密码作为新的M，从他的顺时针方向下一个人从1报数，直至所有人出列
     * @param args
     */
    public static void main(String[] args) {
        //人数
        int size = 10;
        //正整数M的最大值(0,maxM]
        int maxM = 10;


        CircleNodeList nodeList = new CircleNodeList();
        for(int i=1;i<=size;i++){
            CircleNode ele = new CircleNode((int)(Math.random()*maxM)+1,null);
            System.out.print(ele.getEle().toString()+',');
            nodeList.add(ele);
        }

        //初始M值
        int initM = (int)(Math.random()*maxM)+1;
        System.out.println("初始M值为："+initM);

        //开始弹出,第一次从第一个人开始报数1，报到M即弹出
        //最后一个
        CircleNode frontNode = nodeList.get(size-1);
        //第一个，也是当前
        CircleNode currNode = nodeList.get(0);
        for(int n=1;currNode.getNext()!=currNode;n++){
            for(int i=1;i<initM;i++){
                //往后移位的过程中，front与curr都需要移位
                frontNode = frontNode.getNext();
                currNode = currNode.getNext();
            }
            //弹出当前currNode,front不用移位，curr需要后移
            initM =(int) currNode.getEle();
            System.out.println("第"+n+"次"+"弹出节点"+currNode.getEle()+";同时M值更新为"+currNode.getEle());
            frontNode.setNext(currNode.getNext());
            currNode = currNode.getNext();
        }
        System.out.println("游戏结束，胜利者"+currNode.getEle());

    }
}
