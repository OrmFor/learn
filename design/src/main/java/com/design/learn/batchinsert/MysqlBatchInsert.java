package com.design.learn.batchinsert;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class MysqlBatchInsert {

    public static final String url = "jdbc:mysql://localhost:3309/test?";
    // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?
    boolean useUnicode = true;
    String characterEncoding = "utf8";
    // 避免中文乱码要指定useUnicode和characterEncoding
    public static final String user = "root";
    //连接数据库的用户名，一般都设置为root
    public static final String password = "password";

    public static void main(String[] args) throws Exception {
       insert();
    }


    public static void insert() throws Exception{
        java.sql.Connection conn = null;
        Class.forName("com.mysql.jdbc.Driver");

        // 开时时间
        Long begin = new Date().getTime();
        // sql前缀
        String prefix = "INSERT INTO pt_ten_millions_original (goods_id, goods_name, in_date) VALUES ";
        try {
            conn = DriverManager.getConnection(url , user , password);

            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // Statement st = conn.createStatement();
            // 比起st，pst会更好些
            PreparedStatement pst = conn.prepareStatement("");
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 100000; i++) {
                // 第次提交步长
                for (int j = 1; j <= 1000; j++) {
                    // 构建sql后缀
                    suffix.append("(rand_string(10), concat(rand_string(7), '@qq.com'),\n" +
                            "              rand_datetime()),");
                }
                // 构建完整sql
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
               // System.out.println(sql);
                // 添加执行sql
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }
            // 头等连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.out.println("cast : " + (end - begin) / 1000 + " ms");
    }


}
