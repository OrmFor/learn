package com.design.learn.leetcode;

import java.util.Scanner;
import java.util.Stack;

public class dayhappy {

    public static int getNext(int n){
        int totalSum = 0 ;
        while ( n > 0){
           int d = n % 10;
           n = n/10;
           totalSum += d*d;
        }

        return totalSum;
    }

    public static boolean isHappy(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }


    public static void main(String[] args){
        System.out.println("请输入以下内容，输入回车键结束：");
        StringBuilder stringbuilder = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            String text = scanner.next().trim();
            System.out.println(text);
            if (text.length() == 0)
            {
                break;
            }
            stringbuilder.append(text);
        }
        int n = Integer.valueOf(stringbuilder.toString());

        System.out.println(dayhappy.isHappy(n));

    }
}
