package com.sunflow.tutorialmod.effect;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class PeeEffect extends Effect {
	private static final String uuid = "1b602580-8f52-4393-8207-8901c4b3aa78";

	public PeeEffect() {
		super(EffectType.HARMFUL, 0xffff00);
		addAttributesModifier(SharedMonsterAttributes.ARMOR, uuid, 0.2D, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	@Override
	public List<ItemStack> getCurativeItems() {
//		return super.getCurativeItems();
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
//	    ret.add(new ItemStack(Items.MILK_BUCKET));
		ret.add(new ItemStack(Items.POTION));
		return ret;
//		return new ArrayList<ItemStack>();
	}
}
