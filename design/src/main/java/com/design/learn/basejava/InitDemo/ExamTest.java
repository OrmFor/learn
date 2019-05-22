package com.design.learn.basejava.InitDemo;

public class ExamTest {

    public static void main(String[] args) {

        System.out.println(new Wolf("灰太狼", 32.3));

    }

}

class Animal {
    private String desc;

    public Animal() {

        this.desc = getDesc();

    }

    //会被子类覆盖  真实调用子类的getDesc()
    public String getDesc() {

        return "Animal";

    }

    public String toString() {
        return desc;

    }

}

class Wolf extends Animal {
    private String name;
    private double weight;

    public Wolf(String name, double weight) {

        super();//此处会调用getDesc()

        this.name = name;

        this.weight = weight;

    }

    @Override
    public String getDesc() {

        return "Wolf[name=" + name + ", weight=" + weight + "]";

    }

}