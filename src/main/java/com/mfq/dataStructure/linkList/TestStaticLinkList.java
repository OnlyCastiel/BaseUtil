package com.mfq.dataStructure.linkList;

import java.util.ArrayList;

public class TestStaticLinkList {

    public static void main(String[] args) {
        StaticLinkList linkList = new StaticLinkList();
        linkList.add("第1个元素");
        linkList.add("第2个元素");
        linkList.add("第3个元素");
        linkList.add("第4个元素");
        linkList.add("第5个元素");

        System.out.println();

        linkList.remove(3);
        linkList.add("第六个元素");

        System.out.println();

    }
}
