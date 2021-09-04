package com.design.learn.leetcode;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public static void main(String[] args){


    }

    public  ListNode removeElements(ListNode head, int val) {
        ListNode listNode = new ListNode(-1);
        listNode.next = head;

        ListNode pre  = listNode;
        while (pre.next != null){
            if(pre.next.val == val){
                pre.next = pre.next.next;
            }else{
                pre = pre.next;
            }

        }

        return listNode.next;

    }
}
