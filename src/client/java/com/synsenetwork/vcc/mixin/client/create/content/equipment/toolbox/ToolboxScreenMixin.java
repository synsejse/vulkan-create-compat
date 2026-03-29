package com.synsenetwork.vcc.mixin.client.create.content.equipment.toolbox;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

// Fixes the toolbox lid partial preview appearing slightly oversized relative to the box body.
@Mixin(targets = "com.zurrtum.create.client.content.equipment.toolbox.ToolboxScreen")
public class ToolboxScreenMixin {
	@ModifyArg(
		method = "init",
		at = @At(
			value = "INVOKE",
			target = "Lcom/zurrtum/create/client/catnip/gui/element/GuiGameElement$GuiPartialRenderBuilder;scale(F)Lcom/zurrtum/create/client/catnip/gui/element/GuiGameElement$GuiPartialRenderBuilder;",
			ordinal = 1
		),
		index = 0,
		remap = false
	)
	private float vcc$shrinkToolboxLid(float scale) {
		return 2.95F;
	}
}
