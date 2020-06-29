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
//	protected void init() {
	protected void func_231160_c_() {
//		guiLeft = (width - WIDTH) / 2;
//		guiTop = (height - HEIGHT) / 2;
		guiLeft = (field_230708_k_ - WIDTH) / 2;
		guiTop = (field_230709_l_ - HEIGHT) / 2;

//		addButton
		func_230480_a_(new Button(guiLeft + 10, guiTop + 10 + 27 * 0, 160, 20, new TranslationTextComponent("Skeleton"), b -> spawn("minecraft:skeleton")));
		func_230480_a_(new Button(guiLeft + 10, guiTop + 10 + 27 * 1, 160, 20, new TranslationTextComponent("Zombie"), b -> spawn("minecraft:zombie")));
		func_230480_a_(new Button(guiLeft + 10, guiTop + 10 + 27 * 2, 160, 20, new TranslationTextComponent("Cow"), b -> spawn("minecraft:cow")));
		func_230480_a_(new Button(guiLeft + 10, guiTop + 10 + 27 * 3, 160, 20, new TranslationTextComponent("Sheep"), b -> spawn("minecraft:sheep")));
		func_230480_a_(new Button(guiLeft + 10, guiTop + 10 + 27 * 4, 160, 20, new TranslationTextComponent("Chicken"), b -> spawn("minecraft:chicken")));
	}

	private void spawn(String id) {
		Networking.sendToServer(new SpawnPacket(id, field_230706_i_.player.world.func_234923_W_(), field_230706_i_.player.func_233580_cy_()));
		field_230706_i_.displayGuiScreen((Screen) null);
	}

	@Override
	public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
//		GlStateManager.color4f(1F, 1F, 1F, 1F);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		field_230706_i_.getTextureManager().bindTexture(GUI);

		func_238474_b_(matrixStack, guiLeft, guiTop, 0, 0, WIDTH, HEIGHT);
		super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);

	}

	public static void open() { TutorialMod.proxy.getMinecraft().displayGuiScreen(new SpawnerScreen()); }

	@Override
//	public boolean isPauseScreen() { return false; }
	public boolean func_231177_au__() { return false; }

}
