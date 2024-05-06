package com;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<String> openJobTitle = new ArrayList<String>(){{
            add("医师");
            add("主治医师");
            add("副主任医师");
            add("主任医师");
        }};

        System.out.println(openJobTitle.contains(null));
    }

    public static String formatUrl(String url) {
        if (url.contains("ACCESS_TOKEN")) {
            return url.replace("ACCESS_TOKEN", "1234");
        }
        return url;
    }
}
