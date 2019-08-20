package com.design.learn.hashmap;

public interface DIYMap<K,V> {
    V put(K k ,V v);
    V get(K k);

    interface Entry<K , V>{
        K getKey();
        V getValue();
    }
}
