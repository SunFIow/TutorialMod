package com.sunflow.tutorialmod.block.copper_chest;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sunflow.tutorialmod.TutorialMod;

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
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CopperChestTileRenderer<T extends TileEntity & IChestLid> extends TileEntityRenderer<T> {
	private static final ResourceLocation TEXTURE_COPPER_CHEST = new ResourceLocation(TutorialMod.MODID, "textures/entity/chest/copper_chest.png");
//	public static final Material TEXTURE_COPPER_CHEST = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("entity/chest/copper_chest.png"));
	private final CopperChestModel simpleChest;

	public CopperChestTileRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
		simpleChest = new CopperChestModel();
	}

	@Override
	public void func_225616_a_(T p_225616_1_, float p_225616_2_, MatrixStack p_225616_3_, IRenderTypeBuffer p_225616_4_, int p_225616_5_, int p_225616_6_) {
		World world = p_225616_1_.getWorld();
		boolean flag = world != null;
		BlockState blockstate = flag ? p_225616_1_.getBlockState() : Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
		Block block = blockstate.getBlock();
		if (block instanceof CopperChestBlock) {
			p_225616_3_.func_227860_a_();
			float f = blockstate.get(ChestBlock.FACING).getHorizontalAngle();
			p_225616_3_.func_227861_a_(0.5D, 0.5D, 0.5D);
			p_225616_3_.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-f));
			p_225616_3_.func_227861_a_(-0.5D, -0.5D, -0.5D);
			TileEntityMerger.ICallbackWrapper<? extends ChestTileEntity> icallbackwrapper;
			icallbackwrapper = TileEntityMerger.ICallback::func_225537_b_;

			float f1 = icallbackwrapper.apply(ChestBlock.func_226917_a_(p_225616_1_)).get(p_225616_2_);
			f1 = 1.0F - f1;
			f1 = 1.0F - f1 * f1 * f1;
			int i = icallbackwrapper.apply(new DualBrightnessCallback<>()).applyAsInt(p_225616_5_);
//			Material material = Atlases.field_228758_q_;
//			Material material = Atlases.field_228761_t_;
//			Material material = new Material(Atlases.field_228747_f_, new ResourceLocation("entity/chest/" + "copper"));
//			Material material = new Material(Atlases.field_228747_f_, new ResourceLocation("entity/chest/" + "copper_chest"));
//			IVertexBuilder ivertexbuilder = material.func_229311_a_(p_225616_4_, RenderType::func_228638_b_);

			IVertexBuilder ivertexbuilder = p_225616_4_.getBuffer(RenderType.func_228637_a_(TEXTURE_COPPER_CHEST, false));

			this.func_228871_a_(p_225616_3_, ivertexbuilder, f1, i, p_225616_6_);
			p_225616_3_.func_227865_b_();
		}
	}

	private void func_228871_a_(MatrixStack p_228871_1_, IVertexBuilder p_228871_2_, float p_228871_6_, int p_228871_7_, int p_228871_8_) {
		simpleChest.applyRotation(p_228871_6_);

		simpleChest.render(p_228871_1_, p_228871_2_, p_228871_7_, p_228871_8_);
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