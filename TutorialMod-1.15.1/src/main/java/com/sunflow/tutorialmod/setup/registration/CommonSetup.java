package com.sunflow.tutorialmod.setup.registration;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonSetup {
	@SubscribeEvent
	public static void setup(FMLCommonSetupEvent event) {

//		TODO: RenderTypeLookup.setRenderLayer(this, RenderType.func_228643_e_());
		RenderTypeLookup.setRenderLayer(Registration.COPPER_LEAVES.get(), RenderType.func_228643_e_());
		RenderTypeLookup.setRenderLayer(Registration.ALUMINIUM_LEAVES.get(), RenderType.func_228643_e_());
		RenderTypeLookup.setRenderLayer(Registration.SANTA_HAT.get(), RenderType.func_228643_e_());
		RenderTypeLookup.setRenderLayer(Registration.RICE_PLANT.get(), RenderType.func_228643_e_());
	}
}
