package com.design.learn.leetcode;

import java.util.Scanner;
import java.util.Stack;

public class day01 {
    public static boolean isValid(String s){
        Stack<Character> stack = new Stack<Character>();
        for(char c : s.toCharArray()){
            if(c == '('){
                stack.push(')');
            }else if(c == '['){
                stack.push(']');
            }else if(c == '{'){
                stack.push('}');
            }else if(stack.isEmpty() || c!=stack.pop()){ //先去判断栈中有没有值 再去pop比较
                return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args){
        System.out.println("请输入以下内容，输入“end”结束：");
        StringBuilder stringbuilder = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            String text = scanner.nextLine().trim();
            if ("end".equals(text))
            {
                break;
            }
            stringbuilder.append(text);
        }

        boolean s1 = day01.isValid(stringbuilder.toString());
        System.out.println(s1);
    }
}
