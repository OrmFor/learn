package com.design.learn.classloader.classload;

import org.junit.Test;

public class TestFileSystemClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {
        FileSystemClassLoader fileSystemClassLoader = new FileSystemClassLoader("F:\\");

        Class<?> c = fileSystemClassLoader.findClass("Hello");
        System.out.println(c.getClassLoader());
    }

}
