package net.minecraft.client.gui.canvas;

import net.minecraft.client.MinecraftApplet;

import java.awt.Canvas;

public class CanvasMinecraftApplet extends Canvas {
	final MinecraftApplet mcApplet;

	public CanvasMinecraftApplet(MinecraftApplet var1) {
		this.mcApplet = var1;
	}

	public synchronized void addNotify() {
		super.addNotify();
		this.mcApplet.startMainThread();
	}

	public synchronized void removeNotify() {
		this.mcApplet.shutdown();
		super.removeNotify();
	}
}
