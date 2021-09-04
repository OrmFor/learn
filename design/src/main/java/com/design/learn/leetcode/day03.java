package com.design.learn.leetcode;

import java.util.Arrays;
import java.util.Scanner;

public class day03 {

    /**
    * 输出素数
    **/
    public static int countPrimes(int n){
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim,true);
        for(int i = 2 ;i < n ; i++){
            if(isPrim[i]){
                for (int j = 2*i ; j<n ; j+=i){
                    isPrim[j] = false;
                }
            }
        }
        int count = 0;
        for(int i = 2 ; i<n ; i++){
            if(isPrim[i]){
                count++;
                System.out.println(i);
            }
        }
        return count;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
          int count = day03.countPrimes(n);
          System.out.println(count);
    }

}



