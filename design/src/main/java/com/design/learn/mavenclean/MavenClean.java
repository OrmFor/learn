package com.design.learn.mavenclean;

import java.io.File;

public class MavenClean {

    public static final String UNKNOWN = "unknown";

    public static final String FILE_PATH = "F:\\repository";

   // public static final String SUFFIX_REMOTE = "_remote.repositories";

    public static final String SUFFIX = ".lastUpdated";

    public static int COUNT = 0;
    public static int SUM = 0;
    public static int SUM_BLANK = 0;
    public static  int i = 0;
    public static void main(String[] args) {
        deleteErrorFile(new File(FILE_PATH));
        System.out.println(COUNT+"个错误文件");
        System.out.println(SUM+"个UNKNOWN文件");
        System.out.println(SUM_BLANK+"个空白文件");
    }


    public static void deleteErrorFile(File file) {
       // System.out.println(i++);
        if (file.isDirectory()) {
            if (file.getName().equals(UNKNOWN)) {
                if (file.delete()) {
                    SUM++;
                }
            }else{
                String[] list = file.list();
                if(list == null || list.length == 0){
                    if(file.delete()){
                        SUM_BLANK++;
                        deleteErrorFile(file.getParentFile());
                    }
                }else{
                    for (String childrenFile : list){
                        deleteErrorFile(new File(file,childrenFile));
                    }
                }
            }
        }else{
            if(file.getName().endsWith(SUFFIX)
                    //|| file.getName().endsWith(SUFFIX_REMOTE)
            ){
                if(file.delete()){
                    COUNT++;
                    String[] list =file.getParentFile().list();
                    if(list == null || list.length == 0){
                        deleteErrorFile(file.getParentFile());
                    }
                }
            }
        }
    }
}
