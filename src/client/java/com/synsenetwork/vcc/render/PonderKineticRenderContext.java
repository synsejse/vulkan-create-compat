package com.synsenetwork.vcc.render;

import net.minecraft.client.render.VertexConsumerProvider;

public final class PonderKineticRenderContext {
	private static final ThreadLocal<VertexConsumerProvider> BUFFER = new ThreadLocal<>();

	private PonderKineticRenderContext() {
	}

	public static void set(VertexConsumerProvider buffer) {
		BUFFER.set(buffer);
	}

	public static VertexConsumerProvider get() {
		return BUFFER.get();
	}

	public static void clear() {
		BUFFER.remove();
	}
}
