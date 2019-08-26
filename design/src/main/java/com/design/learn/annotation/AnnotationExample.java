package com.design.learn.annotation;

import com.design.learn.annotation.MethodInfo;
import com.design.learn.annotation.ParseAnnotation;

public class AnnotationExample {

    @MethodInfo(author = "wwy",describe = "测试一下",version = "1.0.1")
    public  void test(){
        System.out.println("test annotaion");
    }

    public static void main(String[] args){
         new ParseAnnotation().parseAnnotation();
    }
}
