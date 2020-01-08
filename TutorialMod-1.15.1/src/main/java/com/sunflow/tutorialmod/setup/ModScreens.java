package com.sunflow.tutorialmod.setup;

import java.util.ArrayList;
import java.util.List;

import com.sunflow.tutorialmod.block.copper_chest.CopperChestScreen;
import com.sunflow.tutorialmod.block.machine.charger.ChargerScreen;
import com.sunflow.tutorialmod.block.machine.electric_sintering_furnace.ElectricSinteringFurnaceScreen;
import com.sunflow.tutorialmod.block.machine.energy_storage.EnergyStorageScreen;
import com.sunflow.tutorialmod.block.machine.firstblock.FirstBlockScreen;
import com.sunflow.tutorialmod.block.machine.sintering_furnace.SinteringFurnaceScreen;

import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.ScreenManager.IScreenFactory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModScreens {
	@SuppressWarnings("rawtypes")
	public static final List<ScreenEntry> SCREENS = new ArrayList<>();
//	public static final Map<?, ?> SCREENS1 = new HashMap<>();

	static {
		createScreen(ModContainerTypes.FIRSTBLOCK_CONTAINER, FirstBlockScreen::new);
		createScreen(ModContainerTypes.ENERGY_STORAGE_CONTAINER, EnergyStorageScreen::new);
		createScreen(ModContainerTypes.SINTERING_FURNACE_CONTAINER, SinteringFurnaceScreen::new);
		createScreen(ModContainerTypes.ELECTRIC_SINTERING_FURNACE_CONTAINER, ElectricSinteringFurnaceScreen::new);
		createScreen(ModContainerTypes.COPPER_CHEST_CONTAINER, CopperChestScreen::new);
		createScreen(ModContainerTypes.CHARGER_CONTAINER, ChargerScreen::new);
	}

	public static <M extends Container, U extends Screen & IHasContainer<M>> void createScreen(ContainerType<? extends M> type, ScreenManager.IScreenFactory<M, U> screenFactory) {
		SCREENS.add(new ScreenEntry<M, U>(type, screenFactory));
	}

	public static class ScreenEntry<M extends Container, U extends Screen & IHasContainer<M>> {
		public ContainerType<? extends M> type;
		public IScreenFactory<M, U> factory;

		public ScreenEntry(ContainerType<? extends M> type, IScreenFactory<M, U> screenFactory) {
			this.type = type;
			this.factory = screenFactory;
		}
	}
}
