package net.minecraft.block;

import net.minecraft.entity.Entity;
import net.minecraft.material.Material;
import net.minecraft.world.World;

import java.util.Random;

public class BlockLeaves extends BlockLeavesBase {
	private int leafTexIndex;
	private int decayCounter = 0;

	protected BlockLeaves(int var1, int var2) {
		super(var1, var2, Material.leaves, false);
		this.leafTexIndex = var2;
		this.setTickOnLoad(true);
	}

	public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
		this.decayCounter = 0;
		this.updateCurrentLeaves(var1, var2, var3, var4);
		super.onNeighborBlockChange(var1, var2, var3, var4, var5);
	}

	public void updateConnectedLeaves(World var1, int var2, int var3, int var4, int var5) {
		if(var1.getBlockId(var2, var3, var4) == this.blockID) {
			int var6 = var1.getBlockMetadata(var2, var3, var4);
			if(var6 != 0 && var6 == var5 - 1) {
				this.updateCurrentLeaves(var1, var2, var3, var4);
			}
		}
	}

	public void updateCurrentLeaves(World var1, int var2, int var3, int var4) {
		if(this.decayCounter++ < 100) {
			int var5 = var1.getBlockMaterial(var2, var3 - 1, var4).isSolid() ? 16 : 0;
			int var6 = var1.getBlockMetadata(var2, var3, var4);
			if(var6 == 0) {
				var6 = 1;
				var1.setBlockMetadataWithNotify(var2, var3, var4, 1);
			}

			var5 = this.getConnectionStrength(var1, var2, var3 - 1, var4, var5);
			var5 = this.getConnectionStrength(var1, var2, var3, var4 - 1, var5);
			var5 = this.getConnectionStrength(var1, var2, var3, var4 + 1, var5);
			var5 = this.getConnectionStrength(var1, var2 - 1, var3, var4, var5);
			var5 = this.getConnectionStrength(var1, var2 + 1, var3, var4, var5);
			int var7 = var5 - 1;
			if(var7 < 10) {
				var7 = 1;
			}

			if(var7 != var6) {
				var1.setBlockMetadataWithNotify(var2, var3, var4, var7);
				this.updateConnectedLeaves(var1, var2, var3 - 1, var4, var6);
				this.updateConnectedLeaves(var1, var2, var3 + 1, var4, var6);
				this.updateConnectedLeaves(var1, var2, var3, var4 - 1, var6);
				this.updateConnectedLeaves(var1, var2, var3, var4 + 1, var6);
				this.updateConnectedLeaves(var1, var2 - 1, var3, var4, var6);
				this.updateConnectedLeaves(var1, var2 + 1, var3, var4, var6);
			}

		}
	}

	private int getConnectionStrength(World var1, int var2, int var3, int var4, int var5) {
		int var6 = var1.getBlockId(var2, var3, var4);
		if(var6 == Block.wood.blockID) {
			return 16;
		} else {
			if(var6 == this.blockID) {
				int var7 = var1.getBlockMetadata(var2, var3, var4);
				if(var7 != 0 && var7 > var5) {
					return var7;
				}
			}

			return var5;
		}
	}

	public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
		int var6 = var1.getBlockMetadata(var2, var3, var4);
		if(var6 == 0) {
			this.decayCounter = 0;
			this.updateCurrentLeaves(var1, var2, var3, var4);
		} else if(var6 == 1) {
			this.removeLeaves(var1, var2, var3, var4);
		} else if(var5.nextInt(10) == 0) {
			this.updateCurrentLeaves(var1, var2, var3, var4);
		}

	}

	private void removeLeaves(World var1, int var2, int var3, int var4) {
		this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4));
		var1.setBlockWithNotify(var2, var3, var4, 0);
	}

	public int quantityDropped(Random var1) {
		return var1.nextInt(20) == 0 ? 1 : 0;
	}

	public int idDropped(int var1, Random var2) {
		return Block.sapling.blockID;
	}

	public boolean isOpaqueCube() {
		return !this.graphicsLevel;
	}

	public void setGraphicsLevel(boolean var1) {
		this.graphicsLevel = var1;
		this.blockIndexInTexture = this.leafTexIndex + (var1 ? 0 : 1);
	}

	public void onEntityWalking(World var1, int var2, int var3, int var4, Entity var5) {
		super.onEntityWalking(var1, var2, var3, var4, var5);
	}
}
