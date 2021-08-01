package com.sunflow.tutorialmod.command.spawner;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.SpawnPacket;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

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

		addButton(new Button(guiLeft + 10, guiTop + 10 + 27 * 0, 160, 20, new TranslationTextComponent("Skeleton"), b -> spawn("minecraft:skeleton")));
		addButton(new Button(guiLeft + 10, guiTop + 10 + 27 * 1, 160, 20, new TranslationTextComponent("Zombie"), b -> spawn("minecraft:zombie")));
		addButton(new Button(guiLeft + 10, guiTop + 10 + 27 * 2, 160, 20, new TranslationTextComponent("Cow"), b -> spawn("minecraft:cow")));
		addButton(new Button(guiLeft + 10, guiTop + 10 + 27 * 3, 160, 20, new TranslationTextComponent("Sheep"), b -> spawn("minecraft:sheep")));
		addButton(new Button(guiLeft + 10, guiTop + 10 + 27 * 4, 160, 20, new TranslationTextComponent("Chicken"), b -> spawn("minecraft:chicken")));
	}

	private void spawn(String id) {
		Networking.sendToServer(new SpawnPacket(id, minecraft.player.world.getDimensionKey(), minecraft.player.getPosition()));
		minecraft.displayGuiScreen((Screen) null);
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		minecraft.getTextureManager().bindTexture(GUI);

		blit(matrixStack, guiLeft, guiTop, 0, 0, WIDTH, HEIGHT);
		super.render(matrixStack, mouseX, mouseY, partialTicks);

	}

	public static void open() { TutorialMod.proxy.getMinecraft().displayGuiScreen(new SpawnerScreen()); }

	@Override
	public boolean isPauseScreen() { return false; }

}
