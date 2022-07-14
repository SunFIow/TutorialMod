package com.sunflow.tutorialmod.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class ScrollPacket extends BasePacket {
	private double mouseX, mouseY, scrollDir;
	private int guiLeft, guiTop;

	public ScrollPacket(double mouseX, double mouseY, double scrollDir, int guiLeft, int guiTop) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.scrollDir = scrollDir;
		this.guiLeft = guiLeft;
		this.guiTop = guiTop;
	}

	public ScrollPacket(FriendlyByteBuf buf) {
		this.mouseX = buf.readDouble();
		this.mouseY = buf.readDouble();
		this.scrollDir = buf.readDouble();
		this.guiLeft = buf.readInt();
		this.guiTop = buf.readInt();
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeDouble(mouseX);
		buf.writeDouble(mouseY);
		buf.writeDouble(scrollDir);
		buf.writeInt(guiLeft);
		buf.writeInt(guiTop);
	}

	@Override
	protected boolean action(NetworkEvent.Context ctx) {
		// if (!(ctx.getSender().containerMenu instanceof ContainerBase)) return true;
		// ContainerBase container = (ContainerBase) ctx.getSender().containerMenu;
		// container.mouseScrolled(mouseX, mouseY, scrollDir, guiLeft, guiTop);
		return true;
	}
}
