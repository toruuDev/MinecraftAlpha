package net.minecraft.util;

import org.lwjgl.glfw.GLFW;

public class MouseHelper {

	// rewritten by toru

	private long window;

	private double lastMouseX;
	private double lastMouseY;

	public int deltaX;
	public int deltaY;

	public MouseHelper(long window) {
		this.window = window;

		double[] x = new double[1];
		double[] y = new double[1];

		GLFW.glfwGetCursorPos(window, x, y);

		lastMouseX = x[0];
		lastMouseY = y[0];
	}

	public void mouseXYChange() {

		double[] x = new double[1];
		double[] y = new double[1];

		GLFW.glfwGetCursorPos(window, x, y);

		double mouseX = x[0];
		double mouseY = y[0];

		deltaX = (int)(mouseX - lastMouseX);

		deltaY = (int)(lastMouseY - mouseY);

		lastMouseX = mouseX;
		lastMouseY = mouseY;
	}

	public void grabMouseCursor() {
		GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);

		double[] x = new double[1];
		double[] y = new double[1];

		GLFW.glfwGetCursorPos(window, x, y);

		lastMouseX = x[0];
		lastMouseY = y[0];

		deltaX = 0;
		deltaY = 0;
	}

	public void ungrabMouseCursor() {
		GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
	}
}