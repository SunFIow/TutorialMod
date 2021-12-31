package com.sunflow.tutorialmod.block.furniture.fancyblock;

import com.mojang.math.Vector3d;
import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;

public class FancyBlockTile extends BlockEntity {
	public static final ModelProperty<BlockState> MIMIC = new ModelProperty<BlockState>();
	public static final ModelProperty<Vector3d> OFFSET = new ModelProperty<Vector3d>();

	private BlockState mimic;
	private Vector3d offset;

	public FancyBlockTile(BlockPos pWorldPosition, BlockState pBlockState) {
		super(Registration.FANCYBLOCK.blockEntity(), pWorldPosition, pBlockState);
	}

	public void setMimic(BlockState mimic) {
		this.mimic = mimic;
		setChanged();
		level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
	}

	public void setOffset(Vector3d offset) {
		this.offset = new Vector3d(offset.x, offset.y, offset.z);
		//		this.offset = offset;
		setChanged();
		level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
	}

	public Vector3d getOffset() { return offset; }

	@Override
	public CompoundTag getUpdateTag() { return this.saveWithoutMetadata(); }

	@Override
	// public SUpdateTileEntityPacket getUpdatePacket() {
	// 	return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
	// }
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public IModelData getModelData() {
		return new ModelDataMap.Builder()
				.withInitial(MIMIC, mimic)
				.withInitial(OFFSET, offset)
				.build();
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);

		if (tag.contains("mimic")) {
			mimic = NbtUtils.readBlockState(tag.getCompound("mimic"));
		}
		if (tag.contains("offset")) {
			offset = readVec3d(tag.getCompound("offset"));
		}
	}

	@Override
	public void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		if (mimic != null) {
			tag.put("mimic", NbtUtils.writeBlockState(mimic));
		}
		if (offset != null) {
			tag.put("offset", writeVector3d(offset));
		}
	}

	public static Vector3d readVec3d(CompoundTag tag) {
		return new Vector3d(tag.getDouble("X"), tag.getDouble("Y"), tag.getDouble("Z"));
	}

	public static CompoundTag writeVector3d(Vector3d vec) {
		CompoundTag compoundnbt = new CompoundTag();
		compoundnbt.putDouble("X", vec.x);
		compoundnbt.putDouble("Y", vec.y);
		compoundnbt.putDouble("Z", vec.z);
		return compoundnbt;
	}
}
