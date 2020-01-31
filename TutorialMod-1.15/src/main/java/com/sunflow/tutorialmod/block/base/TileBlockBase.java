package com.sunflow.tutorialmod.block.base;

import com.sunflow.tutorialmod.util.interfaces.ICustomNameable;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class TileBlockBase extends OrientableBlockBase {

	public TileBlockBase(Properties properties) { super(properties); }

	public TileBlockBase() { this(Material.ROCK, 2.0f); }

	public TileBlockBase(Material material, SoundType sound, float hardness, float resistance) {
		this(Properties.create(material)
				.sound(sound)
				.hardnessAndResistance(hardness, resistance));
	}

	public TileBlockBase(Material material, float hardnessAndResistance) {
		this(Properties.create(material).hardnessAndResistance(hardnessAndResistance));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isRemote) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof INamedContainerProvider) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tile, pos);
			}
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (stack.hasDisplayName()) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof ICustomNameable) {
				((ICustomNameable) tile).setCustomName(stack.getDisplayName());
			}
		}
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
}
