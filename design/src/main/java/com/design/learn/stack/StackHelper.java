package com.design.learn.stack;

import java.io.Serializable;
import java.util.EmptyStackException;

class StackHelper<T> implements Stack<T>, Serializable {

    private static final long serialVersionUID = 2819515836402973675L;

    //栈-1表示为空
    private int top = -1;

    //默认容量
    private int capacity = 10;

    //存放容器
    private T[] arrayList;

    //存放数量
    private int size;

    @SuppressWarnings("unchecked")
    public StackHelper(){
        arrayList =(T[]) new Object[this.capacity];
    }

    @SuppressWarnings("unchecked")
    public StackHelper(int capacity){
        arrayList =(T[]) new Object[capacity];
    }


    public int getSize() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return this.top == -1;
    }

    @Override
    public void push(T data) {
        if(arrayList.length == size){
            //需要扩容
            expandCapacity(size*2+1);
        }
        arrayList[++top] = data;

        size++;
    }

    @Override
    public T peek() {
        if(isEmpty()){
            new EmptyStackException();
        }
        return arrayList[top];
    }

    @Override
    public T pop() {
        if(isEmpty()){
            new EmptyStackException();
        }
        size--;
        return arrayList[top--];
    }

    @SuppressWarnings("unchecked")
    private void expandCapacity(int capacity){
        //先判断是否需要扩容
        if(capacity < size){
            return;
        }
        T[] old = arrayList;
        arrayList = (T[]) new Object[capacity];
        /**
         * 这里使用JDK 自带方法System.arraycopy，比for循环来的快
         * 原先数组  原先数组起始位置
         * 目标数组 目标数组起始位置
         * copy长度
         *
         */
        System.arraycopy(old, 0, arrayList, 0, size);

	/*
	    for (int i=0; i<size ; i++){
	            arrayList[i]=old[i];
	    }
	*/
    }

    public static void main(String[] args) {
		/*StackHelper<String> s = new StackHelper<String>(1);
		s.push("1");
		s.push("2");
		String t=s.pop();
		System.out.println(t);*/
        Long start = System.currentTimeMillis();
        StackHelper<String> m = new StackHelper<String>(3);
        for(int i = 0;i<6;i++){
            m.push("hello" + i);
        }
        for(int j = 0 ; j<6 ; j++)
            System.out.println(m.pop());

        Long end = System.currentTimeMillis();
        Long time = (end - start);
        System.out.println(time);
    }

}
