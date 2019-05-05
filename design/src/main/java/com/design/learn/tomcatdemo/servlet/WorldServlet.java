package com.design.learn.tomcatdemo.servlet;

import com.design.learn.tomcatdemo.MyRequest;
import com.design.learn.tomcatdemo.MyResponse;

import java.io.IOException;

public class WorldServlet extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get world");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post world");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
