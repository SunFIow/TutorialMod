package com.sunflow.tutorialmod.block.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.model.IModelLoader;

public class CustomModelLoader implements IModelLoader<CustomModelGeometry> {

	private final CustomModelGeometry geometry;

	public CustomModelLoader(CustomBakedModel bakedModel) { this.geometry = new CustomModelGeometry(bakedModel); }

	public CustomModelLoader(ResourceLocation texture, Vector3d offset, Vector3d length) {
		this.geometry = new CustomModelGeometry(texture, offset, length);
	}

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {}

	@Override
	public CustomModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
		return geometry;
	}
}
