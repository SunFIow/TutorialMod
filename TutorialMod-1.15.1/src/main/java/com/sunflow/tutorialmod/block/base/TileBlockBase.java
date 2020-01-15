package com.sunflow.tutorialmod.block.base;

import com.sunflow.tutorialmod.setup.ModGroups;
import com.sunflow.tutorialmod.util.interfaces.ICustomNameable;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class TileBlockBase extends OrientableBlockBase {

	public TileBlockBase(String name, ItemGroup group, Properties properties) {
		super(name, group, properties);
	}

	public TileBlockBase(String name, Properties properties) {
		this(name, ModGroups.itemGroup, properties);
	}

	public TileBlockBase(String name, Material material, SoundType sound, float hardness, float resistance) {
		this(name, Properties.create(material)
				.sound(sound)
				.hardnessAndResistance(hardness, resistance));
	}

	public TileBlockBase(String name, Material material, float hardnessAndResistance) {
		this(name, Properties.create(material)
				.hardnessAndResistance(hardnessAndResistance));
	}

	public TileBlockBase(String name) {
		this(name, Material.ROCK, 2.0f);
	}

//	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
	@Override
	public ActionResultType func_225533_a_(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
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
