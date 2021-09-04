package com.design.learn.util;

import com.alibaba.fastjson.JSONObject;

import java.io.*;


/**
 * @Author wwy
 * @Description 将安卓日志转换成sql, 方便利用sql统计
 * @Date 10:46 2021/9/4
 * @Param
 * @return
 **/
public class JsonToSql {


    private static final String PATH = "G:\\file";

    private static final String PATH_SQL = PATH + "\\jsonToSql";

    public static void main(String[] args) throws Exception {
        System.out.println("========== JSON ---> 转换成 SQL 开始 ==========");
        File file = new File(PATH_SQL);
        if(!file.exists()){
            file.mkdirs();
        }
        readChildFileByLines(PATH);
        System.out.println("========== JSON ---> 转换成 SQL 结束 ==========");
    }

    public static void readChildFileByLines(String path){
        File file = new File(path);
        if(file.isDirectory()){
            String[] list = file.list();
            for (String childrenFile : list){
                System.out.println("fileName:"+childrenFile);
                if(childrenFile.endsWith(".txt")) {
                    readFileByLines(new File(file, childrenFile));
                }
            }
        }else{
            readFileByLines(file);
        }
    }


    public static void readFileByLines(File file) {
        //File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //System.out.println("line " + line + ": " + tempString);
                LogVO logVO = JSONObject.parseObject(tempString, LogVO.class);
                String abPath = file.getAbsolutePath();

                String jsonToStrPath = abPath.substring(0,abPath.lastIndexOf("\\")) +"\\jsonToSql\\"+file.getName().replace(".txt",".sql");

                if (line == 1) {
                    System.out.println(abPath);
                    jsonToSql(logVO, true,jsonToStrPath);
                } else {
                    jsonToSql(logVO, false,jsonToStrPath);

                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    private static void jsonToSql(LogVO logVO, boolean isFirst,String fileName) throws Exception {
        String sqlStr = "";
        if (isFirst) {
            sqlStr = "insert all \r\n";
        }

        sqlStr = sqlStr + " into log_android "
                + "(msgId, requestUrl,userId ,endTime,startTime,requestTime)"
                + " values ('" + logVO.getMsgId() + "','" + logVO.getRequestUrl() + "','"
                + logVO.getEndTime() + "','" + logVO.getRequestUrl() + ",'"
                + logVO.getStartTime() + "','" + logVO.getEndTime() + ",'"
                + logVO.getRequestTime()
                + "'),\r\n";
       // System.out.println(sqlStr);
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(sqlStr);
        bufferedWriter.close();

    }

}
