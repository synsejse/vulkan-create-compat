package com.synsenetwork.vcc.mixin.client.create.ponder.foundation;

import com.zurrtum.create.client.catnip.render.SuperRenderTypeBuffer;
import com.zurrtum.create.client.ponder.api.level.PonderLevel;
import com.zurrtum.create.client.ponder.foundation.element.WorldSectionElementImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Flushes opaque/cutout scene buffers and queued moving-part renders before translucent world sections.
@Mixin(WorldSectionElementImpl.class)
public class WorldSectionElementImplMixin {
	@Inject(
		method = "renderLayer(Lcom/zurrtum/create/client/ponder/api/level/PonderLevel;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/render/BlockRenderLayer;Lnet/minecraft/client/util/math/MatrixStack;FF)V",
		at = @At("HEAD"),
		remap = false
	)
	private void vcc$flushBeforeTranslucentWorldSection(
		PonderLevel world, VertexConsumerProvider bufferProvider, BlockRenderLayer type, MatrixStack poseStack, float fade, float pt, CallbackInfo ci
	) {
		if (type != BlockRenderLayer.TRANSLUCENT || !(bufferProvider instanceof SuperRenderTypeBuffer buffer)) {
			return;
		}

		buffer.draw(buffer.getRenderLayer(BlockRenderLayer.SOLID));
		buffer.draw(buffer.getRenderLayer(BlockRenderLayer.CUTOUT_MIPPED));
		buffer.draw(buffer.getRenderLayer(BlockRenderLayer.CUTOUT));
		MinecraftClient.getInstance().gameRenderer.getEntityRenderDispatcher().render();
	}
}
