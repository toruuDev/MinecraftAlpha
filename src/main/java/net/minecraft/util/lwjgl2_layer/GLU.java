package net.minecraft.util.lwjgl2_layer;

import org.lwjgl.opengl.GL11;

// NOT apart of original minecraft source code
// lwjgl2 like GLU wrapper

public class GLU {

    public static void gluPerspective(float fovY, float aspect, float zNear, float zFar) {
        float fH = (float)Math.tan(Math.toRadians(fovY) / 2) * zNear;
        float fW = fH * aspect;

        GL11.glFrustum(-fW, fW, -fH, fH, zNear, zFar);
    }
}
