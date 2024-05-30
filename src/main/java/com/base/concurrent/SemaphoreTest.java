package com.base.concurrent;


import java.util.concurrent.*;

/**
 * SemaphoreTest 信号量
 *
 * 主要应用场景：限流器
 * 控制对单个资源的最大并发访问量；
 * 例如：web服务最大连接数为100；但同时下单的请求数不能超过10；
 *
 */
public class SemaphoreTest {

    /**
     * 定义一个同时只能处理5个请求的限流器
     */
    private static final Semaphore semaphore = new Semaphore(5);

    /**
     * 定义一个线程池，模拟最大并发数为100
     */
    private static final Executor executor = new ThreadPoolExecutor(10,20, 60,TimeUnit.SECONDS,new LinkedBlockingDeque<>(10));


    /**
     * 实际的业务执行发放，要求控制并发访问为5
     */
    private static void exec(){
        try {
            semaphore.acquire();
            //模拟业务执行
            System.out.println(Thread.currentThread().getName() + "执行业务方法");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        while (true){
            executor.execute(()->exec());
        }
    }
}
