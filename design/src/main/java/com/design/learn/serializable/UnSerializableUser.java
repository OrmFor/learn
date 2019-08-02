package com.design.learn.serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class UnSerializableUser {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("C:\\TEST.TXT"));
        User u =(User) objectInputStream.readObject();
        System.out.println(u.getName());

    }
}
