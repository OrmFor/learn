package com.design.learn.leetcode;


import java.util.Scanner;

/**
* @Author wwy
* @Description  反转  利用数组尾遍历
* @Date 14:00 2020/7/3
* @Param
* @return
**/
public class Reverse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串(回车键结束)：");
        String s = scanner.nextLine();
        char[] a = s.toCharArray();
        String reverse = "";
        for (int i = a.length-1 ; i>=0 ; i--){
            reverse += a[i];
        }
        System.out.println("反转后字符串为：");
        System.out.println(reverse);

    }


}
