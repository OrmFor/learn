package com.design.learn.thread.threadlocaloom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalOOM {
    private static final int THREAD_LOOP_SIZE = 500;
    private static final int MAX_SIZE = 1000000;


    public static void main(String[] args) {
        final ThreadLocal<List<User>> threadLocal = new ThreadLocal<>();
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_SIZE);

        for (int i = 0; i < THREAD_LOOP_SIZE; i++) {
            executorService.submit(() -> {
                threadLocal.set(new ThreadLocalOOM().addUser());
                String t = Thread.currentThread().getName();
                System.out.println(t);
            });

            try {
                Thread.sleep(1000);

            } catch (Exception e) {

            }
        }
        executorService.shutdown();
    }

    private List<User> addUser() {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < MAX_SIZE; i++) {
            User user = new User(i, "老吴", "老吴" + i);
            System.out.println(user.toString());
            list.add(new User(i, "老吴", "老吴" + i));
        }
        return list;

    }


    @Data
    @AllArgsConstructor
    @ToString
    class User {
        private int age;
        private String name;
        private String pwd;
    }
}
