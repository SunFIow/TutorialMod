package com.sunflow.tutorialmod.command;

import com.mojang.brigadier.builder.ArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;

public interface ICommand {
    ArgumentBuilder<CommandSourceStack, ?> getBuilder();
}
