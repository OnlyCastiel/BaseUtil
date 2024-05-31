package com.base.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 *  CyclicBarrier (回环屏障) ： ReentrantLock + Condition  + parties(参与数量)  + barrierCommand
 *
 *  通过判断是否达到 参与数量 的线程，最后一个抵达的线程执行barrierCommand后，Condition将之前抵达屏障的线程唤醒，并重置屏障的状态
 *  未达到指定数量，则进入 await等待唤醒；
 *
 *  BrokenBarrierException 表示屏障已经被破坏
 *  破坏的原因可能是其中一个线程 await() 时被中断或者超时, 或其他线程调用了 reset()重建屏障
 *  在此之前抵达屏障的线程将抛异常
 *
 *
 *  经典范例：
 *  1、CyclicBarrier 可以用于多线程计算数据，最后合并计算结果的场景。
 *  2、利用可重置的特性，可以支持“人满发车”的场景
 */
public class CyclicBarrierTest {


    public static void main(String[] args) {

        AtomicInteger counter = new AtomicInteger();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,5,1000, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                (r) -> new Thread(r,counter.addAndGet(1)+"号"),
                new ThreadPoolExecutor.AbortPolicy());

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,() -> System.out.println("裁判：开始比赛~~"));

        for(int i= 0;i<5;i++){
            threadPoolExecutor.submit(new Runner(cyclicBarrier));
        }


    }

    static class Runner extends Thread{
        private CyclicBarrier cyclicBarrier;

        public Runner(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                //模拟业务
                int sleepMills = ThreadLocalRandom.current().nextInt(1000);
                Thread.sleep(sleepMills);

                if(Thread.currentThread().getName().equals("3号")){
                    System.out.println("等待线程数：" + cyclicBarrier.getNumberWaiting());
                    cyclicBarrier.reset();
                }

                System.out.println(Thread.currentThread().getName() + "选手已就位！ 耗时：" + sleepMills + "ms");
                cyclicBarrier.await();

                System.out.println(Thread.currentThread().getName() + "选手完成比赛!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                System.out.println(Thread.currentThread().getName() + "线程因为屏障损毁而异常");
                throw new RuntimeException(e);
            }
        }
    }
}
