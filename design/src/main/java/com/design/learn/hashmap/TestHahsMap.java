package com.design.learn.hashmap;

public class TestHahsMap {
    public static void main(String[] args){
          MyHashMap<String,Integer> map = new MyHashMap<>();
          for(int i = 0 ;i < 500 ; i++){
              map.put("key"+i , i);
          }

        for(int i = 0 ;i < 500 ; i++){
           System.out.println("key"+i + "value=" + map.get("key"+i));
        }
    }
}
