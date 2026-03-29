package com.synsenetwork.vcc.mixin.client.create.ponder.foundation;

import com.synsenetwork.vcc.render.PonderKineticRenderContext;
import com.zurrtum.create.client.ponder.api.level.PonderLevel;
import com.zurrtum.create.client.ponder.foundation.element.WorldSectionElementImpl;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderManager;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Exposes the active Ponder buffer while block entities render so kinetic renderers can draw directly into it.
@Mixin(WorldSectionElementImpl.class)
public class PonderRenderContextMixin {
	@Inject(method = "renderFirst", at = @At("HEAD"), remap = false)
	private void vcc$beginPonderKineticCapture(
			BlockEntityRenderManager blockEntityRenderDispatcher, BlockRenderManager blockRenderManager, PonderLevel world, VertexConsumerProvider buffer, OrderedRenderCommandQueue queue, Camera camera, CameraRenderState cameraRenderState, MatrixStack poseStack, float fade, float pt, CallbackInfo ci
	) {
		PonderKineticRenderContext.set(buffer);
	}

	@Inject(method = "renderFirst", at = @At("TAIL"), remap = false)
	private void vcc$endPonderKineticCapture(
			BlockEntityRenderManager blockEntityRenderDispatcher, BlockRenderManager blockRenderManager, PonderLevel world, VertexConsumerProvider buffer, OrderedRenderCommandQueue queue, Camera camera, CameraRenderState cameraRenderState, MatrixStack poseStack, float fade, float pt, CallbackInfo ci
	) {
		PonderKineticRenderContext.clear();
	}
}
