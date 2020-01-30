package com.sunflow.tutorialmod.block.copper_chest;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.registration.Registration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class CopperChestTileRenderer extends TileEntityRenderer<CopperChestTile> {
	public static final ResourceLocation COPPER_CHEST_TEXTURE = new ResourceLocation(TutorialMod.MODID, "entity/chest/copper_chest");
	public static final Material COPPER_CHEST_MATERIAL = new Material(Atlases.CHEST_ATLAS, COPPER_CHEST_TEXTURE);

	private final CopperChestModel simpleChest;

	public CopperChestTileRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
		simpleChest = new CopperChestModel();
	}

	public static void register() {
		ClientRegistry.bindTileEntityRenderer(Registration.COPPER_CHEST_TILE.get(), CopperChestTileRenderer::new);
	}

	public static void stitch(TextureStitchEvent.Pre event) {
		event.addSprite(COPPER_CHEST_TEXTURE);
	}

	@Override
	public void render(CopperChestTile tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		World world = tileEntity.getWorld();
		boolean flag = world != null;
		BlockState blockstate = flag ? tileEntity.getBlockState() : Registration.COPPER_CHEST.get().getDefaultState().with(CopperChestBlock.FACING, Direction.SOUTH);
		Block block = blockstate.getBlock();
		if (block instanceof CopperChestBlock) {
			matrixStack.push();
			float f = blockstate.get(ChestBlock.FACING).getHorizontalAngle();
			matrixStack.translate(0.5D, 0.5D, 0.5D);
			matrixStack.rotate(Vector3f.YP.rotationDegrees(-f));
			matrixStack.translate(-0.5D, -0.5D, -0.5D);
			TileEntityMerger.ICallbackWrapper<? extends ChestTileEntity> icallbackwrapper;
			icallbackwrapper = TileEntityMerger.ICallback::func_225537_b_;

			float f1 = icallbackwrapper.apply(ChestBlock.func_226917_a_(tileEntity)).get(partialTicks);
			f1 = 1.0F - f1;
			f1 = 1.0F - f1 * f1 * f1;
			int i = icallbackwrapper.apply(new DualBrightnessCallback<>()).applyAsInt(combinedLight);

			IVertexBuilder ivertexbuilder = COPPER_CHEST_MATERIAL.getBuffer(buffer, RenderType::entityCutout);

			simpleChest.applyRotation(f1);
			simpleChest.render(matrixStack, ivertexbuilder, i, combinedOverlay);

			matrixStack.pop();
		}
	}
}