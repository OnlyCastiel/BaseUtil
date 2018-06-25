package com.mfq.test;

public class cpuRate {

    public static void main(String[] args) {

        try {
            for(;;){
                for( int i = 0;i< 14400000 ; i++){
                }
                Thread.sleep(10);
            }
        }catch (InterruptedException e){

        }




/*        ThreadTest threadTest1 = new ThreadTest();
        ThreadTest threadTest2 = new ThreadTest();

        threadTest1.start();
        threadTest2.start();*/

    }



}


class ThreadTest extends Thread{
    @Override
    public void run() {
        while (true){

        }
    }
}