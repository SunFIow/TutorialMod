package com.sunflow.tutorialmod.command.spawner;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.SpawnPacket;

import mcjty.theoneprobe.rendering.RenderHelper;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class SpawnerScreen extends Screen {

	private static final int WIDTH = 179;
	private static final int HEIGHT = 151;

	private static final ResourceLocation GUI = new ResourceLocation(TutorialMod.MODID, "textures/gui/spawner_gui.png");

	private int guiLeft;
	private int guiTop;

	public SpawnerScreen() { super(new TextComponent("Spawn something")); }

	@Override
	protected void init() {
		guiLeft = (width - WIDTH) / 2;
		guiTop = (height - HEIGHT) / 2;

		addRenderableWidget(new Button(guiLeft + 10, guiTop + 10 + 27 * 0, 160, 20, new TranslatableComponent("Skeleton"), b -> spawn("minecraft:skeleton")));
		addRenderableWidget(new Button(guiLeft + 10, guiTop + 10 + 27 * 1, 160, 20, new TranslatableComponent("Zombie"), b -> spawn("minecraft:zombie")));
		addRenderableWidget(new Button(guiLeft + 10, guiTop + 10 + 27 * 2, 160, 20, new TranslatableComponent("Cow"), b -> spawn("minecraft:cow")));
		addRenderableWidget(new Button(guiLeft + 10, guiTop + 10 + 27 * 3, 160, 20, new TranslatableComponent("Sheep"), b -> spawn("minecraft:sheep")));
		addRenderableWidget(new Button(guiLeft + 10, guiTop + 10 + 27 * 4, 160, 20, new TranslatableComponent("Chicken"), b -> spawn("minecraft:chicken")));
	}

	private void spawn(String id) {
		Networking.sendToServer(new SpawnPacket(id, minecraft.player.level.dimension(), minecraft.player.blockPosition()));
		minecraft.setScreen((Screen) null);
	}

	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		// RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
		// minecraft.getTextureManager().bindForSetup(GUI);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, GUI);

		// blit(stack, guiLeft, guiTop, 0, 0, WIDTH, HEIGHT);
		RenderHelper.drawTexturedModalRect(stack.last().pose(), guiLeft, guiTop, 0, 0, WIDTH, HEIGHT);
		super.render(stack, mouseX, mouseY, partialTicks);

	}

	public static void open() { TutorialMod.proxy.getMinecraft().setScreen(new SpawnerScreen()); }

	@Override
	public boolean isPauseScreen() { return false; }

}
