package net.minecraft.util;

import org.lwjgl.opengl.GL;

public class OpenGlCapsChecker {

	// rewritten by toru

	private static boolean tryCheckOcclusionCapable = false;

	public static boolean checkARBOcclusion() {
		return tryCheckOcclusionCapable && GL.getCapabilities().GL_ARB_occlusion_query;
	}
}