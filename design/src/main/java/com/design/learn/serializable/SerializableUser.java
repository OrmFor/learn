package com.design.learn.serializable;

import java.io.*;

public class SerializableUser {


    public static void main(String[] args) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("c:\\test.txt"));
        User user = new User();
        user.setName("wwy");
        user.setAge("28");
        out.writeObject(user);
    }
}
