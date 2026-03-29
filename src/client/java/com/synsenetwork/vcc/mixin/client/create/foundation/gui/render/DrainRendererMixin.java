package com.synsenetwork.vcc.mixin.client.create.foundation.gui.render;

import com.zurrtum.create.client.foundation.gui.render.DrainRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

// Fixes upside-down drain GUI previews by flipping the final blit UVs.
@Mixin(DrainRenderer.class)
public class DrainRendererMixin {
    @ModifyArgs(
        method = "render(Lcom/zurrtum/create/client/foundation/gui/render/DrainRenderState;Lnet/minecraft/client/gui/render/state/GuiRenderState;I)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/render/state/TexturedQuadGuiElementRenderState;<init>(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/client/texture/TextureSetup;Lorg/joml/Matrix3x2f;IIIIFFFFILnet/minecraft/client/gui/ScreenRect;Lnet/minecraft/client/gui/ScreenRect;)V"
        ),
        remap = false
    )
    private void vcc$fixDrainBlitUv(Args args) {
        args.set(9, 0.0F);
        args.set(10, 1.0F);
    }
}
