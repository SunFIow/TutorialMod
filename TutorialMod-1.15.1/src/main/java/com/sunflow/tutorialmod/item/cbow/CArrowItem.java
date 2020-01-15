package com.sunflow.tutorialmod.item.cbow;

import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.setup.ModItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CArrowItem extends ArrowItem {

	public CArrowItem() {
		super(new Item.Properties().group(ModGroups.itemGroup));
		setRegistryName("carrow");
		ModItems.ITEMS.add(this);
	}

	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		CArrowEntity arrowentity = new CArrowEntity(worldIn, shooter);
		arrowentity.setPotionEffect(stack);
		return arrowentity;
	}
}
