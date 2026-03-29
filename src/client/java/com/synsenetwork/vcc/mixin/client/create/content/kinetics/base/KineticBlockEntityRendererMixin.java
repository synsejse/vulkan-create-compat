package com.synsenetwork.vcc.mixin.client.create.content.kinetics.base;

import com.zurrtum.create.client.content.kinetics.base.KineticBlockEntityRenderer;
import com.zurrtum.create.client.content.kinetics.base.KineticBlockEntityRenderer.KineticRenderState;
import com.synsenetwork.vcc.render.PonderKineticRenderContext;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Renders Ponder kinetic block entities directly into the scene buffer so translucent fluids can see them.
@Mixin(KineticBlockEntityRenderer.class)
public class KineticBlockEntityRendererMixin {
	@Inject(
		method = "render(Lcom/zurrtum/create/client/content/kinetics/base/KineticBlockEntityRenderer$KineticRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/render/state/CameraRenderState;)V",
		at = @At("HEAD"),
		cancellable = true,
		remap = false
	)
	private void vcc$renderDirectlyInPonder(
		KineticRenderState state,
		MatrixStack matrices,
		OrderedRenderCommandQueue queue,
		CameraRenderState cameraState,
		CallbackInfo ci
	) {
		VertexConsumerProvider buffer = PonderKineticRenderContext.get();
		if (buffer == null || state.support) {
			return;
		}

		state.model.light(state.lightmapCoordinates);
		state.model.rotateCentered(state.angle, state.direction);
		state.model.color(state.color);
		state.model.renderInto(matrices.peek(), buffer.getBuffer(state.layer));
		ci.cancel();
	}
}
