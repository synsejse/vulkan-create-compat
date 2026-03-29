package com.synsenetwork.vcc.mixin.client.create.catnip.gui.element;

import com.zurrtum.create.client.catnip.gui.element.GuiGameElement;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiGameElement.GuiItemRenderBuilder.class)
public interface GuiItemRenderBuilderAccessor {
	@Accessor("stack")
	ItemStack vcc$getStack();

	@Accessor("key")
	Object vcc$getKey();
}
