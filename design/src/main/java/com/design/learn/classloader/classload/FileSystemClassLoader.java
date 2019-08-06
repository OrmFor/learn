package com.design.learn.classloader.classload;

import java.io.*;

public class FileSystemClassLoader extends ClassLoader {

    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        super();
        this.rootDir = rootDir;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //去jvm底层查找  native方法
        Class<?> c = findLoadedClass(name);
        //首先查询这个类有没有加载过
        if (c != null) {
            return c;
        } else {

            ClassLoader parent = this.getParent();
            try {
                c = parent.loadClass(name);
            } catch (Exception e) {
                //e.printStackTrace();
            }
            if (c != null) {
                return c;
            } else {

                byte[] classData = getClassData(name);
                if (classData == null) {
                    throw new ClassNotFoundException();
                } else {

                    c = defineClass(name, classData, 0, classData.length);

                }
            }
        }

        return c;

    }

    private byte[] getClassData(String name) {
        String path = rootDir + "\\" + name.replace(".", "\\") + ".class";
        System.out.println(path);
        InputStream is = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            is = new FileInputStream(path);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;

    }

}
