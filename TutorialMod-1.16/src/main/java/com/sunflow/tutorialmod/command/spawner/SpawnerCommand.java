package com.sunflow.tutorialmod.command.spawner;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.sunflow.tutorialmod.command.CommandBase;
import com.sunflow.tutorialmod.network.Networking;
import com.sunflow.tutorialmod.network.packet.OpenGuiPacket;
import com.sunflow.tutorialmod.util.Log;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkDirection;

public class SpawnerCommand extends CommandBase implements Command<CommandSource> {

//	private static final SpawnerCommand CMD = new SpawnerCommand();

	@Override
	public ArgumentBuilder<CommandSource, ?> getBuilder() {
		return Commands.literal("spawn")
				.requires(cs -> cs.hasPermissionLevel(0))
				.executes(this);
//				.executes(ctx -> run(ctx));
	}

	@Override
	public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
		Log.error("SpawnerCommand#run");
		ServerPlayerEntity player = context.getSource().asPlayer();
		Networking.sendTo(new OpenGuiPacket(), player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		return 0;
	}

}
