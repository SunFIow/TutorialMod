package com.sunflow.tutorialmod.util.interfaces;

import net.minecraft.util.INameable;
import net.minecraft.util.text.ITextComponent;

public interface ICustomNameable extends INameable {
	public void setCustomName(ITextComponent name);
}
