package com.sunflow.tutorialmod.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class Processor implements IProcessor, INBTSerializable<CompoundNBT> {

	protected int time;
	protected int totalTime;

	public Processor() {}

	public Processor(int time, int totalTime) {
		this.time = time;
		this.totalTime = totalTime;
	}

	@Override
	public int getTime() { return time; }

	@Override
	public int getTotalTime() { return totalTime; }

	@Override
	public void setTime(int value) { time = value; }

	@Override
	public void setTotalTime(int value) { totalTime = value; }

	@Override
	public void addTime(int value) { time += value; }

	@Override
	public void addTotalTime(int value) { totalTime += value; }

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT tag = new CompoundNBT();
		tag.putInt("cooktime", time);
		tag.putInt("totalcooktime", totalTime);
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundNBT tag) {
		time = tag.getInt("cooktime");
		totalTime = tag.getInt("totalcooktime");
	}

}
