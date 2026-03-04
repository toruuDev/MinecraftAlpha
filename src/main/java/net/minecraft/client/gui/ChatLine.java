package net.minecraft.client.gui;

public class ChatLine {
	public String message;
	public int updateCounter;

	public ChatLine(String message) {
		this.message = message;
		this.updateCounter = 0;
	}
}
