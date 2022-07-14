package com.sunflow.tutorialmod.command;

import java.awt.geom.Point2D;
import java.util.Random;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.Util;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

public class SlimeChunkCommand implements ICommand {

	@Override
	public ArgumentBuilder<CommandSourceStack, ?> getBuilder() {
		return Commands.literal("slimechunk")
				.then(Commands.literal("check")
						.then(Commands.argument("pos", BlockPosArgument.blockPos())
								.executes(ctx -> check(ctx.getSource(), BlockPosArgument.getLoadedBlockPos(ctx, "pos"))))
						.executes(ctx -> check(ctx.getSource(), ctx.getSource().getPlayerOrException().blockPosition())))
				.then(Commands.literal("find")
						.then(Commands.argument("radius", IntegerArgumentType.integer(0))
								.executes(ctx -> find(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "radius"))))
						.executes(ctx -> find(ctx.getSource(), 1000)));

	}

	private static int check(CommandSourceStack source, BlockPos pos) throws CommandSyntaxException {
		ChunkPos chunk = new ChunkPos(pos);
		long seed = source.getServer().getLevel(Level.OVERWORLD).getSeed();
		boolean isSlimeChunk = checkChunk(seed, chunk.x, chunk.z);
		source.getPlayerOrException().sendMessage(new TextComponent("This is " + (!isSlimeChunk ? "not" : "") + " a Slime Chunk"), Util.NIL_UUID);
		return 1;
	}

	private static int find(CommandSourceStack source, int radius) throws CommandSyntaxException {
		ChunkPos chunk = new ChunkPos(source.getPlayerOrException().blockPosition());
		long seed = source.getServer().getLevel(Level.OVERWORLD).getSeed();
		ChunkPos closest;
		if (checkChunk(seed, chunk.x, chunk.z)) {
			source.getEntity().sendMessage(new TextComponent("You are in a Slime Chunk"), Util.NIL_UUID);
			return 1;
		} else {
			closest = new ChunkPos(chunk.x - radius, chunk.z - radius);
			double closestDistance = distance(chunk, closest);
			for (int i = -radius; i < radius; i++) {
				for (int j = -radius; j < radius; j++) {
					ChunkPos current = new ChunkPos(chunk.x + i, chunk.z + j);
					boolean isSlimeChunk = checkChunk(seed, current.x, current.z);
					double currentDistance = distance(chunk, current);
					if (isSlimeChunk && currentDistance < closestDistance) {
						closestDistance = currentDistance;
						closest = current;
					}
				}
			}
		}
		source.getPlayerOrException().sendMessage(new TextComponent("Your closest Slime Chunk is at [X:" + closest.x + ", Z:" + closest.z + "]"), Util.NIL_UUID);
		return 1;
	}

	private static double distance(ChunkPos c1, ChunkPos c2) {
		return Point2D.distance(c1.x, c1.z, c2.x, c2.z);
	}

	private static boolean checkChunk(long seed, int xPosition, int zPosition) {
		Random rnd = new Random(
				seed +
						xPosition * xPosition * 0x4c1906 +
						xPosition * 0x5ac0db +
						zPosition * zPosition * 0x4307a7L +
						zPosition * 0x5f24f ^ 0x3ad8025f);
		return rnd.nextInt(10) == 0;
	}
}
