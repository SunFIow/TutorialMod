package com.sunflow.tutorialmod.block.model;

import java.util.function.Supplier;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class CustomModelLoader implements IModelLoader<CustomModelGeometry> {

	private Supplier<CustomModelGeometry> supplier;

	public CustomModelLoader(Supplier<CustomModelGeometry> supplier) {
		this.supplier = supplier;
	}

	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {
		// no need to clear cache since we create a new model instance
	}

	@Override
	public CustomModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
		return supplier.get();
	}
}
