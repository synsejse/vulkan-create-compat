package com.synsenetwork.vcc.mixin.client.create.catnip.gui.render;

import com.zurrtum.create.client.catnip.gui.render.EntityBlockRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

// Fixes upside-down entity-block GUI previews by flipping the final blit UVs.
@Mixin(EntityBlockRenderer.class)
public class EntityBlockRendererMixin {
    @ModifyArgs(
        method = "render(Lcom/zurrtum/create/client/catnip/gui/render/EntityBlockRenderState;Lnet/minecraft/client/gui/render/state/GuiRenderState;I)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/render/state/TexturedQuadGuiElementRenderState;<init>(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/client/texture/TextureSetup;Lorg/joml/Matrix3x2f;IIIIFFFFILnet/minecraft/client/gui/ScreenRect;Lnet/minecraft/client/gui/ScreenRect;)V"
        ),
        remap = false
    )
    private void vcc$fixEntityBlockBlitUv(Args args) {
        args.set(9, 0.0F);
        args.set(10, 1.0F);
    }
}
