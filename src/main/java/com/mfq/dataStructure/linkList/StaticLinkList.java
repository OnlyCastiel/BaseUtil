package com.mfq.dataStructure.linkList;

/**
 * 实现基于数组的静态链表
 * 数组首位cur，记录空余位置的首位
 * 数组末尾cur，记录链表首位坐标
 *
 *
 * 好处：
 * 1 节约频繁的分配/删除内存的时间
 * 2 在内存分配和使用上更安全(避免溢出和分配失败)
 */
public class StaticLinkList {

    private static final int MAX_SIZE = 10;

    //使用数组存储
    DataEle[] elems;

    //当前已使用空间
    int size;

    //默认初始化方法
    //默认构造方法，初始化MAX_SIZE的空间；
    public StaticLinkList() {
        this.elems = new DataEle[MAX_SIZE];
        //从第2位（坐标1）开始，设置每一位的cur指向下一位的坐标
        for(int i=0;i<MAX_SIZE;i++){
            elems[i]=new DataEle();
            elems[i].setCur(i+1);
        }
        //数组末尾cur，记录链表首位坐标
        elems[MAX_SIZE-1].setCur(1);
        //数组首位cur，记录空余位置的首位
        elems[0].setCur(1);
        size = 0;
    }


    public int size(){
        return size;
    }

    //空闲位置首位加入一个值
    public void add(Object ele){
        set(size+1,ele);
    }



    //链表中插入
    //TODO 判断是否越界
    public void set(int index,Object ele){
        //分配一个空间
        int pos = elems[0].getCur();
        //给分配出来的空间进行赋值
        elems[pos].setData(ele);
        //将空闲坐标后移一位
        elems[0].setCur(elems[pos].getCur());

        //从第一位开始，获取第index位置的坐标
        int k = 1;//获取index位置的下标,以及index-1位置的下标(插入的位置在index-1之后)
        for(int i = 1;i < index;i++){
            k = elems[k].getCur();
        }
        elems[pos].setCur(elems[k].getCur());
        elems[k].setCur(pos);
        size++;
    }

    public void remove(int index){
        //从第一位开始，获取第index位置的坐标
        int j = MAX_SIZE-1;
        int k = 1;
        for(int i = 1;i < index;i++){
            //保留第k-1元素的坐标
            j = k;
            //第index位置的坐标为k
            k = elems[k].getCur();
        }
        //j直接指向k所指向的位置
        elems[j].setCur(elems[k].getCur());
        //释放k
        elems[k].setData(null);
        elems[k].setCur(elems[0].getCur());
        elems[0].setCur(k);
        size--;
    }


}
