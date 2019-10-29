package com.sunflow.tutorialmod.init;

import java.util.ArrayList;
import java.util.List;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.sunflow.tutorialmod.commands.SlimeChunkCommand;
import com.sunflow.tutorialmod.util.interfaces.ICommand;

import net.minecraft.command.CommandSource;
import net.minecraftforge.server.command.ConfigCommand;

public class ModCommands {
	public static final List<ICommand> COMMANDS = new ArrayList<>();

	public static final ICommand SLIME_CHUNK_COMMAND = new SlimeChunkCommand();

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> builder = LiteralArgumentBuilder.<CommandSource>literal("tutorialmod");
		for (ICommand command : COMMANDS) {
			builder.then(command.getBuilder());
		}
		dispatcher.register(builder);

		ConfigCommand.register(dispatcher);
	}
}