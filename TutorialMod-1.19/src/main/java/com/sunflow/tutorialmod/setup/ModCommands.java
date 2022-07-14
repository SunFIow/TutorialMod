package com.sunflow.tutorialmod.setup;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.command.ICommand;
import com.sunflow.tutorialmod.command.SlimeChunkCommand;
import com.sunflow.tutorialmod.command.TpDimCommand;
import com.sunflow.tutorialmod.command.spawner.SpawnerCommand;

import net.minecraft.commands.CommandSourceStack;

public enum ModCommands {
	SLIME_CHUNK_COMMAND(new SlimeChunkCommand()),
	SPAWNER_COMMAND(new SpawnerCommand()),
	TpDim_COMMAND(new TpDimCommand());

	private final ArgumentBuilder<CommandSourceStack, ?> builder;

	ModCommands(ICommand command) { this.builder = command.getBuilder(); }

	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		LiteralArgumentBuilder<CommandSourceStack> builder = LiteralArgumentBuilder.<CommandSourceStack>literal(TutorialMod.MODID);
		for (ModCommands command : ModCommands.values())
			builder.then(command.builder);
		LiteralCommandNode<CommandSourceStack> cmdTut = dispatcher.register(builder);

		LiteralArgumentBuilder<CommandSourceStack> lab = LiteralArgumentBuilder.<CommandSourceStack>literal("tut").redirect(cmdTut);
		dispatcher.register(lab);
		// ConfigCommand.register(dispatcher);
	}
}