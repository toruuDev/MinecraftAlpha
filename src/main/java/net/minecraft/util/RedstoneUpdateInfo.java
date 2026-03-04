package net.minecraft.util;

public class RedstoneUpdateInfo {
	public int x;
	public int y;
	public int z;
	public long updateTime;

	public RedstoneUpdateInfo(int x, int y, int z, long updateTime) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.updateTime = updateTime;
	}
}
