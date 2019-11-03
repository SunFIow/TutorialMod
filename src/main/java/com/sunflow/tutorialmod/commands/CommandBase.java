package com.sunflow.tutorialmod.commands;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.sunflow.tutorialmod.init.ModCommands;

import net.minecraft.command.CommandSource;

public abstract class CommandBase {
	public CommandBase() {
		ModCommands.COMMANDS.add(this);
	}

	public abstract ArgumentBuilder<CommandSource, ?> getBuilder();
}
