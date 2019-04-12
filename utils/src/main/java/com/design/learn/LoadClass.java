package com.design.learn;

import javax.naming.Context;
import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class LoadClass {

    public static void scanAllClasses() {
        String url = getClassPath();
        List<String> classes = getClassesList(url);
        // 遍历classes，如果发现@Component就注入到容器中
        //  scanComponent2Container(classes);

    }

    private static String getClassPath() {
        String url = URLDecoder.decode(Context.class.getResource("/").getPath());
        if (url.startsWith("/")) {
            url = url.replaceFirst("/", "");
        }
        url = url.replaceAll("/", "\\\\");
        return url;
    }

    public static List<String> getClassesList(String url) {
        File file = new File(url);
        List<String> classes = getAllClass(file);
        for (int i = 0; i < classes.size(); i++) {
            classes.set(i, classes.get(i).replace(url, "").replace(".class", "").replace("\\", "."));
        }
        return classes;
    }


    private static List<String> getAllClass(File file) {
        List<String> ret = new ArrayList<>();
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            for (File file1 : list) {
                List<String> j = getAllClass(file1);
                ret.addAll(j);
            }
        } else {

            ret.add(file.getAbsolutePath());
        }
        return ret;
    }

}
