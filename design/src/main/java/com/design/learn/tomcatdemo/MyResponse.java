package com.design.learn.tomcatdemo;

import java.io.IOException;
import java.io.OutputStream;

public class MyResponse {

    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException{
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 OK\n")
                .append("Content-Type:application/json\n")
                .append("\r\n")
                .append(content);
        outputStream.write(httpResponse.toString().getBytes());
        outputStream.close();
    }
}
