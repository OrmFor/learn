package com.design.learn.serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializableUser {


    public static void main(String[] args) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("c:\\test.txt"));
        com.design.learn.serializable.User user = new User();
        user.setName("wwy");
        user.setAge("28");
        out.writeObject(user);
    }
}
