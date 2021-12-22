package com.sunflow.tutorialmod.command.spawner;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.command.CommandBase;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.OpenGuiPacket;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;

public class SpawnerCommand extends CommandBase implements Command<CommandSourceStack> {

	@Override
	public ArgumentBuilder<CommandSourceStack, ?> getBuilder() {
		return Commands.literal("spawn")
				.requires(cs -> cs.hasPermission(0))
				.executes(this);
	}

	@Override
	public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		TutorialMod.LOGGER.error("SpawnerCommand#run");
		ServerPlayer player = context.getSource().getPlayerOrException();
		Networking.sendTo(new OpenGuiPacket(), player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
		return 0;
	}

}
