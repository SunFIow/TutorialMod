package com.sunflow.tutorialmod.items.armor;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.init.ModItems;
import com.sunflow.tutorialmod.items.armor.SkinUtil.SkinType;

import net.minecraft.item.Item;

public class ItemNBTSkin {
	public ItemNBTSkin() {
//		super(new Item.Properties().group(TutorialMod.setup.itemGroup));
//		this.setRegistryName("skin");
//		setUnlocalizedName("itemskin");
//		setHasSubtypes(true);
		for (SkinType skin : SkinType.values()) {
			ModItems.ITEMS.add(new Item(new Item.Properties().group(TutorialMod.groups.itemGroup)).setRegistryName("skin_" + skin.getName()));
		}
//		ModItems.ITEMS.add(this);
	}

	public static Item[] create() {
		List<Item> skins = new ArrayList<>();
		for (SkinType skin : SkinType.values()) {
			Item item = new Item(new Item.Properties().group(TutorialMod.groups.itemGroup)).setRegistryName(skin.getName() + "_skin");
			skins.add(item);
		}
		ModItems.ITEMS.addAll(skins);
		return skins.toArray(new Item[0]);

	}

//	@Override
//	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
//		if (this.isInGroup(group)) {
//			for (SkinType skin : SkinType.values()) {
//				items.add(SkinUtil.createSkin(new ItemStack(this), skin));
//			}
//		}
//	}
//
//	@Override
//	public String getTranslationKey(ItemStack stack) {
//		return super.getTranslationKey(stack) + "_" + SkinUtil.getRegistryNameFromNBT(stack);
//	}
//
//	@Override
//	public ItemNBTSkin getItem() {
//		return (ItemNBTSkin) super.getItem();
//	}
//
//	@Override
//	public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
//		return super.itemInteractionForEntity(stack, playerIn, target, hand);
//	}
}
