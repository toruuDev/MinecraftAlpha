package net.minecraft.util.lwjgl2_layer;

import org.lwjgl.glfw.GLFW;

import java.util.ArrayDeque;
import java.util.Queue;

// NOT apart of original minecraft source code
// lwjgl2 like keyboard wrapper

public class Keyboard {
    private static long window ;

    private static class KeyEvent {
        int key;
        boolean state;
        char character;

        KeyEvent(int key, boolean state, char character) {
            this.key = key;
            this.state = state;
            this.character = character;
        }
    }

    private static final Queue<KeyEvent> eventQueue = new ArrayDeque<>();
    private static KeyEvent currentEvent;
    private static boolean repeatEnabled = false;

    public static final int KEY_ESCAPE = GLFW.GLFW_KEY_ESCAPE;

    public static final int KEY_1 = GLFW.GLFW_KEY_1;
    public static final int KEY_2 = GLFW.GLFW_KEY_2;
    public static final int KEY_3 = GLFW.GLFW_KEY_3;
    public static final int KEY_4 = GLFW.GLFW_KEY_4;
    public static final int KEY_5 = GLFW.GLFW_KEY_5;
    public static final int KEY_6 = GLFW.GLFW_KEY_6;
    public static final int KEY_7 = GLFW.GLFW_KEY_7;
    public static final int KEY_8 = GLFW.GLFW_KEY_8;
    public static final int KEY_9 = GLFW.GLFW_KEY_9;
    public static final int KEY_0 = GLFW.GLFW_KEY_0;

    public static final int KEY_MINUS = GLFW.GLFW_KEY_MINUS;
    public static final int KEY_EQUALS = GLFW.GLFW_KEY_EQUAL;
    public static final int KEY_BACK = GLFW.GLFW_KEY_BACKSPACE;
    public static final int KEY_TAB = GLFW.GLFW_KEY_TAB;

    public static final int KEY_Q = GLFW.GLFW_KEY_Q;
    public static final int KEY_W = GLFW.GLFW_KEY_W;
    public static final int KEY_E = GLFW.GLFW_KEY_E;
    public static final int KEY_R = GLFW.GLFW_KEY_R;
    public static final int KEY_T = GLFW.GLFW_KEY_T;
    public static final int KEY_Y = GLFW.GLFW_KEY_Y;
    public static final int KEY_U = GLFW.GLFW_KEY_U;
    public static final int KEY_I = GLFW.GLFW_KEY_I;
    public static final int KEY_O = GLFW.GLFW_KEY_O;
    public static final int KEY_P = GLFW.GLFW_KEY_P;

    public static final int KEY_LBRACKET = GLFW.GLFW_KEY_LEFT_BRACKET;
    public static final int KEY_RBRACKET = GLFW.GLFW_KEY_RIGHT_BRACKET;

    public static final int KEY_RETURN = GLFW.GLFW_KEY_ENTER;

    public static final int KEY_LCONTROL = GLFW.GLFW_KEY_LEFT_CONTROL;
    public static final int KEY_RCONTROL = GLFW.GLFW_KEY_RIGHT_CONTROL;

    public static final int KEY_A = GLFW.GLFW_KEY_A;
    public static final int KEY_S = GLFW.GLFW_KEY_S;
    public static final int KEY_D = GLFW.GLFW_KEY_D;
    public static final int KEY_F = GLFW.GLFW_KEY_F;
    public static final int KEY_G = GLFW.GLFW_KEY_G;
    public static final int KEY_H = GLFW.GLFW_KEY_H;
    public static final int KEY_J = GLFW.GLFW_KEY_J;
    public static final int KEY_K = GLFW.GLFW_KEY_K;
    public static final int KEY_L = GLFW.GLFW_KEY_L;

    public static final int KEY_SEMICOLON = GLFW.GLFW_KEY_SEMICOLON;
    public static final int KEY_APOSTROPHE = GLFW.GLFW_KEY_APOSTROPHE;
    public static final int KEY_GRAVE = GLFW.GLFW_KEY_GRAVE_ACCENT;

    public static final int KEY_LSHIFT = GLFW.GLFW_KEY_LEFT_SHIFT;
    public static final int KEY_RSHIFT = GLFW.GLFW_KEY_RIGHT_SHIFT;

    public static final int KEY_BACKSLASH = GLFW.GLFW_KEY_BACKSLASH;

    public static final int KEY_Z = GLFW.GLFW_KEY_Z;
    public static final int KEY_X = GLFW.GLFW_KEY_X;
    public static final int KEY_C = GLFW.GLFW_KEY_C;
    public static final int KEY_V = GLFW.GLFW_KEY_V;
    public static final int KEY_B = GLFW.GLFW_KEY_B;
    public static final int KEY_N = GLFW.GLFW_KEY_N;
    public static final int KEY_M = GLFW.GLFW_KEY_M;

    public static final int KEY_COMMA = GLFW.GLFW_KEY_COMMA;
    public static final int KEY_PERIOD = GLFW.GLFW_KEY_PERIOD;
    public static final int KEY_SLASH = GLFW.GLFW_KEY_SLASH;

