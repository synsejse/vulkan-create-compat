package com.synsenetwork.vcc.mixin.client.create.ponder.foundation.render;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import org.spongepowered.asm.mixin.injection.At;

// Fixes upside-down Ponder scene previews by flipping the final scene blit UVs.
@Mixin(targets = "com.zurrtum.create.client.ponder.foundation.render.SceneRenderer")
public class SceneRendererMixin {
	@ModifyArgs(
		method = "render(Lcom/zurrtum/create/client/ponder/foundation/render/SceneRenderState;Lnet/minecraft/client/gui/render/state/GuiRenderState;I)V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/gui/render/state/TexturedQuadGuiElementRenderState;<init>(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/client/texture/TextureSetup;Lorg/joml/Matrix3x2f;IIIIFFFFILnet/minecraft/client/gui/ScreenRect;Lnet/minecraft/client/gui/ScreenRect;)V"
		),
		remap = false
	)
	private void vcc$fixSceneRendererBlitUv(Args args) {
		args.set(9, 0.0F);
		args.set(10, 1.0F);
	}
}
