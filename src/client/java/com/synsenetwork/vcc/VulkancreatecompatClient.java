package com.synsenetwork.vcc;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;

public class VulkancreatecompatClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientLifecycleEvents.CLIENT_STARTED.register(client ->
			Vulkancreatecompat.LOGGER.info("Initializing Create Fly compatibility patches for VulkanMod"));
	}
}
