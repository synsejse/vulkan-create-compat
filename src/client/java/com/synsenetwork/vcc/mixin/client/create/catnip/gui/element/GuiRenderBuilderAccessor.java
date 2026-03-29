package com.synsenetwork.vcc.mixin.client.create.catnip.gui.element;

import com.zurrtum.create.client.catnip.gui.element.GuiGameElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiGameElement.GuiRenderBuilder.class)
public interface GuiRenderBuilderAccessor {
	@Accessor("scale")
	float vcc$getScale();

	@Accessor("padding")
	int vcc$getPadding();

	@Accessor("xRot")
	float vcc$getXRot();

	@Accessor("yRot")
	float vcc$getYRot();

	@Accessor("zRot")
	float vcc$getZRot();
}
