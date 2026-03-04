package net.minecraft.client.gui.canvas;

import java.awt.Canvas;
import java.awt.Dimension;

public class CanvasCrashReport extends Canvas {
	public CanvasCrashReport(int var1) {
		this.setPreferredSize(new Dimension(var1, var1));
		this.setMinimumSize(new Dimension(var1, var1));
	}
}
