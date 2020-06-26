package com.sunflow.tutorialmod.block.furniture.fancyblock;

import java.util.Objects;

import com.sunflow.tutorialmod.setup.Registration;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.common.util.Constants;

public class FancyBlockTile extends TileEntity {
	public static final ModelProperty<BlockState> MIMIC = new ModelProperty<BlockState>();
	public static final ModelProperty<Vec3d> OFFSET = new ModelProperty<Vec3d>();

	private BlockState mimic;
	private Vec3d offset;

	public FancyBlockTile() {
		super(Registration.FANCY_BLOCK_TILE.get());
	}

	public void setMimic(BlockState mimic) {
		this.mimic = mimic;
		markDirty();
		world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 0 +
				Constants.BlockFlags.BLOCK_UPDATE +
				Constants.BlockFlags.NOTIFY_NEIGHBORS);
	}

	public void setOffset(Vec3d offset) {
		this.offset = new Vec3d(offset.x, offset.y, offset.z);
//		this.offset = offset;
		markDirty();
		world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 0 +
				Constants.BlockFlags.BLOCK_UPDATE +
				Constants.BlockFlags.NOTIFY_NEIGHBORS);
	}

	public Vec3d getOffset() { return offset; }

	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT tag = super.getUpdateTag();
		if (mimic != null) {
			tag.put("mimic", NBTUtil.writeBlockState(mimic));
		}
		if (offset != null) {
			tag.put("offset", writeVec3d(offset));
		}
		return tag;
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		BlockState oldMimic = mimic;
		Vec3d oldOffset = offset;
		CompoundNBT tag = pkt.getNbtCompound();
		if (tag.contains("mimic")) {
			mimic = NBTUtil.readBlockState(tag.getCompound("mimic"));
		}
		if (tag.contains("offset")) {
			offset = readVec3d(tag.getCompound("offset"));
		}

		if (!Objects.equals(oldMimic, mimic) || !Objects.equals(oldOffset, offset)) {
			ModelDataManager.requestModelDataRefresh(this);
			world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 0 +
					Constants.BlockFlags.BLOCK_UPDATE +
					Constants.BlockFlags.NOTIFY_NEIGHBORS);
		}
	}

	@Override
	public IModelData getModelData() {
		return new ModelDataMap.Builder()
				.withInitial(MIMIC, mimic)
				.withInitial(OFFSET, offset)
				.build();
	}

	@Override
	public void read(CompoundNBT tag) {
		super.read(tag);

		if (tag.contains("mimic")) {
			mimic = NBTUtil.readBlockState(tag.getCompound("mimic"));
		}
		if (tag.contains("offset")) {
			offset = readVec3d(tag.getCompound("offset"));
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		if (mimic != null) {
			tag.put("mimic", NBTUtil.writeBlockState(mimic));
		}
		if (offset != null) {
			tag.put("offset", writeVec3d(offset));
		}

		return super.write(tag);
	}

	public static Vec3d readVec3d(CompoundNBT tag) {
		return new Vec3d(tag.getDouble("X"), tag.getDouble("Y"), tag.getDouble("Z"));
	}

	public static CompoundNBT writeVec3d(Vec3d vec) {
		CompoundNBT compoundnbt = new CompoundNBT();
		compoundnbt.putDouble("X", vec.getX());
		compoundnbt.putDouble("Y", vec.getY());
		compoundnbt.putDouble("Z", vec.getZ());
		return compoundnbt;
	}
}
