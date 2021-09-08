package com.sunflow.tutorialmod.blocks.poweruser;

import com.sunflow.tutorialmod.setup.Registration;
import com.sunflow.tutorialmod.tools.CustomEnergyStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class PowerUserBE extends BlockEntity {

	private final CustomEnergyStorage energyStorage = createEnergy();

	private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

	//
	private boolean hasPower;

	public PowerUserBE(BlockPos pos, BlockState state) { super(Registration.POWERUSER_BE.get(), pos, state); }

	@Override
	public void setRemoved() {
		super.setRemoved();
		// Don't forget to invalidate your caps when block entity is removed
		energy.invalidate();
	}

	// getUpdatePacket() and onDataPacket() are for synchronizing on block updates
	// getUpdatePacket() is called server side and collects data for client
	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		CompoundTag tag = new CompoundTag();
		tag.putBoolean("hasPower", hasPower);
		return new ClientboundBlockEntityDataPacket(worldPosition, 1, tag);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		CompoundTag tag = pkt.getTag();
		hasPower = tag.getBoolean("hasPower");
	}

	// getUpdateTag() and handleUpdateTag() are for synchronizing the client side BE on chunk load
	// getUpdateTag() is called server side and collects data for client
	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = super.getUpdateTag();
		tag.putBoolean("hasPower", hasPower);
		return tag;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag) {
		super.handleUpdateTag(tag);
		hasPower = tag.getBoolean("hasPower");
	}

	public void tickClient(BlockState state) {
		if (!hasPower) return;
		BlockPos pos = this.worldPosition;
		level.addParticle(ParticleTypes.CLOUD, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, 0.0, 0.0, 0.0);
	}

	public void tickServer(BlockState state) {
		if (!hasEnoughPowerToWork()) return;
		energyStorage.consumeEnergy(10);
	}

	private boolean hasEnoughPowerToWork() { return energyStorage.getEnergyStored() >= 10; }

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityEnergy.ENERGY) {
			return energy.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		CompoundTag tm = tag.getCompound("tutorialmod");
		energyStorage.deserializeNBT(tm.get("energy"));
	}

	@Override
	public CompoundTag save(CompoundTag tag) {
		super.save(tag);
		CompoundTag tm = new CompoundTag();
		tm.put("energy", energyStorage.serializeNBT());
		tag.put("tutorialmod", tm);
		return tag;
	}

	private final CustomEnergyStorage createEnergy() {
		return new CustomEnergyStorage(1000, 20) {
			@Override
			protected void onEnergyChanged() {
				boolean newHasPower = hasEnoughPowerToWork();
				if (newHasPower != hasPower) {
					hasPower = newHasPower;
					level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
				}

				// To make sure the BE persists when the chunk is saved later
				// we need to mark it dirty every thime the item handler changes.
				setChanged();
			}
		};
	}

}
