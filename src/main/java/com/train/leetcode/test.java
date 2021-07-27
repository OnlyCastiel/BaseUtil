package com.train.leetcode;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.*;

public class test {

    public static void main(String[] args) {

        String str[] = new String[]{"one","two","three","four","five"};

        //People humens[] = new People[]{new People(15,"张"),new People(17,"王"),new People(18,"刘")};



/*        List<Object> list = new ArrayList<Long>();//无法编译通过
        list.add("I don't fit in");
        Arrays.sort(humens);
        ArrayList list = new ArrayList();
        System.out.println(Arrays.toString(humens));*/

    }


    public List<?> getNumListsss(){
        return null;
    }

    public List<? extends Number> getNumList(){
        return null;
    }

/*    public List<E extends Number> getNumListss(){
        return null;
    }*/

    public <E extends Number> List<E> getNumLists(){
        return null;
    }

    public static <E extends Comparable<? super E>> void selectionSorte(E[] a,int n){

    }


    public static <T extends Comparable<? super T>> void selectionSort(T[] a,int n){

    }

    static <E> List<E> asList(E[] a){
        return null;
    }


}
