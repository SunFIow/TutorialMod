package com.sunflow.tutorialmod.item.armor;

import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.PlayerSkinPacket;
import com.sunflow.tutorialmod.setup.ModTabs;

import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemNBTSkin extends Item {
	private final SkinType skin;

	public ItemNBTSkin(SkinType skin) { super(new Item.Properties().tab(ModTabs.ITEM_TAB)); this.skin = skin; }

	public String getTranslation() { return skin.translation; }

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		if (skin == SkinType.DEFAULT) {
			if (!level.isClientSide()) return super.use(level, player, hand);
			ResourceLocation location = DefaultPlayerSkin.getDefaultSkin(player.getUUID());
			Networking.sendToServer(new PlayerSkinPacket(player.getUUID(), location));
			return InteractionResultHolder.success(player.getItemInHand(hand));
		}
		if (level.isClientSide()) return super.use(level, player, hand);
		String path = "textures/entity/skins/" + skin.getName();
		Networking.sendToConnected(new PlayerSkinPacket(player.getUUID(), path));
		return InteractionResultHolder.success(player.getItemInHand(hand));
	}

	public enum SkinType {
		DEFAULT("default", "Default Skin"), SUNFLOW("sunflow", "SunFlow's Skin"), BEKESCSABA99("beke", "Bekescaba's Skin"), PHANTOM("phantom", "Phatnom Skin");

		private String name;
		private String translation;

		private SkinType(String name, String translation) { this.name = name; this.translation = translation; }

		public String getName() { return name; }

		public static RegistryObject<ItemNBTSkin>[] create(DeferredRegister<Item> ITEMS) {
			@SuppressWarnings("unchecked")
			RegistryObject<ItemNBTSkin>[] skins = new RegistryObject[SkinType.values().length];
			int i = 0;
			for (SkinType skin : values()) skins[i++] = ITEMS.register(skin.getName() + "_skin", () -> new ItemNBTSkin(skin));
			return skins;
		}
	}
}