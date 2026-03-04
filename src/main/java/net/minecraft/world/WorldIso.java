package net.minecraft.world;

import net.minecraft.isom.CanvasIsomPreview;
import net.minecraft.util.IChunkProvider;
import net.minecraft.world.chunk.ChunkLoader;
import net.minecraft.world.chunk.ChunkProviderIso;

import java.io.File;

public class WorldIso extends World {
	final CanvasIsomPreview isomPreview;

	public WorldIso(CanvasIsomPreview var1, File var2, String var3) {
		super(var2, var3);
		this.isomPreview = var1;
	}

	protected IChunkProvider getChunkProvider(File var1) {
		return new ChunkProviderIso(this, new ChunkLoader(var1, false));
	}
}