    public static final int KEY_SPACE = GLFW.GLFW_KEY_SPACE;

    public static final int KEY_LMENU = GLFW.GLFW_KEY_LEFT_ALT;
    public static final int KEY_RMENU = GLFW.GLFW_KEY_RIGHT_ALT;

    public static final int KEY_F1 = GLFW.GLFW_KEY_F1;
    public static final int KEY_F2 = GLFW.GLFW_KEY_F2;
    public static final int KEY_F3 = GLFW.GLFW_KEY_F3;
    public static final int KEY_F4 = GLFW.GLFW_KEY_F4;
    public static final int KEY_F5 = GLFW.GLFW_KEY_F5;
    public static final int KEY_F6 = GLFW.GLFW_KEY_F6;
    public static final int KEY_F7 = GLFW.GLFW_KEY_F7;
    public static final int KEY_F8 = GLFW.GLFW_KEY_F8;
    public static final int KEY_F9 = GLFW.GLFW_KEY_F9;
    public static final int KEY_F10 = GLFW.GLFW_KEY_F10;
    public static final int KEY_F11 = GLFW.GLFW_KEY_F11;
    public static final int KEY_F12 = GLFW.GLFW_KEY_F12;

    public static final int KEY_UP = GLFW.GLFW_KEY_UP;
    public static final int KEY_DOWN = GLFW.GLFW_KEY_DOWN;
    public static final int KEY_LEFT = GLFW.GLFW_KEY_LEFT;
    public static final int KEY_RIGHT = GLFW.GLFW_KEY_RIGHT;

    public static final int KEY_HOME = GLFW.GLFW_KEY_HOME;
    public static final int KEY_END = GLFW.GLFW_KEY_END;
    public static final int KEY_INSERT = GLFW.GLFW_KEY_INSERT;
    public static final int KEY_DELETE = GLFW.GLFW_KEY_DELETE;
    public static final int KEY_PRIOR = GLFW.GLFW_KEY_PAGE_UP;
    public static final int KEY_NEXT = GLFW.GLFW_KEY_PAGE_DOWN;

    public static final int KEY_CAPITAL = GLFW.GLFW_KEY_CAPS_LOCK;

    public static void init(long windowHandle) {
        window = windowHandle;

        GLFW.glfwSetKeyCallback(window, (w, key, scancode, action, mods) -> {

            if (action == GLFW.GLFW_PRESS ||
                    (action == GLFW.GLFW_REPEAT && repeatEnabled) ||
                    action == GLFW.GLFW_RELEASE) {

                boolean pressed = action != GLFW.GLFW_RELEASE;
                eventQueue.add(new KeyEvent(key, pressed, '\0'));
            }

        });

        GLFW.glfwSetCharCallback(window, (w, codepoint) -> {
            eventQueue.add(new KeyEvent(0, true, (char) codepoint));
        });
    }

    public static void enableRepeatEvents(boolean enable) {
        repeatEnabled = enable;
    }

    public static boolean next() {
        currentEvent = eventQueue.poll();
        return currentEvent != null;
    }

    public static int getEventKey() {
        return currentEvent == null ? 0 : currentEvent.key;
    }

    public static boolean getEventKeyState() {
        return currentEvent != null && currentEvent.state;
    }

    public static char getEventCharacter() {
        return currentEvent == null ? '\0' : currentEvent.character;
    }

    public static boolean isKeyDown(int key) {
        return GLFW.glfwGetKey(window, key) == GLFW.GLFW_PRESS;
    }

    public static void destroy() {
        //
    }

    public static String getKeyName(int key) {
        switch (key) {
            case KEY_ESCAPE: return "ESCAPE";
            case KEY_RETURN: return "ENTER";
            case KEY_BACK: return "BACKSPACE";
            case KEY_TAB: return "TAB";
            case KEY_SPACE: return "SPACE";

            case KEY_LSHIFT: return "LSHIFT";
            case KEY_RSHIFT: return "RSHIFT";
            case KEY_LCONTROL: return "LCONTROL";
            case KEY_RCONTROL: return "RCONTROL";

            case KEY_UP: return "UP";
            case KEY_DOWN: return "DOWN";
            case KEY_LEFT: return "LEFT";
            case KEY_RIGHT: return "RIGHT";

            case KEY_F1: return "F1";
            case KEY_F2: return "F2";
            case KEY_F3: return "F3";
            case KEY_F4: return "F4";
            case KEY_F5: return "F5";
            case KEY_F6: return "F6";
            case KEY_F7: return "F7";
            case KEY_F8: return "F8";
            case KEY_F9: return "F9";
            case KEY_F10: return "F10";
            case KEY_F11: return "F11";
            case KEY_F12: return "F12";
        }

        String name = GLFW.glfwGetKeyName(key, 0);
        if (name != null) {
            return name.toUpperCase();
        }

        return "KEY_" + key;
    }
}
