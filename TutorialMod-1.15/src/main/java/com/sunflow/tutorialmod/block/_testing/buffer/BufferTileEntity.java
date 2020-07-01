package com.sunflow.tutorialmod.block._testing.buffer;

import java.util.List;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;

public class BufferTileEntity extends TileEntity implements ITickableTileEntity {

	public BufferTileEntity() { super(Registration.BUFFER_BLOCK_TILE.get()); }

	@Override
	public void tick() {
		if (this.world.getGameTime() % 80L == 0L) {
			if (!this.world.isRemote) {
				AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.pos)).grow(10).expand(0.0D, this.world.getHeight(), 0.0D);
				List<PlayerEntity> list = this.world.getEntitiesWithinAABB(PlayerEntity.class, axisalignedbb);

				for (PlayerEntity playerentity : list) {
					playerentity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 10 * 20, 0, true, true));
				}
			}
			this.playSound(SoundEvents.BLOCK_BEACON_AMBIENT);
		}
	}

	public void playSound(SoundEvent soundEvent) {
		this.world.playSound((PlayerEntity) null, this.pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}
}
