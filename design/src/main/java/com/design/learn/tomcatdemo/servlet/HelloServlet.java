package com.design.learn.tomcatdemo.servlet;

import com.design.learn.tomcatdemo.MyRequest;
import com.design.learn.tomcatdemo.MyResponse;

import java.io.IOException;

public class HelloServlet extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
