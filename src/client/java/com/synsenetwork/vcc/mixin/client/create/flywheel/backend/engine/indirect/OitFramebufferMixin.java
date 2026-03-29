package com.synsenetwork.vcc.mixin.client.create.flywheel.backend.engine.indirect;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Prevents Create Fly's GL OIT compositor from requesting GL framebuffers on Vulkan.
@Mixin(targets = "com.zurrtum.create.client.flywheel.backend.engine.indirect.OitFramebuffer")
public class OitFramebufferMixin {
	@Inject(method = "composite", at = @At("HEAD"), cancellable = true, remap = false)
	private void vcc$skipGlComposite(CallbackInfo ci) {
		ci.cancel();
	}
}
