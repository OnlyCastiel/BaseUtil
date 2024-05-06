package com.mfq.multiTask;

import sun.misc.Unsafe;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestExecutor {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;


    public static void main(String[] args) {

        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("机器核心线程数："+i);


        System.out.println(COUNT_BITS);
        System.out.println(CAPACITY);
        System.out.println(Integer.toBinaryString(CAPACITY));

        System.out.println(Integer.toBinaryString(RUNNING));
        System.out.println(Integer.toBinaryString(SHUTDOWN));
        System.out.println(Integer.toBinaryString(STOP));
        System.out.println(Integer.toBinaryString(TIDYING));
        System.out.println(Integer.toBinaryString(TERMINATED));

        System.out.println(Integer.toBinaryString(-1));


        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(0, 20,
                        0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10),
                        new ThreadFactory() {
                            @Override
                            public Thread newThread(Runnable r) {
                                return null;
                            }
                        }, new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println();
            }
        };
        thread.start();

        //Callable callable

        //Future<> submit = threadPoolExecutor.submit(thread);

        threadPoolExecutor.execute(thread);

    }

    /**
     * unsafe 可以直接根据对象及属性偏移量，获取内存中的值；而不是获取栈中的值；
     * 好处：不涉及内核态与用户态的切换，具体实现不是锁，而是一种值的比较判断
     *
     */
    public void testUnsafe(){
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();


        Unsafe unsafe = Unsafe.getUnsafe();

        //unsafe.compareAndSwapInt();
    }

}
