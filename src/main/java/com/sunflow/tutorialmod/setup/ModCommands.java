package com.sunflow.tutorialmod.setup;

import java.util.ArrayList;
import java.util.List;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.command.CommandBase;
import com.sunflow.tutorialmod.command.SlimeChunkCommand;
import com.sunflow.tutorialmod.command.SpawnerCommand;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.server.command.ConfigCommand;

public class ModCommands {
	public static final List<CommandBase> COMMANDS = new ArrayList<>();

	public static final CommandBase SLIME_CHUNK_COMMAND = new SlimeChunkCommand();
	public static final CommandBase SPAWNER_COMMAND = new SpawnerCommand();

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
//		LiteralArgumentBuilder<CommandSource> builder = LiteralArgumentBuilder.<CommandSource>literal(TutorialMod.MODID);
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal(TutorialMod.MODID);
		for (CommandBase command : COMMANDS) {
			builder.then(command.getBuilder());
		}
		LiteralCommandNode<CommandSource> node = dispatcher.register(builder);
		dispatcher.register(Commands.literal("tut").redirect(node));

		ConfigCommand.register(dispatcher);
	}
}