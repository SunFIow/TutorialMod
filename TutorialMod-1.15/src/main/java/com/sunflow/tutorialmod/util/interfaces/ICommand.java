package com.sunflow.tutorialmod.util.interfaces;

import com.mojang.brigadier.builder.ArgumentBuilder;

import net.minecraft.command.CommandSource;

public interface ICommand {
	ArgumentBuilder<CommandSource, ?> getBuilder();
}
