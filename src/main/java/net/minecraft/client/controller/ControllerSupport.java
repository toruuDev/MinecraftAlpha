package net.minecraft.client.controller;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiInventory;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Mouse;

/*
    made by toruuDev
    NOT included in Original Minecraft Code
*/

public class ControllerSupport {
    private Minecraft mc;
    private Controller controller;

    private boolean[] lastButtons = new boolean[16];

    private float deadzone = 0.2f;
    private float lookSensitivity;

    // virtual cursor
    private float cursorX;
    private float cursorY;
    private float cursorSpeed;

    public ControllerSupport(Minecraft mc) {
        this.mc = mc;

        initialize();
    }

    private boolean pressedOnce(int button) {
        boolean pressed = controller.isButtonPressed(button);
        boolean result = pressed && !lastButtons[button];
        lastButtons[button] = pressed;
        return result;
    }

    public boolean controllerEnabled() {
        return mc.options.controllerEnabled;
    }

    private void initialize() {
        try {
            Controllers.create();

            // dont fucking know how this works but it does
            // and im not touching it
            // i dont care FIGHT ME - toru

            for (int i = 0; i < Controllers.getControllerCount(); i++) {

                Controller gameController = Controllers.getController(i);

                if (gameController.getAxisCount() >= 4 && gameController.getButtonCount() >= 10) {
                    controller = gameController;

                    mc.ingameGUI.addChatMessage(
                            "Controller connected: " + controller.getName()
                    );

                    if(!controllerEnabled()) {
                        mc.ingameGUI.addChatMessage("Controller is not enabled. go into settings and enable it ");
                    }

                    System.out.println("Using controller: " + controller.getName());

                    cursorX = Mouse.getX();
                    cursorY = Mouse.getY();
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        if (controller == null) {
            return;
        }

        if(!controllerEnabled()) {
            return;
        }

        lookSensitivity = mc.options.controllerSensitivity * 10F;
        cursorSpeed = mc.options.interfaceSensitivity * 20F;

        Controllers.poll();

        movement();
        camera();
        buttons();
        triggers();
        bumpers();
        guiCursor();
    }

    private float applyDeadzone(float value) {
        return Math.abs(value) < deadzone ? 0 : value;
    }

    private boolean lastAttack = false;
    private boolean lastUse = false;

    private void triggers() {
        if (controller == null) return;
        if(!controllerEnabled()) {
            return;
        }

        float trigger = controller.getZAxisValue();

        boolean attackTrigger = trigger < -0.3f;
        boolean useTrigger = trigger > 0.3f;

        if (mc.inGameHasFocus && mc.currentScreen == null) {

            if (attackTrigger) {
                MouseController.holdLeftMouse();
            } else {
                MouseController.releaseLeftMouse();
            }

            if (useTrigger) {
                MouseController.holdRightMouse();
            } else {
                MouseController.releaseRightMouse();
            }

        } else {

            MouseController.releaseLeftMouse();
            MouseController.releaseRightMouse();
        }
    }

    private void movement() {
        if(mc.currentScreen != null) return;

        float moveX = applyDeadzone(controller.getXAxisValue());
        float moveY = applyDeadzone(controller.getYAxisValue());

        mc.thePlayer.movementInput.moveStrafe = -moveX;
        mc.thePlayer.movementInput.moveForward = -moveY;

        mc.thePlayer.movementInput.sneak = controller.isButtonPressed(ControllerButtons.BACK.get());
    }

    private void camera() {
        if(mc.currentScreen != null) return;

        float lookX = applyDeadzone(controller.getRXAxisValue());
        float lookY = applyDeadzone(controller.getRYAxisValue());

        mc.thePlayer.rotationYaw += lookX * lookSensitivity;
        mc.thePlayer.rotationPitch += lookY * lookSensitivity;

        if(mc.thePlayer.rotationPitch > 90F) mc.thePlayer.rotationPitch = 90F;
        if(mc.thePlayer.rotationPitch < -90F) mc.thePlayer.rotationPitch = -90F;
    }

    private void bumpers() {

        if(mc.currentScreen != null) return;

        if (pressedOnce(ControllerButtons.LB.get())) {
            mc.thePlayer.inventory.currentItem--;
            if (mc.thePlayer.inventory.currentItem < 0)
                mc.thePlayer.inventory.currentItem = 8;
        }

        if (pressedOnce(ControllerButtons.RB.get())) {
            mc.thePlayer.inventory.currentItem++;
            if (mc.thePlayer.inventory.currentItem > 8)
                mc.thePlayer.inventory.currentItem = 0;
        }
    }

    private void buttons() {
        if(mc.thePlayer == null) return;
        mc.thePlayer.movementInput.jump = controller.isButtonPressed(ControllerButtons.A.get());

        if (pressedOnce(ControllerButtons.B.get()) && mc.currentScreen == null) {
            mc.thePlayer.dropPlayerItemWithRandomChoice(
                    mc.thePlayer.inventory.decrStackSize(
                            mc.thePlayer.inventory.currentItem, 1), false);
        }

        if (mc.currentScreen != null && pressedOnce(ControllerButtons.A.get())) {
            MouseController.clickLeftMouse();
        }

        if(pressedOnce(ControllerButtons.B.get())) {
            if(mc.currentScreen != null) {
                mc.displayGuiScreen(null);
            }
        }

        if (pressedOnce(ControllerButtons.Y.get())) {

            if (mc.currentScreen instanceof GuiInventory) {
                mc.displayGuiScreen(null);
            }
            else if (mc.currentScreen == null) {
                mc.displayGuiScreen(new GuiInventory(mc.thePlayer.inventory, new ItemStack[4]));
            }
        }
    }

    private void guiCursor() {

        if(mc.currentScreen == null) return;

        float moveX = applyDeadzone(controller.getXAxisValue());
        float moveY = applyDeadzone(controller.getYAxisValue());

        cursorX += moveX * cursorSpeed;
        cursorY += moveY * cursorSpeed;

        int width = mc.displayWidth;
        int height = mc.displayHeight;

        if(cursorX < 0) cursorX = 0;
        if(cursorY < 0) cursorY = 0;

        if(cursorX > width) cursorX = width;
        if(cursorY > height) cursorY = height;

        Mouse.setCursorPosition((int)cursorX, height - (int)cursorY);
    }

    private enum ControllerButtons {

        A(0),
        B(1),
        X(2),
        Y(3),
        LB(4),
        RB(5),
        BACK(6),
        START(7),
        L3(8),
        R3(9);

        private final int value;

        ControllerButtons(int value) {
            this.value = value;
        }

        public int get() {
            return value;
        }
    }
}