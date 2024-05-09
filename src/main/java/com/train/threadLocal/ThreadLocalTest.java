package com.train.threadLocal;

/**
 * ThreadLocal验证相关设想
 *
 * 1、线程变化的情况下，ThreadLocal不变，ThreadLocalMap中缓存的数据是否变化 ---（验证ThreadLocal是通过Thread实现的线程本地缓存机制）
 * key相同，线程变化，则ThreadLocalMap不同
 *
 * 2、线程不变的情况下， ThreadLocal用private static修饰，重复调用放入值，操作结束不调用remove()，查看数据是否覆盖
 * 若未能用新值覆盖旧值，会有脏数据出现，若一直未调用，则该缓存数据无法被回收
 *
 *
 * 3、线程不变的情况下，每次响应用户操作，new ThreadLocal() ,查看ThreadLocalMap中是否有旧值，
 * 线程不变（不销毁），ThreadLocal每次new，ThreadLocalMap中缓存的旧数据，
 * 会因为 ThreadLocal被Thread中的ThreadLocalMap作为key引用；而thread因为线程池一直存在，不会被销毁，故而造成内存泄漏
 */
public class ThreadLocalTest extends Thread{

    //private static ThreadLocal<String> session = new ThreadLocal<>();

    //操作调用
    public void operate(){
        ThreadLocal<String> session = new ThreadLocal<>();
        long id = Thread.currentThread().getId();
        System.out.println(id + "当前缓存值" + session.get());
        session.set("aaaaa");

    }

    @Override
    public void run() {
        operate();
    }

    public static void main(String[] args) {
        ThreadLocalTest test = new ThreadLocalTest();
        test.operate();
        test.operate();
    }

}
