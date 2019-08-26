package com.design.learn.tomcatdemo;

import com.design.learn.tomcatdemo.MyRequest;
import com.design.learn.tomcatdemo.MyResponse;
import com.design.learn.tomcatdemo.servlet.MyServlet;
import com.design.learn.tomcatdemo.servlet.ServletMapping;
import com.design.learn.tomcatdemo.servlet.ServletMappingConfig;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MyTomcat {

    private int port = 8080;

    private Map<String,String> urlServletMap =new HashMap<String,String>();

    public MyTomcat(int port){
        this.port = port;
    }

    public void start(){
        initServletMapping();
        ServerSocket serverSocket = null;

        try {
            serverSocket=new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                MyRequest myRequest = new MyRequest(inputStream);
                MyResponse myResponse = new MyResponse(outputStream);
                dispath(myRequest, myResponse);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void initServletMapping(){
        for(ServletMapping servletMapping : ServletMappingConfig.servletMappingList){
            urlServletMap.put(servletMapping.getUrl(),servletMapping.getClazz());
        }
    }

    private void dispath(MyRequest myRequest, MyResponse myResponse){
        String clazz = urlServletMap.get(myRequest.getUrl());

        try {
            if(StringUtils.isNotBlank(clazz)) {
                Class<MyServlet> servletClass = (Class<MyServlet>) Class.forName(clazz);
                MyServlet myServlet = servletClass.newInstance();
                myServlet.service(myRequest, myResponse);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MyTomcat(8080).start();
    }
}
