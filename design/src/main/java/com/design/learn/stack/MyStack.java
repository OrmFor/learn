package com.design.learn.stack;

/**
 * 比较low的实现，会有很多问题出现
 * 　1、上面栈的实现初始化容量之后，后面是不能进行扩容的（虽然栈不是用来存储大量数据的），如果说后期数据量超过初始容量之后怎么办？（自动扩容）
 * 　2、我们是用数组实现栈，在定义数组类型的时候，也就规定了存储在栈中的数据类型，那么同一个栈能不能存储不同类型的数据呢？（声明为Object）
 */
public class MyStack {
    private int[] array;
    private int maxSize;
    private int top;

    public MyStack(int size){
        this.maxSize = size;
        array = new int[size];
        top = -1;
    }

    //压入数据
    public void push(int value){
        if(top < maxSize-1){
            array[++top] = value;
        }
    }

    //弹出栈顶数据
    public int pop(){
        return array[top--];
    }

    //访问栈顶数据
    public int peek(){
        return array[top];
    }

    //判断栈是否为空
    public boolean isEmpty(){
        return (top == -1);
    }

    //判断栈是否满了
    public boolean isFull(){
        return (top == maxSize-1);
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack(3);
        stack.push(0);
        stack.push(2);
        stack.push(5);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }
}
