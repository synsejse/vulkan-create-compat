package com.synsenetwork.vcc.mixin.client.create.flywheel.backend.engine;

import com.zurrtum.create.client.flywheel.backend.engine.MaterialRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Prevents Create Fly's disabled GL Flywheel backend from touching GL-only framebuffers on Vulkan.
@Mixin(MaterialRenderState.class)
public class MaterialRenderStateMixin {
	@Inject(method = "setupFrameBuffer", at = @At("HEAD"), cancellable = true, remap = false)
	private static void vcc$skipGlFramebufferSetup(CallbackInfo ci) {
		ci.cancel();
	}
}
