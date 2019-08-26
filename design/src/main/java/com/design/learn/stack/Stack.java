package com.design.learn.stack;

public interface Stack<T> {

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty();

    /**
     * 入栈操作
     * @return
     */
    public void push(T data);

    /**
     * 获取栈顶元素，不删除
     * @return
     */
    public T peek();

    /**
     * 出栈，获取栈顶元素并删除
     * @return
     */
    public T pop();


}


