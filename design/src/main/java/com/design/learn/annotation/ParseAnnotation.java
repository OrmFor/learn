package com.design.learn.annotation;

import com.design.learn.LoadClass;
import com.design.learn.annotation.MethodInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ParseAnnotation {

    public void parseAnnotation() {

        try {
            //获取项目路径下所有的class文件
            /*String url = this.getClass().getResource("/").getPath().substring(1);
            System.out.println(MessageFormat.format("url=[{0}]",url));
            List<String> classes = LoadClass.getClassesList(url);
            List<Class> classList = new ArrayList<>();
            for(String urlClass : classes){
                Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass(urlClass);
                classList.add(aClass);
            }
            System.out.println(classList.isEmpty());*/
            List<Class> classList = new ArrayList<>();
            classList.add(ClassLoader.getSystemClassLoader().loadClass("com.design.learn.annotation.AnnotationExample"));
            for (Class clazz : classList) {
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(MethodInfo.class)) {
                        for (Annotation anno : method.getDeclaredAnnotations()) {
                            System.out.println("annotations = " + anno);
                        }

                        MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);

                        if (!methodAnno.version().equals("1.0.0")) {
                            System.out.println(MessageFormat.format("Method with version no 1.0.0 : [{0}] " , method));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
