package com.sunflow.tutorialmod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.sunflow.tutorialmod.command.util.TeleportationTools;
import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.util.interfaces.ICommand;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

public class TpDimCommand implements ICommand, Command<CommandSource> {

	@Override
	public ArgumentBuilder<CommandSource, ?> getBuilder() {
		return Commands.literal("dim")
				.requires(cs -> cs.hasPermissionLevel(0))
				.executes(this);
//				.executes(ctx -> run(ctx));
	}

	@Override
	public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity player = context.getSource().asPlayer();
		BlockPos blockPos = new BlockPos(player.getPosX(), 140, player.getPosZ());

		DimensionType dimType;
		if (player.dimension.equals(Registration.BADLANDS_TYPE)) dimType = DimensionType.OVERWORLD;
		else if (player.dimension.equals(DimensionType.OVERWORLD)) dimType = DimensionType.THE_NETHER;
		else dimType = Registration.BADLANDS_TYPE;

		TeleportationTools.teleportToDimension(player, dimType, blockPos);

		return 0;
	}

}
