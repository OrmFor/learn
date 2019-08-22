package com.design.learn.sort;

import java.util.Scanner;

/**
* @Author wwy
* @Description 堆排序
 *             利用平衡二叉树原理   left < root < right
 *             它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 *             利用比较大小  改变数组的 index
* @Date 16:22 2019/8/8
* @Param
* @return
**/
public class HeapSort {

    public static void heapify(int[] arrays,int currentRootNode , int size){

        if (currentRootNode < size) {
            //左子树和右字数的位置   root 为 0 , left 1 , right 2
            int left = 2 * currentRootNode + 1;
            int right = 2 * currentRootNode + 2;

            //把当前父节点位置看成是最大的
            int max = currentRootNode;

            if (left < size) {
                //如果比当前根元素要大，记录它的位置
                if (arrays[max] < arrays[left]) {
                    max = left;
                }
            }
            if (right < size) {
                //如果比当前根元素要大，记录它的位置
                if (arrays[max] < arrays[right]) {
                    max = right;
                }
            }
            //如果最大的不是根元素位置，那么就交换
            if (max != currentRootNode) {
                int temp = arrays[max];
                arrays[max] = arrays[currentRootNode];
                arrays[currentRootNode] = temp;

                //继续比较，直到完成一次建堆
                heapify(arrays, max, size);
            }
        }


    }

    public static void maxHeapify(int[] arrays, int size) {

        // 从数组的尾部开始，直到第一个元素(角标为0)
        for (int i = size - 1; i >= 0; i--) {
            heapify(arrays, i, size);
        }

    }


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int[] arrays =new int[10];
        System.out.println("请输入:");
        for(int i = 0 ; scanner.hasNext() ; i++){
            arrays[i] = scanner.nextInt();
            if(arrays[9] != 0){
                break;
            }
        }
        //每次建堆就可以排除一个元素了
        maxHeapify(arrays, arrays.length );
        int size = arrays.length -1 ;

        for (int i = 0; i < arrays.length; i++) {

            //交换
            int temp = arrays[0];
            arrays[0] = arrays[(arrays.length - 1) - i];
            arrays[(arrays.length - 1) - i] = temp;
            heapify(arrays,0,size);
            size--;

        }

        for(int i = 0 ; i< arrays.length ; i++)
            System.out.println(arrays[i]);

    }


}
