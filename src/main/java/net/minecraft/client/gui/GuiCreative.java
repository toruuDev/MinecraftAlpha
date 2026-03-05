package net.minecraft.client.gui;

import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/*
Added by toru
NOT in original minecraft source code

 */

public class GuiCreative extends GuiContainer {

    private IInventory creativeInventory;
    private InventoryPlayer playerInventory;

    private int scrollOffset = 0;
    private int visibleRows = 6;

    private ItemStack[] allItems;

    public GuiCreative(InventoryPlayer playerInv, IInventory creativeInv) {

        this.playerInventory = playerInv;
        this.creativeInventory = creativeInv;

        this.xSize = 176;
        this.ySize = 222;

        int count = 0;
        for(int i = 0; i < Item.itemsList.length; i++) {
            if(Item.itemsList[i] != null) count++;
        }

        allItems = new ItemStack[count];

        int index = 0;
        for(int i = 0; i < Item.itemsList.length; i++) {
            if(Item.itemsList[i] != null) {
                allItems[index++] = new ItemStack(Item.itemsList[i]);
            }
        }

        for(int y = 0; y < visibleRows; y++) {
            for(int x = 0; x < 9; x++) {

                this.inventorySlots.add(new SlotInventory(this, creativeInv, x + y * 9, 8 + x * 18, 18 + y * 18));
            }
        }

        // player inventory
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                this.inventorySlots.add(new SlotInventory(this, playerInv, x + y * 9 + 9, 8 + x * 18, 139 + y * 18));
            }
        }

        // hotbar
        for(int x = 0; x < 9; x++) {

            this.inventorySlots.add(
                    new SlotInventory(
                            this,
                            playerInv,
                            x,
                            8 + x * 18,
                            197
                    )
            );
        }

        updateVisibleItems();
    }

    private void updateVisibleItems() {

        for(int i = 0; i < visibleRows * 9; i++) {

            int itemIndex = i + scrollOffset * 9;

            if(itemIndex < allItems.length) {
                ItemStack stack = allItems[itemIndex].copy();
                stack.stackSize = 64;
                creativeInventory.setInventorySlotContents(i, stack);
            } else {
                creativeInventory.setInventorySlotContents(i, null);
            }
        }
    }

    public void handleMouseInput() {

        super.handleMouseInput();

        int wheel = Mouse.getEventDWheel();

        if(wheel != 0) {

            int maxRows = (allItems.length / 9) - visibleRows;

            if(wheel < 0 && scrollOffset < maxRows) {
                scrollOffset++;
            }

            if(wheel > 0 && scrollOffset > 0) {
                scrollOffset--;
            }

            updateVisibleItems();
        }
    }

    protected void drawGuiContainerBackgroundLayer(float f) {

        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        int texture = mc.renderEngine.getTexture("/gui/container.png");

        GL11.glColor4f(1F,1F,1F,1F);
        mc.renderEngine.bindTexture(texture);

        drawTexturedModalRect(x, y, 0, 0, xSize, 126);
        drawTexturedModalRect(x, y + 126, 0, 126, xSize, 96);
    }

    protected void drawGuiContainerForegroundLayer() {
        fontRenderer.drawString("Creative", 8, 6, 4210752);
    }
}