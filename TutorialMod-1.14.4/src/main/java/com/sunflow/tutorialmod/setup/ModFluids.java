package com.sunflow.tutorialmod.setup;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class ModFluids {
	public static final List<Fluid> FLUIDS = new ArrayList<>();

	public static final ResourceLocation FLUID_MOLTEN_COPPER_STILL = new ResourceLocation(TutorialMod.MODID, "block/molten_copper_still");
	public static final ResourceLocation FLUID_MOLTEN_COPPER_FLOWING = new ResourceLocation(TutorialMod.MODID, "block/molten_copper_flow");

	public static final ForgeFlowingFluid.Properties prop = new ForgeFlowingFluid.Properties(() -> ModFluids.MOLTEN_COPPER, () -> ModFluids.FLOWING_MOLTEN_COPPER,
			FluidAttributes.builder(ModFluids.FLUID_MOLTEN_COPPER_STILL, ModFluids.FLUID_MOLTEN_COPPER_FLOWING))
					.bucket(() -> ModFluids.MOLTEN_COPPER_BUCKET).block(() -> ModFluids.MOLTEN_COPPER_BLOCK);

	public static final FlowingFluid MOLTEN_COPPER = (FlowingFluid) addAndGet(new ForgeFlowingFluid.Source(prop).setRegistryName("molten_copper"), FLUIDS);
	public static final FlowingFluid FLOWING_MOLTEN_COPPER = (FlowingFluid) addAndGet(new ForgeFlowingFluid.Flowing(prop).setRegistryName("molten_copper_flowing"), FLUIDS);

	public static final FlowingFluidBlock MOLTEN_COPPER_BLOCK = (FlowingFluidBlock) addAndGet(new FlowingFluidBlock(() -> MOLTEN_COPPER, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()).setRegistryName("molten_copper_block"), ModBlocks.BLOCKS);
	public static final Item MOLTEN_COPPER_BUCKET = addAndGet(new BucketItem(() -> MOLTEN_COPPER, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(TutorialMod.groups.itemGroup)).setRegistryName("molten_copper_bucket"), ModItems.ITEMS);

	private static <T> T addAndGet(T obj, List<T> list) {
		list.add(obj);
		return obj;
	}

	public static void init() {}
}
