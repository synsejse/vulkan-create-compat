package com.synsenetwork.vcc.mixin.client.create.catnip.gui.element;

import com.zurrtum.create.client.catnip.gui.element.AbstractRenderElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractRenderElement.class)
public interface AbstractRenderElementAccessor {
	@Accessor("x")
	float vcc$getX();

	@Accessor("y")
	float vcc$getY();
}
