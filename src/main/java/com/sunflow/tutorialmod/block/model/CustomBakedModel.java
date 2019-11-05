package com.sunflow.tutorialmod.block.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.block.tile.FancyBlockTile;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.model.ItemTransformVec3f;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;

public class CustomBakedModel implements IDynamicBakedModel {

	private final VertexFormat format;
	private final ItemCameraTransforms transforms = getAllTransforms();

	private final String name;
	private List<BakedQuad> quads;
	private Vec3d offset;
	private Vec3d length;

	public CustomBakedModel(VertexFormat format, String name, Vec3d offset, Vec3d length) {
		this.format = format;
		this.name = name;
		this.offset = offset;
		this.length = length;
		this.quads = getOrCreateQuads();
	}

	private TextureAtlasSprite getTexture() {
		String n = TutorialMod.MODID + ":block/" + name;
		return Minecraft.getInstance().getTextureMap().getAtlasSprite(n);
	}

	@Nonnull
	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
		BlockState mimic = extraData.getData(FancyBlockTile.MIMIC);
		if (mimic != null) {
			ModelResourceLocation location = BlockModelShapes.getModelLocation(mimic);
			if (location != null) {
				IBakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
				if (model != null && !(model instanceof CustomBakedModel)) {
					return model.getQuads(mimic, side, rand, extraData);
				}
			}
		}

		if (side != null) {
			return Collections.emptyList();
		}

		Vec3d offset = extraData.getData(FancyBlockTile.OFFSET);
		if (offset != null) {
			this.offset = offset;
			quads = null;
			return getOrCreateQuads();
		}

		return quads;
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

			Vec3d v5 = new Vec3d(x1, y1, z1);
			Vec3d v8 = new Vec3d(x1, y1, z2);
			Vec3d v1 = new Vec3d(x1, y2, z1);
			Vec3d v2 = new Vec3d(x1, y2, z2);
			Vec3d v6 = new Vec3d(x2, y1, z1);
			Vec3d v7 = new Vec3d(x2, y1, z2);
			Vec3d v4 = new Vec3d(x2, y2, z1);
			Vec3d v3 = new Vec3d(x2, y2, z2);

			quads.add(createQuad(v1, v2, v3, v4, texture));
			quads.add(createQuad(v5, v6, v7, v8, texture));
			quads.add(createQuad(v3, v7, v6, v4, texture));
			quads.add(createQuad(v1, v5, v8, v2, texture));
			quads.add(createQuad(v4, v6, v5, v1, texture));
			quads.add(createQuad(v2, v8, v7, v3, texture));
		}
		return quads;
	}

	private BakedQuad createQuad(Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4, TextureAtlasSprite sprite) {
		Vec3d normal = v3.subtract(v2).crossProduct(v1.subtract(v2)).normalize();

		UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(format);
		builder.setTexture(sprite);
		putVertex(builder, normal, v1.x, v1.y, v1.z, 0, 0, sprite, 1.0f, 1.0f, 1.0f);
		putVertex(builder, normal, v2.x, v2.y, v2.z, 0, h(), sprite, 1.0f, 1.0f, 1.0f);
		putVertex(builder, normal, v3.x, v3.y, v3.z, w(), h(), sprite, 1.0f, 1.0f, 1.0f);
		putVertex(builder, normal, v4.x, v4.y, v4.z, w(), 0, sprite, 1.0f, 1.0f, 1.0f);
		return builder.build();
	}

	private void putVertex(UnpackedBakedQuad.Builder builder, Vec3d normal,
			double x, double y, double z, float u, float v,
			TextureAtlasSprite sprite, float r, float g, float b) {
		for (int e = 0; e < format.getElementCount(); e++) {
			switch (format.getElement(e).getUsage()) {
				case POSITION:
					builder.put(e, (float) x, (float) y, (float) z, 1.0f);
					break;
				case COLOR:
					builder.put(e, r, g, b, 1.0f);
					break;
				case UV:
					if (format.getElement(e).getIndex() == 0) {
						u = sprite.getInterpolatedU(u);
						v = sprite.getInterpolatedV(v);
						builder.put(e, u, v, 0f, 1f);
						break;
					}
				case NORMAL:
					builder.put(e, (float) normal.x, (float) normal.y, (float) normal.z, 0f);
					break;
				default:
					builder.put(e);
					break;
			}
		}
	}

	private int w() {
		return getTexture().getWidth();
	}

	private int h() {
		return getTexture().getHeight();
	}

	@Override
	public boolean isAmbientOcclusion() {
		return true;
	}

	@Override
	public boolean isGui3d() {
		return false;
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return getTexture();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.EMPTY;
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return transforms;
	}

	public ItemCameraTransforms getAllTransforms() {
		ItemTransformVec3f tpLeft = getTransform(TransformType.THIRD_PERSON_LEFT_HAND);
		ItemTransformVec3f tpRight = getTransform(TransformType.THIRD_PERSON_RIGHT_HAND);
		ItemTransformVec3f fpLeft = getTransform(TransformType.FIRST_PERSON_LEFT_HAND);
		ItemTransformVec3f fpRight = getTransform(TransformType.FIRST_PERSON_RIGHT_HAND);
		ItemTransformVec3f head = getTransform(TransformType.HEAD);
		ItemTransformVec3f gui = getTransform(TransformType.GUI);
		ItemTransformVec3f ground = getTransform(TransformType.GROUND);
		ItemTransformVec3f fixed = getTransform(TransformType.FIXED);
		return new ItemCameraTransforms(tpLeft, tpRight, fpLeft, fpRight, head, gui, ground, fixed);
	}

	private ItemTransformVec3f getTransform(TransformType type) {
		if (type.equals(TransformType.GUI)) {
			return new ItemTransformVec3f(new Vector3f(33F, 45F, 0F), new Vector3f(), new Vector3f(0.8F, 0.8F, 0.8F));
		} else if (type.equals(TransformType.FIRST_PERSON_LEFT_HAND) || type.equals(TransformType.FIRST_PERSON_RIGHT_HAND)) {
			return new ItemTransformVec3f(new Vector3f(0F, 45F, 0F), new Vector3f(), new Vector3f(0.53F, 0.53F, 0.53F));
		} else if (type.equals(TransformType.THIRD_PERSON_LEFT_HAND) || type.equals(TransformType.THIRD_PERSON_RIGHT_HAND)) {
			return new ItemTransformVec3f(new Vector3f(-15F, 0F, 45F), new Vector3f(0F, 0.17f, 0F), new Vector3f(0.5F, 0.5F, 0.5F));
		}
		return ItemTransformVec3f.DEFAULT;
	}

}