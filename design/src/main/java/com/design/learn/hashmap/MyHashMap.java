package com.design.learn.hashmap;

import java.util.ArrayList;
import java.util.List;

/**
* @Author wwy
* @Description 1.7版本   1.8引入红黑树  当链表长度超过8（TREEIFY_THRESHOLD = 8）时会进行树化操作
 *                                    当树的节点小于6（UNTREEIFY_THRESHOLD = 6）时会进行链表化操作
* @Date 13:51 2019/8/6
* @Param
* @return
**/
public class MyHashMap<K,V> implements DIYMap<K,V> {

    //初始化数组长度
    private static final int DEFAULT_INITIAL_CAPACITY = 1<<4;

    //阀值比例   16 * 0.75 = 12 当数组长度为12时触发
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int defaultInitSize;

    private float defaultLoadFactor;

    private int entryUseSize;

    //entry table数组
    private MyEntry<K,V>[] table = null;


    public MyHashMap(){
        this(DEFAULT_INITIAL_CAPACITY,DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int defaultInitCapacity,float defaultLoadFactor){
        if(defaultInitCapacity < 0){
            throw new IllegalArgumentException("Illegal init capacity:" + defaultInitCapacity );
        }

        if(defaultLoadFactor <= 0 || Float.isNaN(defaultLoadFactor)){
            throw new IllegalArgumentException("Illegal load factor:" + defaultLoadFactor);
        }
        this.defaultInitSize = defaultInitCapacity;
        this.defaultLoadFactor = defaultLoadFactor;
        table = new MyEntry[this.defaultInitSize];

    }


    /**
    * @Description
     * 第一，要考虑是否扩容？
     * HashMap中的Entry的数量（数组以及单链表中的所有Entry）是否达到阀值？
     * 第二，如果扩容，意味着新生成一个Entry[]，不仅如此还得重新散列。
     * 第三，要根据Key计算出在Entry[]中的位置，定位后，如果Entry[]中的元素为null，
     * 那么可以放入其中，如果不为空，那么得遍历单链表，要么更新value，要么形成一个新的Entry“挤压”单链表！
    * @Date 10:24 2019/8/6
    * @Param [k, v]
    * @return V
    **/
    @Override
    public V put(K k, V v) {
        V oldValue = null ;
        //判断是否需要扩容
        if(entryUseSize >= defaultInitSize * defaultLoadFactor){
            resize(2 * defaultInitSize);
        }

        //得到hash值，计算出数组中的位置
        int index = hash(k) & (defaultInitSize - 1);
        //如果链表中不存在放置最后
        if(table[index] == null){
            table[index] = new MyEntry<K, V>(k,v,null) ;
            ++entryUseSize;
        }else{//需要遍历单链表
            MyEntry<K,V> entry = table[index];
            MyEntry<K,V> e = entry;
            while (e != null){
                //如果相等则放入entry数组里面
                if(k == e.getKey() || k.equals(e.getKey())){
                    oldValue = e.value;
                    e.value = v;
                    return oldValue;
                }
                e = e.next;
            }
            table[index] = new MyEntry<K,V>(k,v,entry);
            ++entryUseSize;
        }

        return oldValue;
    }

    @Override
    public V get(K k) {
        int index = hash(k) & (defaultInitSize - 1);
        if(table[index] == null) {
            return null;
        }else {
            //遍历Entry
            MyEntry<K,V> entry = table[index];
            do{
                if(k == entry.getKey() || k.equals(entry.getKey())){
                    return entry.value;
                }
                entry = entry.next;

            }while (entry != null);
        }


        return null;
    }


    private void resize(int size){
        MyEntry[] newTable = new MyEntry[size];
        defaultInitSize = size;
        entryUseSize = 0;
        rehash(newTable);
    }

    private void rehash(MyEntry<K,V>[] newTable){
        List<MyEntry<K,V>> entryList = new ArrayList<MyEntry<K,V>>();
        for(MyEntry<K,V> e : table){
            if(e != null){
                do{
                    entryList.add(e);
                    e = e.next;
                }while (e != null);
            }
        }

        //覆盖旧的table
        if(newTable.length > 0){
            table = newTable;
        }

        //重新hash就是重新put一遍
        for(MyEntry<K,V> entry : entryList){
            put(entry.getKey(),entry.getValue());
        }

    }

    private int hash(K k){
        int hashCode = k.hashCode();
        //
        hashCode = hashCode ^ ((hashCode >>> 20) ^ (hashCode >>> 12));
        return hashCode ^ ((hashCode >>> 7) ^(hashCode >>> 4));
    }


    static class MyEntry<K,V> implements DIYMap.Entry<K,V>{

        K key;
        V value;
        MyEntry<K,V> next;

        public MyEntry(){

        }

        public MyEntry(K key ,V value , MyEntry<K,V> next){

            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }


}

