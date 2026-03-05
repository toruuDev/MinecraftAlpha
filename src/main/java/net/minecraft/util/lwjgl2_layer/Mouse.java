package net.minecraft.util.lwjgl2_layer;

import org.lwjgl.glfw.GLFW;

import java.util.ArrayDeque;
import java.util.Queue;

// NOT apart of original minecraft source code
// lwjgl2 like Mouse wrapper

public class Mouse {

    private static long window;

    private static class MouseEvent {
        int button;
        boolean state;
        double x;
        double y;

        MouseEvent(int button, boolean state, double x, double y) {
            this.button = button;
            this.state = state;
            this.x = x;
            this.y = y;
        }
    }

    private static final Queue<MouseEvent> eventQueue = new ArrayDeque<>();
    private static MouseEvent currentEvent;

    private static double mouseX;
    private static double mouseY;

    public static void initialize(long windowHandle) {
        window = windowHandle;

        GLFW.glfwSetCursorPosCallback(window, (w, x, y) -> {
            mouseX = x;
            mouseY = y;
        });

        GLFW.glfwSetMouseButtonCallback(window, (w, button, action, mods) -> {
            boolean pressed = action == GLFW.GLFW_PRESS;
            eventQueue.add(new MouseEvent(button, pressed, mouseX, mouseY));
        });
    }

    public static boolean next() {
        currentEvent = eventQueue.poll();
        return currentEvent != null;
    }

    public static int getEventButton() {
        return currentEvent != null ? currentEvent.button : 0;
    }

    public static boolean getEventButtonState() {
        return currentEvent != null && currentEvent.state;
    }

    public static int getEventX() {
        return (int) mouseX;
    }

    public static int getEventY() {
        int[] height = new int[1];
        GLFW.glfwGetWindowSize(window, new int[1], height);

        return height[0] - (int) mouseY;
    }

    public static int getX() {
        double[] x = new double[1];
        double[] y = new double[1];
        GLFW.glfwGetCursorPos(window, x, y);
        return (int)x[0];
    }

    public static int getY() {
        double[] x = new double[1];
        double[] y = new double[1];
        GLFW.glfwGetCursorPos(window, x, y);
        return (int)y[0];
    }

    public static boolean isButtonDown(int button) {
        return GLFW.glfwGetMouseButton(window, button) == GLFW.GLFW_PRESS;
    }

    public static void destroy() {
        eventQueue.clear();
    }
}