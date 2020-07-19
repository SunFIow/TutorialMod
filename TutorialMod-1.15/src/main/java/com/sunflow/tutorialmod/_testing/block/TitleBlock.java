package com.sunflow.tutorialmod._testing.block;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class TitleBlock extends Block {

	public TitleBlock() { super(Properties.create(Material.ROCK)); }

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		@SuppressWarnings("resource")
		IngameGui ingameGUI = TutorialMod.proxy.getMinecraft().ingameGUI;
		String title = new StringTextComponent("Iverness").applyTextStyle(TextFormatting.GREEN).getFormattedText();
		String subtitle = new StringTextComponent("Slums District").applyTextStyle(TextFormatting.DARK_GREEN).getFormattedText();
		ingameGUI.displayTitle(title, null, -1, -1, -1); // Setting the Title
		ingameGUI.displayTitle(null, subtitle, -1, -1, -1); // Setting the Subtitle
		ingameGUI.displayTitle(null, null, 50, 70, 20); // Setting the Timings
//		ingameGUI.setDefaultTitlesTimes(); // Default Timings are 10 | 70 | 20
	}
}
