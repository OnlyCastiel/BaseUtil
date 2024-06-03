package com.base.concurrent;


import java.util.concurrent.*;

/**
 * 阻塞队列
 *
 * 为什么线程安全：因为读/写是阻塞的；
 *
 * 阻塞队列通过维护一个数组或者链表
 * 并内部通过ReentrantLock加锁的方式，控制读写并发的线程安全
 *
 * 重要的三个类：
 * ArrayBlockingQueue
 * LinkedBlockingQueue
 * DelayQueue
 */
public class BlockingQueueTest {




    public static void main(String[] args) throws InterruptedException {



        ArrayBlockingQueue queue1 = new ArrayBlockingQueue(16);
        LinkedBlockingQueue queue2 = new LinkedBlockingQueue();
        DelayQueue queue3 = new DelayQueue<DelayDemo>();


        queue3.add(new DelayDemo("A",1 * 1000));
        queue3.add(new DelayDemo("B",3 * 1000));
        queue3.add(new DelayDemo("C",2 * 1000));
        queue3.add(new DelayDemo("D",5 * 1000));

        System.out.println(queue3.take());
        System.out.println(queue3.take());
        System.out.println(queue3.take());
        System.out.println(queue3.take());


    }

}



class DelayDemo implements Delayed{


    private String name;

    private long expireTime;

    @Override
    public String toString() {
        return name;
    }

    public DelayDemo(String name, long expireTime) {
        this.name = name;
        this.expireTime = System.currentTimeMillis() + expireTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = expireTime - System.currentTimeMillis();
        return unit.convert(diff,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if(this.expireTime < ((DelayDemo) o).expireTime ){
            return  -1;
        }
        if(this.expireTime > ((DelayDemo) o).expireTime ){
            return  1;
        }
        return 0;
    }
}
