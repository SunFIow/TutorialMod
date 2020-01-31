package com.sunflow.tutorialmod.block.model;

import java.util.function.Supplier;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class CustomModelLoader implements IModelLoader<CustomModelGeometry> {

	private final Supplier<CustomBakedModel> bakedModel;

	public CustomModelLoader(Supplier<CustomBakedModel> bakedModel) { this.bakedModel = bakedModel; }

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {}

	@Override
	public CustomModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
		return new CustomModelGeometry(bakedModel);
	}

}
