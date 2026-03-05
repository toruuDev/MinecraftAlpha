package net.minecraft.entity.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChest;
import net.minecraft.client.gui.GuiCrafting;
import net.minecraft.client.gui.GuiEditSign;
import net.minecraft.client.gui.GuiFurnace;
import net.minecraft.client.renderer.tile.TileEntityFurnace;
import net.minecraft.client.renderer.tile.TileEntitySign;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.client.particle.EntityPickupFX;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovementInput;
import net.minecraft.util.Session;
import net.minecraft.world.World;

public class EntityPlayerSP extends EntityPlayer {
	public MovementInput movementInput;
	private Minecraft mc;

	public boolean isFlying = false;
	private int flyTimer = 0;

	public EntityPlayerSP(Minecraft var1, World var2, Session var3) {
		super(var2);
		this.mc = var1;

		// toru: remove and replace old www.minecraft.net/skin/ because
		// it has been deprecated for 10 years

		if(var3 != null && var3.username != null && var3.username.length() > 0) {
			this.skinUrl = "https://minotar.net/skin/" + var3.username + ".png";
			System.out.println("Loading texture " + this.skinUrl);
		}

		this.username = var3.username;
	}

	public void updateEntityActionState() {
		super.updateEntityActionState();
		this.moveStrafing = this.movementInput.moveStrafe;
		this.moveForward = this.movementInput.moveForward;
		this.isJumping = this.movementInput.jump;
	}

	public void onLivingUpdate() {
		this.movementInput.updatePlayerMoveState(this);
		if(this.movementInput.sneak && this.ySize < 0.2F) {
			this.ySize = 0.2F;
		}

		if(mc.controllerSupport != null && mc.thePlayer != null) {
			mc.controllerSupport.tick();
		}

		if(!this.mc.isMultiplayerWorld() && this.mc.thePlayer.onGround) {

			if (this.creativeMode) {
				if (this.movementInput.jump) {
					if (flyTimer == 0) {
						this.flyTimer = 7;
					} else {
						isFlying = !this.isFlying;
						flyTimer = 0;
					}
				}
			}

			if (flyTimer > 0) {
				flyTimer--;
			}

			if (this.creativeMode && this.isFlying) {
				this.motionY = 0.0d;
				if (this.movementInput.jump) {
					this.motionY += 0.5d;
				}
				if (this.movementInput.sneak) this.motionY -= 0.5d;
				this.fallDistance = 0.0f;
			}
		}


		super.onLivingUpdate();
	}

	public void resetPlayerKeyState() {
		this.movementInput.resetKeyState();
	}

	public void handleKeyPress(int var1, boolean var2) {
		this.movementInput.checkKeyForMovementInput(var1, var2);
	}

	public void writeEntityToNBT(NBTTagCompound var1) {
		super.writeEntityToNBT(var1);
		var1.setInteger("Score", this.score);
	}

	public void readEntityFromNBT(NBTTagCompound var1) {
		super.readEntityFromNBT(var1);
		this.score = var1.getInteger("Score");
	}

	public void displayGUIChest(IInventory var1) {
		this.mc.displayGuiScreen(new GuiChest(this.inventory, var1));
	}

	public void displayGUIEditSign(TileEntitySign var1) {
		this.mc.displayGuiScreen(new GuiEditSign(var1));
	}

	public void displayWorkbenchGUI() {
		this.mc.displayGuiScreen(new GuiCrafting(this.inventory));
	}

	public void displayGUIFurnace(TileEntityFurnace var1) {
		this.mc.displayGuiScreen(new GuiFurnace(this.inventory, var1));
	}

	public void attackEntity(Entity var1) {
		int var2 = this.inventory.getDamageVsEntity(var1);
		if(var2 > 0) {
			var1.attackEntityFrom(this, var2);
			ItemStack var3 = this.getCurrentEquippedItem();
			if(var3 != null && var1 instanceof EntityLiving) {
				var3.hitEntity((EntityLiving)var1);
				if(var3.stackSize <= 0) {
					var3.onItemDestroyedByUse(this);
					this.destroyCurrentEquippedItem();
				}
			}
		}

	}

	public void onItemPickup(Entity var1, int var2) {
		this.mc.effectRenderer.addEffect(new EntityPickupFX(this.mc.theWorld, var1, this, -0.5F));
	}

	public int getPlayerArmorValue() {
		return this.inventory.getTotalArmorValue();
	}

	public void interactWithEntity(Entity var1) {
		if(!var1.interact(this)) {
			ItemStack var2 = this.getCurrentEquippedItem();
			if(var2 != null && var1 instanceof EntityLiving) {
				var2.useItemOnEntity((EntityLiving)var1);
				if(var2.stackSize <= 0) {
					var2.onItemDestroyedByUse(this);
					this.destroyCurrentEquippedItem();
				}
			}

		}
	}

	public void sendChatMessage(String message) {

		if(message.startsWith(".")) {
			if(message.equalsIgnoreCase(".gamemode creative")) {
				if(this.mc.isMultiplayerWorld()) {
					this.mc.ingameGUI.addChatMessage("You cannot change your gamemode inside a mulitplayer instance!.");
					return;
				}

				this.creativeMode = true;
				this.mc.ingameGUI.addChatMessage("Set gamemode to creative.");
				return;
			}

			if (message.equalsIgnoreCase(".gamemode survival")) {
				if(this.mc.isMultiplayerWorld()) {
					this.mc.ingameGUI.addChatMessage("You cannot change your gamemode inside a mulitplayer instance!.");
					return;
				}

				this.creativeMode = false;
				this.isFlying = false;

				this.mc.ingameGUI.addChatMessage("Set gamemode to survival.");
				return;
			}

			this.mc.ingameGUI.addChatMessage("Unknown command.");
			return;
		}
	}

	public void onPlayerUpdate() {
	}

	public boolean isSneaking() {
		return this.movementInput.sneak;
	}
}
