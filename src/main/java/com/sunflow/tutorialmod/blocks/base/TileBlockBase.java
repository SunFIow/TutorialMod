package com.sunflow.tutorialmod.blocks.base;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.util.interfaces.ICustomNameable;
import com.sunflow.tutorialmod.util.interfaces.ITileEntity;

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
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class TileBlockBase extends OrientableBlockBase implements ITileEntity {

	public TileBlockBase(String name, ItemGroup group, Properties properties) {
		super(name, group, properties);
	}

	public TileBlockBase(String name, Properties properties) {
		this(name, TutorialMod.setup.itemGroup, properties);
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

	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isRemote) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof INamedContainerProvider) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tile, pos);
			}
		}
		return true;
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

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return this.getTileEntity();
	}
}
