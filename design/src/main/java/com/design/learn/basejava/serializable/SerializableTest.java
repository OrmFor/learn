package com.design.learn.basejava.serializable;

import lombok.Data;

import java.io.*;

public class SerializableTest {



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //序列化   把内存中的对象保存到硬盘中   fos writeObject
        FileOutputStream fos = new FileOutputStream("f:\\test.txt");
        People p = new People();
        p.setAge(18);
        p.setName("wwy");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(p);
        oos.close();
        fos.close();

        //反序列化   把硬盘中的文件解析到内存   fis  readObject
        FileInputStream fis = new FileInputStream("F:\\test.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        People object = (People)ois.readObject();
        fis.close();
        System.out.println(object.getName());

        String [] b = "a|b|c".split("\\|");
        System.out.println(b.length);
        for (int i = 0 ;i < b.length ; i++){
            System.out.println(b[i]);
        }
    }


}


@Data
class  People implements Serializable {
    private  String name;
    private Integer age;
}
