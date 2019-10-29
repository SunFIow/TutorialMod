/*
 * Minecraft Forge
 * Copyright (c) 2016-2019.
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */

package com.sunflow.tutorialmod.init;

import java.util.ArrayList;
import java.util.List;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.sunflow.tutorialmod.commands.SlimeChunkCommand;
import com.sunflow.tutorialmod.util.interfaces.ICommand;

import net.minecraft.command.CommandSource;
import net.minecraftforge.server.command.ConfigCommand;

public class ModCommands {
	public static final List<ICommand> COMMANDS = new ArrayList<>();

	public static final ICommand SLIME_CHUNK_COMMAND = new SlimeChunkCommand();

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> builder = LiteralArgumentBuilder.<CommandSource>literal("tutorialmod");
		for (ICommand command : COMMANDS) {
			builder.then(command.getBuilder());
		}
		dispatcher.register(builder);

		ConfigCommand.register(dispatcher);
	}
}