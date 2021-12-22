package com.sunflow.tutorialmod.setup;

import java.util.ArrayList;
import java.util.List;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.command.CommandBase;
import com.sunflow.tutorialmod.command.SlimeChunkCommand;
import com.sunflow.tutorialmod.command.spawner.SpawnerCommand;

import net.minecraft.commands.CommandSourceStack;

public class ModCommands {
	public static final List<CommandBase> COMMANDS = new ArrayList<>();

	public static final CommandBase SLIME_CHUNK_COMMAND = new SlimeChunkCommand();
	public static final CommandBase SPAWNER_COMMAND = new SpawnerCommand();
	// public static final CommandBase TpDim_COMMAND = new TpDimCommand();

	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		LiteralArgumentBuilder<CommandSourceStack> builder = LiteralArgumentBuilder.<CommandSourceStack>literal(TutorialMod.MODID);
		COMMANDS.forEach(command -> builder.then(command.getBuilder()));
		LiteralCommandNode<CommandSourceStack> cmdTut = dispatcher.register(builder);

		LiteralArgumentBuilder<CommandSourceStack> lab = LiteralArgumentBuilder.<CommandSourceStack>literal("tut").redirect(cmdTut);
		dispatcher.register(lab);
		// ConfigCommand.register(dispatcher);
	}
}