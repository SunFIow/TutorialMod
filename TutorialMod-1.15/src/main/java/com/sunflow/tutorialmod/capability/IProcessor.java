package com.sunflow.tutorialmod.capability;

public interface IProcessor {
	int getTime();

	int getTotalTime();

	void setTime(int value);

	void setTotalTime(int value);

	void addTime(int value);

	void addTotalTime(int value);
}
