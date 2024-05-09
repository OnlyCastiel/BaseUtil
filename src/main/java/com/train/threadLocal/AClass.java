package com.train.threadLocal;


import lombok.Data;

import java.util.*;

@Data
public class AClass extends BaseClass{

    private static  ThreadLocal<String> s = new ThreadLocal<>();

    public static void main(String[] args) {
       // ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor();
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.MONTH));
//        CountDownLatch count = new CountDownLatch(1);
//
//               // CyclicBarrier ;
//        //Semaphore
//        //AppClassLoader
//        ClassLoader.getSystemClassLoader().loadClass();
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        executorService.submit();
//        ReentrantLock lock = new ReentrantLock(false);
//        s.set("123123");
    }


//    HashMap

//    CopyOnWriteArrayList
//    Exception
//    StringBuilder;
//    StringBuffer;
//    LinkedList;

}
