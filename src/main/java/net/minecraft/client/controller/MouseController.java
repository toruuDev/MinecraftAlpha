package net.minecraft.client.controller;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class MouseController {

    private static Robot robot;

    private static boolean leftHeld = false;
    private static boolean rightHeld = false;

    static {
        try {
            robot = new Robot();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void holdLeftMouse() {
        if(robot == null) return;

        if(!leftHeld) {
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            leftHeld = true;
        }
    }

    public static void releaseLeftMouse() {
        if(robot == null) return;

        if(leftHeld) {
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            leftHeld = false;
        }
    }

    public static void holdRightMouse() {
        if(robot == null) return;

        if(!rightHeld) {
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            rightHeld = true;
        }
    }

    public static void releaseRightMouse() {
        if(robot == null) return;

        if(rightHeld) {
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
            rightHeld = false;
        }
    }
}