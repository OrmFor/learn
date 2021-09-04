package com.design.learn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class maopao {

    public static void main(String[] args) {
        //冒泡排序算法
//        Scanner scanner = new Scanner(System.in);
//        String str = scanner.nextLine();
//        scanner.close();
//        String[] strs = str.split(",");
//        System.out.println("输入长度：" + strs.length);
//        int[] numbers = new int[strs.length];
//        for (int l = 0; l < strs.length; l++) {
//            numbers[l] = Integer.valueOf(strs[l]);
//        }
//
//        int i, j;
//        for (i = 0; i < numbers.length - 1; i++) {
//            for (j = 0; j < numbers.length - 1 - i; j++) {
//                if (numbers[j] > numbers[j + 1]) {
//                    int temp = numbers[j];
//                    numbers[j] = numbers[j + 1];
//                    numbers[j + 1] = temp;
//                }
//            }
//        }
//        System.out.println("从小到大排序后的结果是:");
//        for (i = 0; i < numbers.length; i++) {
//            System.out.print(numbers[i] + " ");
//        }

        // maopao.sort();
        //  sushu(100,200);
        //shuixianhua(153);
    //    fenjie(90);  //欧几里得分解
//
//        System.out.println(153/100);
//        System.out.println(153%100 /10);
//        System.out.println(153%10);

       // commondivisor(9,27);
        commonMultiple(14,27);
    }


    public static void sort() {
        int[] n = {99, 354, 5, 667, 234, 2};

        for (int i = 0; i < n.length - 1; i++) {
            for (int j = 0; j < n.length - 1 - i; j++) {
                if (n[j] > n[j + 1]) {
                    int temp = n[j];
                    n[j] = n[j + 1];
                    n[j + 1] = temp;
                }
            }
        }
        for (int j = 0; j < n.length - 1; j++) {
            System.out.println(n[j]);
        }
    }


    public static void sushu(int n, int m) {
        int count = 0;
        for (int i = n; i < m; i++) {
            boolean flag = false;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            }
            if (flag) {
                count++;
                System.out.println("素数:" + i);
            }
        }
        System.out.println("个数" + count);

    }

    public static void shuixianhua(int n) {
        int a, b, c;
        String nn = n + "";
        int length = nn.trim().length();
        List<Integer> arrList = new ArrayList<Integer>();
        for (int i = 0; i <= length; i++) {
            int k = 0;
            int t = length - i - 1;
            System.out.println(t);
            if (i == 1) {
                k = n / (10 << (length - i - 1));

            } else {
                k = (n % (10 << (length) >> (i - 1))) / (10 << (length - i + 1));

            }
            System.out.println(k);
            arrList.add(k);
        }

        int k = 0;
        for (int i = 0; i < arrList.size(); i++) {
            k += arrList.get(i) * arrList.get(i) * arrList.get(i);
        }

        if (n == k) {
            System.out.println(n + "是水仙花数");
        }
    }


    public static void fenjie(int n) {
        int k = 2;
        while (k <= n) {
            if (k == n) {
                System.out.println(n);
                break;
            } else if (n % k == 0) {
                System.out.print(k + "*");
                n = n / k;
            } else {
                k++;
            }

        }
    }


    //最大公约数 效率比较低
    public static int commondivisor(int n, int m){
        //比较最大值
        int max = n > m ? n : m;
        int min = n < m ? n : m;
        int divisor = 1;
        for(int i = min ; i>1 ; i--){
            if((max % i == 0) && (min % i == 0) ){
                divisor = i;
                System.out.println(i);
                break;
            }
        }
        return divisor;
    }

    public static int commonMultiple(int n,int m){
        int divisor = maopao.commondivisor(n,m);
        int l= n*m/divisor;
        System.out.println(l);
        return l ;
    }
}