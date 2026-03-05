package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/*
Added by toru
NOT in original minecraft source code

 */

public class CreativeInventory implements IInventory {

    private ItemStack[] items;

    public CreativeInventory(int size) {
        items = new ItemStack[size];
    }

    @Override
    public int getSizeInventory() {
        return items.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return items[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int amount) {

        ItemStack stack = items[i];

        if(stack != null) {
            items[i] = null;
        }

        return stack;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack stack) {
        items[i] = stack;
    }

    @Override
    public String getInvName() {
        return "Creative";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void onInventoryChanged() {
    }
}