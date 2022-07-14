package com.sunflow.tutorialmod.block.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import com.mojang.math.Vector3d;
import com.mojang.math.Vector3f;
import com.sunflow.tutorialmod.block.furniture.fancyblock.FancyBlock;
import com.sunflow.tutorialmod.block.furniture.fancyblock.FancyBlockEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;

public class CustomBakedModel implements IDynamicBakedModel {
	private final ItemTransforms transforms = getAllTransforms();

	final ResourceLocation texture;
	private List<BakedQuad> quads;
	private Vector3d offset;
	private Vector3d length;

	public CustomBakedModel(ResourceLocation texture, Vector3d offset, Vector3d length) {
		this.texture = texture;
		this.offset = offset;
		this.length = length;
	}

	private TextureAtlasSprite getTexture() {
		return Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(texture);
	}

	@Nonnull
	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
		BlockState mimic = extraData.getData(FancyBlockEntity.PROPERTY_MIMIC);
		if (mimic != null && !(mimic.getBlock() instanceof FancyBlock)) {
			ModelResourceLocation location = BlockModelShaper.stateToModelLocation(mimic);
			if (location != null) {
				BakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
				if (model != null) return model.getQuads(mimic, side, rand, extraData);
			}
		}

		if (side != null) return Collections.emptyList();

		Vector3d offset = extraData.getData(FancyBlockEntity.PROPERTY_OFFSET);
		if (offset != null) {
			this.offset = offset;
			quads = null;
		}

		return getOrCreateQuads();
	}

	private List<BakedQuad> getOrCreateQuads() {
		if (quads == null) {
			TextureAtlasSprite texture = getTexture();
			quads = new ArrayList<BakedQuad>();

			double x1 = offset.x + 0.25D;
			double y1 = offset.y;
			double z1 = offset.z + 0.25D;

			double x2 = x1 + length.x;
			double y2 = y1 + length.y;
			double z2 = z1 + length.z;

			Vec3 v5 = new Vec3(x1, y1, z1);
			Vec3 v8 = new Vec3(x1, y1, z2);
			Vec3 v1 = new Vec3(x1, y2, z1);
			Vec3 v2 = new Vec3(x1, y2, z2);
			Vec3 v6 = new Vec3(x2, y1, z1);
			Vec3 v7 = new Vec3(x2, y1, z2);
			Vec3 v4 = new Vec3(x2, y2, z1);
			Vec3 v3 = new Vec3(x2, y2, z2);

			quads.add(createQuad(v1, v2, v3, v4, texture));
			quads.add(createQuad(v5, v6, v7, v8, texture));
			quads.add(createQuad(v3, v7, v6, v4, texture));
			quads.add(createQuad(v1, v5, v8, v2, texture));
			quads.add(createQuad(v4, v6, v5, v1, texture));
			quads.add(createQuad(v2, v8, v7, v3, texture));
		}
		return quads;
	}

	private BakedQuad createQuad(Vec3 v1, Vec3 v2, Vec3 v3, Vec3 v4, TextureAtlasSprite sprite) {
		Vec3 normal = v3.subtract(v2).cross(v1.subtract(v2)).normalize();

		BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
		builder.setQuadOrientation(Direction.getNearest(normal.x, normal.y, normal.z));
		putVertex(builder, normal, v1.x, v1.y, v1.z, 0, 0, sprite, 1.0f, 1.0f, 1.0f);
		putVertex(builder, normal, v2.x, v2.y, v2.z, 0, sprite.getHeight(), sprite, 1.0f, 1.0f, 1.0f);
		putVertex(builder, normal, v3.x, v3.y, v3.z, sprite.getWidth(), sprite.getHeight(), sprite, 1.0f, 1.0f, 1.0f);
		putVertex(builder, normal, v4.x, v4.y, v4.z, sprite.getWidth(), 0, sprite, 1.0f, 1.0f, 1.0f);
		return builder.build();
	}

	private void putVertex(BakedQuadBuilder builder, Vec3 normal,
			double x, double y, double z, float u, float v, TextureAtlasSprite sprite, float r, float g, float b) {

		ImmutableList<VertexFormatElement> elements = builder.getVertexFormat().getElements(); // .asList();
		for (int i = 0; i < elements.size(); i++) {
			VertexFormatElement e = elements.get(i);
			switch (e.getUsage()) {
				case POSITION:
					builder.put(i, (float) x, (float) y, (float) z, 1.0f);
					break;
				case COLOR:
					builder.put(i, r, g, b, 1.0f);
					break;
				case UV:
					switch (e.getIndex()) {
						case 0:
							float iu = sprite.getU(u);
							float iv = sprite.getV(v);
							builder.put(i, iu, iv);
							break;
						case 2:
							builder.put(i, 0, 0);
							break;
						default:
							builder.put(i);
							break;
					}
					break;
				case NORMAL:
					builder.put(i, (float) normal.x, (float) normal.y, (float) normal.z);
					break;
				default:
					builder.put(i);
					break;
			}
		}

	}

	@Override
	public boolean isGui3d() { return false; }

	@Override
	public boolean useAmbientOcclusion() { return true; }

	@Override
	public boolean usesBlockLight() { return true; }

	@Override
	public boolean isCustomRenderer() { return true; }

	@Override
	public TextureAtlasSprite getParticleIcon() { return getTexture(); }

	@Override
	public ItemOverrides getOverrides() { return ItemOverrides.EMPTY; }

	@Override
	public ItemTransforms getTransforms() { return ItemTransforms.NO_TRANSFORMS; }
	// public ItemTransforms getTransforms() { return ItemTransforms.NO_TRANSFORMS; }

	public ItemTransforms getAllTransforms() {
		ItemTransform tpLeft = getTransform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
		ItemTransform tpRight = getTransform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
		ItemTransform fpLeft = getTransform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND);
		ItemTransform fpRight = getTransform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND);
		ItemTransform head = getTransform(ItemTransforms.TransformType.HEAD);
		ItemTransform gui = getTransform(ItemTransforms.TransformType.GUI);
		ItemTransform ground = getTransform(ItemTransforms.TransformType.GROUND);
		ItemTransform fixed = getTransform(ItemTransforms.TransformType.FIXED);
		return new ItemTransforms(tpLeft, tpRight, fpLeft, fpRight, head, gui, ground, fixed);
	}

	private ItemTransform getTransform(ItemTransforms.TransformType type) {
		if (type.equals(ItemTransforms.TransformType.GUI)) {
			return new ItemTransform(new Vector3f(33F, 45F, 0F), new Vector3f(), new Vector3f(0.8F, 0.8F, 0.8F));
		} else if (type.equals(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND) || type.equals(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)) {
			return new ItemTransform(new Vector3f(0F, 45F, 0F), new Vector3f(), new Vector3f(0.53F, 0.53F, 0.53F));
		} else if (type.equals(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND) || type.equals(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)) {
			return new ItemTransform(new Vector3f(-15F, 0F, 45F), new Vector3f(0F, 0.17f, 0F), new Vector3f(0.5F, 0.5F, 0.5F));
		}
		return ItemTransform.NO_TRANSFORM;
	}

}