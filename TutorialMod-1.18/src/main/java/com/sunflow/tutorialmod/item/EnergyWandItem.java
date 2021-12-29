package com.sunflow.tutorialmod.item;

import java.util.List;

import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.setup.ModTabs;
import com.sunflow.tutorialmod.util.CustomEnergyStorage;
import com.sunflow.tutorialmod.util.EnergyUtils;
import com.sunflow.tutorialmod.util.EnergyUtils.EnergyUnit;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.interfaces.IEnergyItem;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class EnergyWandItem extends Item implements IEnergyItem {

	public EnergyWandItem() { super(new Item.Properties().tab(ModTabs.ITEM_TAB).stacksTo(1)); }

	@Override
	public CustomEnergyStorage createEnergy() {
		return new CustomEnergyStorage(TutorialModConfig.SERVER.ENERGY_ITEM_MAXPOWER.get());
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
		if (!pLevel.isClientSide()) {
			ItemStack stack = pPlayer.getItemInHand(pUsedHand);
			CustomEnergyStorage itemEnergy = EnergyUtils.readStorage(stack, EnergyUnit.DEFAULT);

			if (pPlayer.isCrouching()) itemEnergy.receiveEnergy(100, false);

			// tag.put(EnergyUnit.DEFAULT.name, itemEnergy.serializeNBT());
			EnergyUtils.writeStorage(stack, EnergyUnit.DEFAULT, itemEnergy);
		}

		return super.use(pLevel, pPlayer, pUsedHand);
	}

	@Override
	public boolean isBarVisible(ItemStack pStack) {
		return EnergyUtils.hasSupport(pStack, EnergyUnit.DEFAULT) && EnergyUtils.getEnergyStored(pStack, EnergyUnit.DEFAULT) != EnergyUtils.getCapacity(pStack, EnergyUnit.DEFAULT);
	}

	// public double getDurabilityForDisplay(ItemStack stack) {
	// return EnergyUtils.getEnergyDurabilityForDisplay(stack, EnergyUnit.DEFAULT);
	// }

	// @Override
	// public int getDamage(ItemStack stack) {
	// return EnergyUtils.getEnergyDurabilityForDisplay(stack, EnergyUnit.DEFAULT);
	// }

	public int getBarWidth(ItemStack pStack) {
		return EnergyUtils.getEnergyDurabilityForDisplay(pStack, EnergyUnit.DEFAULT);
	}

	@Override
	public int getBarColor(ItemStack pStack) {
		return EnergyUtils.getEnergyRGBForDisplay(pStack, EnergyUnit.DEFAULT);
	}

	@Override
	public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
		super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
		pTooltipComponents.add(new TextComponent(EnergyUtils.getEnergyStored(pStack, EnergyUnit.DEFAULT) + " / " + EnergyUtils.getCapacity(pStack, EnergyUnit.DEFAULT)));
	}

	@Override
	public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
		Log.warn("{}:\n\t{}\n\t{}\n\t{}", "hellooo", pStack, pLevel, pPlayer);
	}

}
