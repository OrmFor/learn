package com.design.learn.basejava.listdemo;

import java.util.ArrayList;
import java.util.Arrays;

public class ListDemo {

    public static void main(String[] args){
       ArrayList<String> arr = new ArrayList();
        arr.add("123");
        arr.add("222");
        arr.add("33");
        arr.add("3");
        arr.add("3");
        arr.add("222");

       for (int i = 0 ;i < arr.size() ; i++){
           if(arr.get(i).equals("3")){
               arr.remove(i);
           }
           System.gc();
       }

        System.out.println(Arrays.toString(arr.toArray()));
    }
}
