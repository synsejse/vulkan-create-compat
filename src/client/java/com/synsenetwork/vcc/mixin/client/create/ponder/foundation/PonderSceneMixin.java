package com.synsenetwork.vcc.mixin.client.create.ponder.foundation;

import com.zurrtum.create.client.catnip.render.SuperRenderTypeBuffer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.command.OrderedRenderCommandQueueImpl;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Flushes queued entity renders before Ponder starts its translucent world-section passes.
@Mixin(targets = "com.zurrtum.create.client.ponder.foundation.PonderScene")
public class PonderSceneMixin {
	@Inject(
		method = "renderScene(Lnet/minecraft/client/MinecraftClient;Lcom/zurrtum/create/client/catnip/render/SuperRenderTypeBuffer;Lnet/minecraft/client/render/command/OrderedRenderCommandQueueImpl;Lnet/minecraft/client/util/math/MatrixStack;F)V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/MinecraftClient;setCameraEntity(Lnet/minecraft/entity/Entity;)V",
			ordinal = 1,
			shift = At.Shift.AFTER
		),
		remap = false
	)
	private void vcc$flushQueuedEntityRendersBeforeLayers(
			MinecraftClient mc, SuperRenderTypeBuffer buffer, OrderedRenderCommandQueueImpl queue, MatrixStack ms, float pt, CallbackInfo ci
	) {
		mc.gameRenderer.getEntityRenderDispatcher().render();
	}
}
