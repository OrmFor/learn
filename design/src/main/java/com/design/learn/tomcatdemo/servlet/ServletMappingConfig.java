package com.design.learn.tomcatdemo.servlet;

import com.design.learn.tomcatdemo.servlet.ServletMapping;

import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {

    public static List<ServletMapping> servletMappingList = new ArrayList<ServletMapping>();

    static {
        servletMappingList.add(new ServletMapping("hello","/hello",
                "com.design.learn.tomcatdemo.servlet.HelloServlet"));
        servletMappingList.add(new ServletMapping("world","/world",
                "com.design.learn.tomcatdemo.servlet.WorldServlet"));
    }
}
