package com.design.learn.wx.lbs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.Set;

public class Json2Excel {
    public static void down(JSONArray array) {
        Set<String> keys = null;
        // 创建HSSFWorkbook对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("sheet0");

//        FileReader reader = new FileReader("c://resource.txt");
//        BufferedReader br = new BufferedReader(reader);
//        String str = null;
//        int roleNo = 0;
//        int rowNo = 0;
//        while ((str = br.readLine()) != null) {
//
//
//        }
//
//        br.close();
//        reader.close();

        int roleNo = 0;
        int rowNo = 0;
        for (int i = 0 ; i< array.size() ; i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            // 创建HSSFRow对象
            HSSFRow row = sheet.createRow(roleNo++);
            // 创建HSSFCell对象
            if (keys == null) {
                //标题
                keys = jsonObject.keySet();
                for (String s : keys) {
                    HSSFCell cell = row.createCell(rowNo++);
                    cell.setCellValue(s);
                }
                rowNo = 0;
                row = sheet.createRow(roleNo++);
            }

            for (String s : keys) {
                HSSFCell cell = row.createCell(rowNo++);
                if(s.equals("lng") || s.equals("lat")){
                    cell.setCellValue(jsonObject.getDoubleValue(s));
                }else{
                    cell.setCellValue(jsonObject.getString(s));
                }

            }
            rowNo = 0;
            System.out.println(rowNo);

        }
        // 输出Excel文件
        FileOutputStream output = null;
        try {
            output = new FileOutputStream("d://"+System.currentTimeMillis()+".xls");
            wb.write(output);
            wb.close();
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

