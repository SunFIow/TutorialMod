package com.sunflow.tutorialmod.block;

import com.sunflow.tutorialmod.TutorialMod;
import com.sunflow.tutorialmod.config.TutorialModConfig1;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fml.config.ModConfig;

public class ConfigBlock extends Block {

    public ConfigBlock() { super(Properties.of(Material.STONE)); }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide() && pHand.equals(InteractionHand.MAIN_HAND)) {
            TutorialModConfig1.createScreen(null, ModConfig.Type.COMMON).open(TutorialMod.proxy.getMinecraft());
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

}
