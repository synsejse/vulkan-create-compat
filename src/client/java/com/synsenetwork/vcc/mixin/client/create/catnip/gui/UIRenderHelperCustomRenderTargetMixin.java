package com.synsenetwork.vcc.mixin.client.create.catnip.gui;

import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.AddressMode;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.TextureFormat;
import com.zurrtum.create.client.catnip.gui.UIRenderHelper;
import net.minecraft.client.gl.Framebuffer;

import java.util.Objects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Fixes the Create Fly render-target crash by creating backend-neutral GPU textures.
@Mixin(UIRenderHelper.CustomRenderTarget.class)
public abstract class UIRenderHelperCustomRenderTargetMixin extends Framebuffer {
	protected UIRenderHelperCustomRenderTargetMixin(String label, boolean useDepth) {
		super(label, useDepth);
	}

	@Inject(method = "initFbo(II)V", at = @At("HEAD"), cancellable = true, remap = false)
	private void vcc$createBackendNeutralBuffers(int width, int height, CallbackInfo ci) {
		RenderSystem.assertOnRenderThread();

		GpuDevice device = RenderSystem.getDevice();
		int maxTextureSize = device.getMaxTextureSize();
		if (width <= 0 || width > maxTextureSize || height <= 0 || height > maxTextureSize) {
			throw new IllegalArgumentException("Window " + width + "x" + height + " size out of bounds (max. size: " + maxTextureSize + ")");
		}

		this.textureWidth = width;
		this.textureHeight = height;

		int usage = GpuTexture.USAGE_COPY_DST
			| GpuTexture.USAGE_COPY_SRC
			| GpuTexture.USAGE_TEXTURE_BINDING
			| GpuTexture.USAGE_RENDER_ATTACHMENT;

		this.colorAttachment = Objects.requireNonNull(
			device.createTexture(() -> this.name + " / Color", usage, TextureFormat.RGBA8, width, height, 1, 1),
			"Failed to create color attachment"
		);
		this.colorAttachmentView = device.createTextureView(this.colorAttachment);
		this.colorAttachment.setAddressMode(AddressMode.CLAMP_TO_EDGE);
		this.setFilter(FilterMode.NEAREST);

		if (this.useDepthAttachment) {
			this.depthAttachment = Objects.requireNonNull(
				device.createTexture(() -> this.name + " / Depth", usage, TextureFormat.DEPTH32, width, height, 1, 1),
				"Failed to create depth attachment"
			);
			this.depthAttachmentView = device.createTextureView(this.depthAttachment);
			this.depthAttachment.setTextureFilter(FilterMode.NEAREST, false);
			this.depthAttachment.setAddressMode(AddressMode.CLAMP_TO_EDGE);
		}

		ci.cancel();
	}
}
