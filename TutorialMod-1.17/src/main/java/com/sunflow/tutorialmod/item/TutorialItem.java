package com.sunflow.tutorialmod.item;

import java.util.List;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.ForgeMod;

public class TutorialItem extends PickaxeItem {

	public TutorialItem() { super(Tiers.NETHERITE, 1, -2.8F, new Properties().tab(TutorialMod.TAB_TUTORIALMOD)); }

	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flags) {
		list.add(new TranslatableComponent("message.tutorialmod.tutorial_item.tooltip", getDistance(stack)).withStyle(ChatFormatting.LIGHT_PURPLE));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		int distance = getDistance(stack);
		int newDistance = (distance + 1) % 4;
		setDistance(stack, newDistance);
		if (level.isClientSide()) {
			player.sendMessage(new TranslatableComponent("message.tutorialmod.tutorial_item.change", distance, newDistance), Util.NIL_UUID);
		}
		return super.use(level, player, hand);

	}

	public static int getDistance(ItemStack stack) { return stack.hasTag() ? stack.getTag().getInt("distance") : 0; }

	public static void setDistance(ItemStack stack, int uses) { stack.getOrCreateTag().putInt("distance", uses); }

	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
		boolean result = super.mineBlock(stack, level, state, pos, entity);
		if (result) {
			int distance = getDistance(stack);
			if (distance > 0) {
				CompoundTag tag = stack.getOrCreateTag();
				boolean mining = tag.getBoolean("mining");
				if (!mining) {
					BlockHitResult hit = trace(level, entity);
					if (hit.getType() == HitResult.Type.BLOCK) {
						tag.putBoolean("mining", true);
						for (int i = 0; i < distance; i++) {
							BlockPos relative = pos.relative(hit.getDirection().getOpposite(), i + 1);
							if (!tryHarvest(stack, entity, relative)) {
								tag.putBoolean("mining", false);
								return result;
							}
						}
						tag.putBoolean("mining", false);
					}
				}
			}
		}
		return result;
	}

	private boolean tryHarvest(ItemStack stack, LivingEntity entity, BlockPos pos) {
		BlockState state = entity.level.getBlockState(pos);
		if (canHarvestBlock(stack, state)) {
			if (entity instanceof ServerPlayer player) {
				return player.gameMode.destroyBlock(pos);
			}
		}
		return false;
	}

	private BlockHitResult trace(Level level, LivingEntity entity) {
		double reach = entity.getAttributeValue(ForgeMod.REACH_DISTANCE.get());
		return (BlockHitResult) entity.pick(reach, 1.0f, false);
//		Vec3 eye = entity.getEyePosition(1.0f);
//		Vec3 view = entity.getViewVector(1.0f);
//		Vec3 withReach = eye.add(view.multiply(reach, reach, reach));		
//		return level.clip(new ClipContext(eye, withReach, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity));

	}
}
