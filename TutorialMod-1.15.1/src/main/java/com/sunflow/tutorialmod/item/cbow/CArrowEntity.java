package com.sunflow.tutorialmod.item.cbow;

import com.sunflow.tutorialmod.setup.ModItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CArrowEntity extends ArrowEntity {

	public CArrowEntity(World worldIn, LivingEntity shooter) { super(worldIn, shooter); }

	@Override
	protected ItemStack getArrowStack() {
//		ItemStack itemstack = super.getArrowStack();
//		if (itemstack.getItem() == Items.ARROW) return new ItemStack(ModItems.CARROW);
//		return itemstack;
		return new ItemStack(ModItems.CARROW);
	}
}
