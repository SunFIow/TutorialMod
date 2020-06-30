package com.sunflow.tutorialmod.setup;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.command.SlimeChunkCommand;
import com.sunflow.tutorialmod.command.TpDimCommand;
import com.sunflow.tutorialmod.command.spawner.SpawnerCommand;
import com.sunflow.tutorialmod.util.interfaces.ICommand;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public enum ModCommands {

	SLIME_CHUNK_COMMAND(new SlimeChunkCommand()),
	SPAWNER_COMMAND(new SpawnerCommand()),
	TpDim_COMMAND(new TpDimCommand());

	private final ArgumentBuilder<CommandSource, ?> builder;

	ModCommands(ICommand command) { this.builder = command.getBuilder(); }

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal(TutorialMod.MODID);
		for (ModCommands command : ModCommands.values())
			builder.then(command.builder);

		LiteralCommandNode<CommandSource> cmdTut = dispatcher.register(builder);
		dispatcher.register(Commands.literal("tut").redirect(cmdTut));
	}
}