package com.mfq.test;

import com.train.leetcode.People;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DomainTest {

    public static void main(String[] args) {


        System.out.printf("2020-04-04 12:12:00".substring(11,16));

/*
        Integer msgTime = 1587020602;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis((long) msgTime*1000);
        calendar.add(Calendar.MINUTE,30);
        Date tigerTime = calendar.getTime();

        People man = new People(18,"张三",new Date());

        System.out.println(man.toJson());
*/

        /*List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Integer[] str = list.toArray(new Integer[list.size()]);

        System.out.println();*/


    }

    public static String changeStr(String str){
        return new String("123456");
    }
}
