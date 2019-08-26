package com.design.learn.thread.future.impl;


import com.design.learn.thread.future.impl.RealData;
import com.design.learn.thread.future.service.Data;

public class Future implements Data {

    private RealData realData = null;

    //阻塞标志
    private boolean flag = false;

    public synchronized void setRealData(RealData realData){
        if(flag){
            return;
        }

        this.realData = realData;
        flag = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return realData.getResult();
    }
}
