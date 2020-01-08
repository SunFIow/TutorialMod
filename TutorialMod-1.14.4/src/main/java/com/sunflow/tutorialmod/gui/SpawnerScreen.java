package com.sunflow.tutorialmod.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.SpawnPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class SpawnerScreen extends Screen {

	private static final int WIDTH = 179;
	private static final int HEIGHT = 151;

	private static final ResourceLocation GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/spawner_gui.png");

	private int guiLeft;
	private int guiTop;

	public SpawnerScreen() {
		super(new StringTextComponent("Spawn something"));
	}

	@Override
	protected void init() {
		guiLeft = (width - WIDTH) / 2;
		guiTop = (height - HEIGHT) / 2;

		addButton(new Button(guiLeft + 10, guiTop + 10 + 27 * 0, 160, 20, "Skeleton", b -> spawn("minecraft:skeleton")));
		addButton(new Button(guiLeft + 10, guiTop + 10 + 27 * 1, 160, 20, "Zombie", b -> spawn("minecraft:zombie")));
		addButton(new Button(guiLeft + 10, guiTop + 10 + 27 * 2, 160, 20, "Cow", b -> spawn("minecraft:cow")));
		addButton(new Button(guiLeft + 10, guiTop + 10 + 27 * 3, 160, 20, "Sheep", b -> spawn("minecraft:sheep")));
		addButton(new Button(guiLeft + 10, guiTop + 10 + 27 * 4, 160, 20, "Chicken", b -> spawn("minecraft:chicken")));
	}

	private void spawn(String id) {
		Networking.sendToServer(new SpawnPacket(id, minecraft.player.dimension, minecraft.player.getPosition()));
		minecraft.displayGuiScreen(null);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.color4f(1F, 1F, 1F, 1F);
		minecraft.getTextureManager().bindTexture(GUI);

		blit(guiLeft, guiTop, 0, 0, WIDTH, HEIGHT);
		super.render(mouseX, mouseY, partialTicks);

	}

	public static void open() {
		Minecraft.getInstance().displayGuiScreen(new SpawnerScreen());
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
