package com.sunflow.tutorialmod.item.cbow;

import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.setup.ModItems;

import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;

public class CBowItem extends BowItem {

	public CBowItem() {
		super(new Item.Properties().maxDamage(348).group(ModGroups.itemGroup));
		setRegistryName("cbow");
		ModItems.ITEMS.add(this);
	}

	@Override
	public AbstractArrowEntity customeArrow(AbstractArrowEntity arrow) {
		arrow.setDamage(arrow.getDamage() * 2);
		return arrow;
	}
}
