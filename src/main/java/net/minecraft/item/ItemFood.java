package net.minecraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemFood extends Item {
	private int healAmount;

	public ItemFood(int var1, int var2) {
		super(var1);
		this.healAmount = var2;
		this.maxStackSize = 1;
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
		--var1.stackSize;
		var3.heal(this.healAmount);
		return var1;
	}
}
