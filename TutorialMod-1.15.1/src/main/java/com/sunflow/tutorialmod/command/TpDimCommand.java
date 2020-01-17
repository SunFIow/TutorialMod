package com.sunflow.tutorialmod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.sunflow.tutorialmod.command.util.TeleportationTools;
import com.sunflow.tutorialmod.setup.ModDimensions;
import com.sunflow.tutorialmod.util.VersionUtils;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

public class TpDimCommand extends CommandBase implements Command<CommandSource> {

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
		if (player.dimension.equals(ModDimensions.BADLANDS_TYPE))
			TeleportationTools.teleportToDimension(player, DimensionType.OVERWORLD, new BlockPos(VersionUtils.getX(player), 200, VersionUtils.getZ(player)));
		else if (player.dimension.equals(DimensionType.OVERWORLD))
			TeleportationTools.teleportToDimension(player, DimensionType.THE_NETHER, new BlockPos(VersionUtils.getX(player), 200, VersionUtils.getZ(player)));
		else
			TeleportationTools.teleportToDimension(player, ModDimensions.BADLANDS_TYPE, new BlockPos(VersionUtils.getX(player), 200, VersionUtils.getZ(player)));

		return 0;
	}

}
