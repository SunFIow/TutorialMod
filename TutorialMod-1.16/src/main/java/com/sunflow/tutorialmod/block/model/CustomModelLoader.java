package com.sunflow.tutorialmod.block.model;

import com.google.common.base.Supplier;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class CustomModelLoader implements IModelLoader<CustomModelGeometry> {

	private Supplier<CustomModelGeometry> supplier;

	public CustomModelLoader(Supplier<CustomModelGeometry> supplier) {
		this.supplier = supplier;
	}

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {}

	@Override
	public CustomModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
		return supplier.get();
	}
}
