package com.design.learn.sort;

import java.util.concurrent.ConcurrentHashMap;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-8, 49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22};
        QuickSort(arr, 0, arr.length - 1);
        System.out.println("排序后:");
        for (int i : arr) {
            System.out.println(i);
        }

    }


    public static void QuickSort(int[] a, int left, int right) {
        //如果left等于right，即数组只有一个元素，直接返回
        if (left >= right) {
            return;
        }
        //设置最左边的元素为基准值
        int key = a[left];
        //数组中比key小的放在左边，比key大的放在右边，key值下标为i
        int i = left;
        int j = right;
        while (i < j) {
            //j向左移，直到遇到比key小的值
            while (a[j] >= key && i < j) {
                j--;
            }
            //i向右移，直到遇到比key大的值
            while (a[i] <= key && i < j) {
                i++;
            }
            //i和j指向的元素交换
            if (i < j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        a[left] = a[i];
        a[i] = key;
        QuickSort(a, left, i - 1);
        QuickSort(a, i + 1, right);
    }

}
