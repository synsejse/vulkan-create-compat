package com.synsenetwork.vcc.mixin.client.create.catnip.gui.element;

import com.zurrtum.create.client.catnip.gui.element.GuiGameElement;
import net.minecraft.client.gui.DrawContext;
import org.joml.Matrix3x2fStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Fixes shrunk Ponder category/tag icons by rendering scale-only GUI items directly.
@Mixin(GuiGameElement.GuiItemRenderBuilder.class)
abstract class GuiItemRenderBuilderMixin {
	@Shadow
	public abstract void clear();

	@Inject(method = "render", at = @At("HEAD"), cancellable = true, remap = false)
	private void vcc$renderScaleOnlyItemsDirectly(DrawContext graphics, CallbackInfo ci) {
		GuiRenderBuilderAccessor builder = (GuiRenderBuilderAccessor) this;
		if (builder.vcc$getPadding() != 0
			|| builder.vcc$getXRot() != 0.0F
			|| builder.vcc$getYRot() != 0.0F
			|| builder.vcc$getZRot() != 0.0F) {
			return;
		}

		GuiItemRenderBuilderAccessor itemBuilder = (GuiItemRenderBuilderAccessor) this;
		if (itemBuilder.vcc$getKey() != null) {
			this.clear();
		}

		float scale = builder.vcc$getScale();
		float x = ((AbstractRenderElementAccessor) this).vcc$getX();
		float y = ((AbstractRenderElementAccessor) this).vcc$getY();

		if (scale == 1.0F) {
			graphics.drawItem(itemBuilder.vcc$getStack(), (int) x, (int) y);
		} else {
			Matrix3x2fStack matrices = graphics.getMatrices();
			matrices.pushMatrix();
			matrices.translate(x, y);
			matrices.scale(scale);
			graphics.drawItem(itemBuilder.vcc$getStack(), 0, 0);
			matrices.popMatrix();
		}

		ci.cancel();
	}
}
