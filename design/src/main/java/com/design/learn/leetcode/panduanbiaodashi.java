package com.design.learn.leetcode;

import java.util.Scanner;

public class panduanbiaodashi {

    public static void main(String[] args) {
        //冒泡排序算法
      //  int[] numbers = new int[]{1, 5, 8, 2, 3, 9, 4};

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        scanner.close();
        String[] strs = str.split(",");
        System.out.println("输入长度："+strs.length);
        int[] numbers = new int[strs.length];
        for(int l = 0 ; l<strs.length ; l++){
            numbers[l] = Integer.valueOf(strs[l]);
         }

        int i, j;
        for (i = 0; i < numbers.length - 1; i++) {
            for (j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        System.out.println("从小到大排序后的结果是:");
        for (i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }

}
