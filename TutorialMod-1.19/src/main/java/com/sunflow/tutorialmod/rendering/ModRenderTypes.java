package com.sunflow.tutorialmod.rendering;

import java.util.OptionalDouble;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.minecraft.client.renderer.RenderType;

public class ModRenderTypes extends RenderType {
	@SuppressWarnings("java:S4449")
	// DUMMY
	private ModRenderTypes() { super(null, null, null, 0, false, false, null, null); }

	private static final LineStateShard THICK_LINES = new LineStateShard(OptionalDouble.of(3.0D));

	private static final RenderType OVERLAY_LINES = create("overlay_lines",
			DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.LINES, 256,
			false, false,
			RenderType.CompositeState.builder()
					.setLineState(THICK_LINES)
					.setOverlayState(OVERLAY)
					.setTransparencyState(TRANSLUCENT_TRANSPARENCY)
					.setTextureState(NO_TEXTURE)
					.setDepthTestState(NO_DEPTH_TEST)
					.setCullState(NO_CULL)
					.setLightmapState(NO_LIGHTMAP)
					.setWriteMaskState(COLOR_WRITE)
					.createCompositeState(false));

	public static RenderType getOverlayLines() { return OVERLAY_LINES; }
}
