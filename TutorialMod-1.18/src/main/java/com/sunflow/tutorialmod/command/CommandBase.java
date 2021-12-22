package com.sunflow.tutorialmod.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.sunflow.tutorialmod.setup.ModCommands;

import net.minecraft.commands.CommandSourceStack;

public abstract class CommandBase {
	public CommandBase() { ModCommands.COMMANDS.add(this); }

	public abstract ArgumentBuilder<CommandSourceStack, ?> getBuilder();
}
