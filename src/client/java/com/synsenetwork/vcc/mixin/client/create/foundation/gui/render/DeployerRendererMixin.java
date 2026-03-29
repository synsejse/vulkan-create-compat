package com.synsenetwork.vcc.mixin.client.create.foundation.gui.render;

import com.zurrtum.create.client.foundation.gui.render.DeployerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

// Fixes upside-down deployer GUI previews by flipping the final blit UVs.
@Mixin(DeployerRenderer.class)
public class DeployerRendererMixin {
    @ModifyArgs(
        method = "render(Lcom/zurrtum/create/client/foundation/gui/render/DeployerRenderState;Lnet/minecraft/client/gui/render/state/GuiRenderState;I)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/render/state/TexturedQuadGuiElementRenderState;<init>(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/client/texture/TextureSetup;Lorg/joml/Matrix3x2f;IIIIFFFFILnet/minecraft/client/gui/ScreenRect;Lnet/minecraft/client/gui/ScreenRect;)V"
        ),
        remap = false
    )
    private void vcc$fixDeployerBlitUv(Args args) {
        args.set(9, 0.0F);
        args.set(10, 1.0F);
    }
}
