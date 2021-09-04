package com.design.learn.dynamicregistration;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class DynamicRegistrate {

    public static void main(String[] args) throws Exception {
         DynamicRegistrate.createStudentByFile();
    }

    private static void createStudentByFile() throws Exception{
        String studentString = "package com.design.learn.dynamicregistration;public class Test{private String studentId;public String getStudentId(){return this.studentId;}public void setStudentId(String studentId){this.studentId = studentId;}}";
        String fileName = System.getProperty("user.dir") + "\\design\\src\\main\\java\\com\\design\\learn\\dynamicregistration\\Test.java";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(studentString);
        fileWriter.flush();
        fileWriter.close();


        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
        Iterable<? extends JavaFileObject> javaFileObjects = manager.getJavaFileObjects(fileName);
        String dest = System.getProperty("user.dir") + "/design/target/classes";

        //options就是指定编译输入目录，与我们命令行写javac -d C://是一样的

        List<String> options = new ArrayList<String>();
        options.add("-d");
        options.add(dest);
        JavaCompiler.CompilationTask task = compiler.getTask(null,manager,null,options,null,javaFileObjects);
        task.call();
        manager.close();
        URL[] urls = new URL[]{new URL("file:/" + System.getProperty("user.dir") + "/target/classes")};

        //加载class时要告诉你的classloader去哪个位置加载class文件

        ClassLoader classLoader = new URLClassLoader(urls);
        Object student = classLoader.loadClass("com.design.learn.dynamicregistration.Test").newInstance();
        System.out.println(MessageFormat.format("设置的student为：{0}", student));

    }
}
