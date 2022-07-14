package com.sunflow.tutorialmod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.sunflow.tutorialmod.command.util.TeleportationTools;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;

public class TpDimCommand implements ICommand, Command<CommandSourceStack> {

	@Override
	public ArgumentBuilder<CommandSourceStack, ?> getBuilder() {
		return Commands.literal("dim")
				.requires(cs -> cs.hasPermission(0))
				.executes(this);
	}

	@Override
	public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		ServerPlayer player = context.getSource().getPlayerOrException();
		BlockPos blockPos = new BlockPos(player.getX(), 140, player.getZ());

		ResourceKey<Level> playerDim = player.getLevel().dimension();
		ResourceKey<Level> tpDim;
		if (playerDim.equals(Level.END)) tpDim = Level.OVERWORLD; // Level.END = Registration.BADLANDS_TYPE
		else if (playerDim.equals(Level.OVERWORLD)) tpDim = Level.NETHER;
		else tpDim = Level.END;

		TeleportationTools.teleportToDimension(player, tpDim, blockPos);

		return 0;
	}

}
