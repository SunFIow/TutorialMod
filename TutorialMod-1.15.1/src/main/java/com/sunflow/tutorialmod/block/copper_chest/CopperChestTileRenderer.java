package com.sunflow.tutorialmod.block.copper_chest;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.setup.registration.Registration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class CopperChestTileRenderer extends TileEntityRenderer<CopperChestTile> {
	private static final ResourceLocation TEXTURE_COPPER_CHEST = new ResourceLocation(TutorialMod.MODID, "textures/entity/chest/copper_chest.png");
//	public static final Material TEXTURE_COPPER_CHEST = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("entity/chest/copper_chest.png"));
	private final CopperChestModel simpleChest;

	public CopperChestTileRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
		simpleChest = new CopperChestModel();
	}

	public static void register() {
		ClientRegistry.bindTileEntityRenderer(Registration.COPPER_CHEST_TILE.get(), CopperChestTileRenderer::new);
	}

	@Override
	public void render(CopperChestTile tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		World world = tileEntity.getWorld();
		boolean flag = world != null;
		BlockState blockstate = flag ? tileEntity.getBlockState() : Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
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
//			Material material = Atlases.field_228758_q_;
//			Material material = Atlases.field_228761_t_;
//			Material material = new Material(Atlases.field_228747_f_, new ResourceLocation("entity/chest/" + "copper"));
//			Material material = new Material(Atlases.field_228747_f_, new ResourceLocation("entity/chest/" + "copper_chest"));
//			IVertexBuilder ivertexbuilder = material.func_229311_a_(p_225616_4_, RenderType::func_228638_b_);

//			ChestType chesttype = blockstate.has(ChestBlock.TYPE) ? blockstate.get(ChestBlock.TYPE) : ChestType.SINGLE;
//			boolean isChristmas = false;
//			Material material = Atlases.getChestMaterial(tileEntity, chesttype, isChristmas);
//			IVertexBuilder ivertexbuilder = material.getBuffer(buffer, RenderType::entityCutout);

//			IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.func_228637_a_(TEXTURE_COPPER_CHEST, false));
			IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.entityCutout(TEXTURE_COPPER_CHEST));

			this.render(matrixStack, ivertexbuilder, f1, i, combinedOverlay);
			matrixStack.pop();
		}
	}

	private void render(MatrixStack matrixStack, IVertexBuilder buffer, float rotation, int combinedLight, int combinedOverlay) {
		simpleChest.applyRotation(rotation);

		simpleChest.render(matrixStack, buffer, combinedLight, combinedOverlay);
	}
//	private CopperChestModel getChestModel(int destroyStage) {
//		ResourceLocation resourcelocation;
//		if (destroyStage >= 0) {
//			resourcelocation = DESTROY_STAGES[destroyStage];
//		} else {
//			resourcelocation = TEXTURE_COPPER_CHEST;
//		}
//
//		bindTexture(resourcelocation);
//		return simpleChest;
//	}
}