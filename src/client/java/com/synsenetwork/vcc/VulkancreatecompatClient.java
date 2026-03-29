package com.synsenetwork.vcc;

import net.fabricmc.api.ClientModInitializer;

public class VulkancreatecompatClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Vulkancreatecompat.LOGGER.info("Initializing Create Fly compatibility patches for VulkanMod");
	}
}
