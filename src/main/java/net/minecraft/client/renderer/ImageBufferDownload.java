package net.minecraft.client.renderer;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;

public class ImageBufferDownload implements ImageBuffer {
	private int[] imageData;
	private int imageWidth;
	private int imageHeight;

	public BufferedImage parseUserSkin(BufferedImage image) {
		// toru : this destroyed new skin models
		// so i recoded it to support both!
		// also why is notch so bad at coding this looked so silly

		if (image == null) return null;

		int width = image.getWidth();
		int height = image.getHeight();

		// if 64x32 then expand to 64x64
		if (width == 64 && height == 32) {
			BufferedImage newImage = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = newImage.getGraphics();
			graphics.drawImage(image, 0, 0, null);
			graphics.dispose();
			image = newImage;
		}

		// if already 64x64 modern then just use it directly
		if(image.getWidth() != 64 || image.getHeight() != 64) {
			BufferedImage resizedImage = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = resizedImage.getGraphics();
			graphics.drawImage(image, 0, 0, 64, 64, null);
			graphics.dispose();
			image = resizedImage;
		}

		return image;
	}
}
