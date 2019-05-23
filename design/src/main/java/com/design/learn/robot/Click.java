package com.design.learn.robot;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Click {

    private static Robot robot;

    public static void main(String[] args) {
        try {
            robot = new Robot();
            Thread.sleep(3000);

            robot.mouseMove(120, 230);
            long now = System.currentTimeMillis();

            while (System.currentTimeMillis() - now > 3000) {
                Thread.sleep(50);
                robot.mousePress(KeyEvent.BUTTON1_MASK);
                robot.mouseRelease(KeyEvent.BUTTON1_MASK);
            }
            System.out.print("over");
        } catch (Exception e) {

        }
    }

}
