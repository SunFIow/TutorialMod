package com.sunflow.tutorialmod.rendering;

import java.util.OptionalDouble;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;

public class ModRenderTypes extends RenderType {

	// Dummy
	private ModRenderTypes(String name, VertexFormat format, int i0, int i1, boolean b0, boolean b1, Runnable runnablePre, Runnable runnablePost) {
		super(name, format, i0, i1, b0, b1, runnablePre, runnablePost);
	}

	private static final LineState THICK_LINES = new LineState(OptionalDouble.of(3.0D));

	private static final RenderType OVERLAY_LINES = makeType("overlay_lines",
			DefaultVertexFormats.POSITION_COLOR, GL11.GL_LINES, 256,
			RenderType.State.getBuilder().line(THICK_LINES)
					.layer(field_239235_M_)
					.transparency(TRANSLUCENT_TRANSPARENCY)
					.texture(NO_TEXTURE)
					.depthTest(DEPTH_ALWAYS)
					.cull(CULL_DISABLED)
					.lightmap(LIGHTMAP_DISABLED)
					.writeMask(COLOR_WRITE)
					.build(false));

	public static RenderType getOverlayLines() {
		return OVERLAY_LINES;
	}

}
