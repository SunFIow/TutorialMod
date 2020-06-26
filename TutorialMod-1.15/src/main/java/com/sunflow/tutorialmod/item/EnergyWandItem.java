package com.sunflow.tutorialmod.item;

import java.util.List;

import com.sunflow.tutorialmod.config.TutorialModConfig;
import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.util.CustomEnergyStorage;
import com.sunflow.tutorialmod.util.EnergyUtils;
import com.sunflow.tutorialmod.util.EnergyUtils.EnergyUnit;
import com.sunflow.tutorialmod.util.Log;
import com.sunflow.tutorialmod.util.interfaces.IEnergyItem;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class EnergyWandItem extends Item implements IEnergyItem {

	public EnergyWandItem() { super(new Item.Properties().group(ModGroups.itemGroup).maxStackSize(1)); }

	@Override
	public CustomEnergyStorage createEnergy() {
		return new CustomEnergyStorage(TutorialModConfig.ENERGY_ITEM_MAXPOWER.get());
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if (!world.isRemote) {
			ItemStack stack = player.getHeldItem(hand);
			CustomEnergyStorage itemEnergy = EnergyUtils.readStorage(stack, EnergyUnit.DEFAULT);

			if (player.isSneaking()) {
				itemEnergy.receiveEnergy(100, false);
			}
//			tag.put(EnergyUnit.DEFAULT.name, itemEnergy.serializeNBT());
			EnergyUtils.writeStorage(stack, EnergyUnit.DEFAULT, itemEnergy);
		}

		return super.onItemRightClick(world, player, hand);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return EnergyUtils.hasSupport(stack, EnergyUnit.DEFAULT) && EnergyUtils.getEnergyStored(stack, EnergyUnit.DEFAULT) != EnergyUtils.getCapacity(stack, EnergyUnit.DEFAULT);
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return EnergyUtils.getEnergyDurabilityForDisplay(stack, EnergyUnit.DEFAULT);
	}

//	@Override
//	public int getRGBDurabilityForDisplay(ItemStack stack) {
////	    return MathHelper.hsvToRGB(Math.max(0.0F, (float) (1.0F - getDurabilityForDisplay(stack))) / 3.0F, 1.0F, 1.0F);    
//		return EnergyUtils.getEnergyRGBForDisplay(stack, EnergyUnit.DEFAULT);
//	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new StringTextComponent(EnergyUtils.getEnergyStored(stack, EnergyUnit.DEFAULT) + " / " + EnergyUtils.getCapacity(stack, EnergyUnit.DEFAULT)));
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
		Log.warn("hellooo");
	}

}
