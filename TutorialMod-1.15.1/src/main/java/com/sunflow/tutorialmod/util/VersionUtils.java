package com.sunflow.tutorialmod.util;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;

public class VersionUtils {
	private VersionUtils() {}

	public static final double getX(Entity e) { return e.func_226277_ct_(); }

	public static final double getY(Entity e) { return e.func_226278_cu_(); }

	public static final double getZ(Entity e) { return e.func_226281_cx_(); }

	public static boolean isSneaking(Entity e) { return e.func_226271_bk_(); }

	public static BlockPos down(BlockPos pos) { return pos.func_177977_b(); }

	public static BlockPos.Mutable setPos(BlockPos.Mutable pos, int x, int y, int z) { return pos.func_181079_c(x, y, z); }

	public static ServerWorld getWorld(MinecraftServer s, DimensionType dim) { return s.func_71218_a(dim); }

	public static void addChild(ModelRenderer parent, ModelRenderer child) { parent.func_78792_a(child); }

	public static <C extends IFeatureConfig> void addStructure(Biome biome, ConfiguredFeature<C, ? extends Structure<C>> feature) {
		biome.func_226711_a_(feature);
	}

	public static <C extends IFeatureConfig> ConfiguredFeature<C, ? extends Structure<C>> getFeature(Structure<C> structure, C config) {
		return structure.func_225566_b_(config);
	}

	public static void addBox(ModelRenderer modelRenderer, float f, float g, float h, int i, int j, int k, float l) {
		modelRenderer.func_228301_a_(f, g, h, i, j, k, l);
	}
}
