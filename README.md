# vulkan-create-compat

<img src="src/main/resources/assets/vulkan-create-compat/icon.png" alt="vulkan-create-compat logo" width="128" height="128">

Compatibility fixes for running Create and Create Fly on VulkanMod.

For Minecraft Fabric 1.21.10

## What It Fixes

- Replaces Create Fly's GL-only custom render target setup with backend-neutral GPU textures so Create GUI and Ponder offscreen renders can initialize on Vulkan.
- Fixes upside-down Create GUI previews and Ponder scene previews by correcting the final blit UVs used for those render targets.
- Fixes shrunk Ponder category and tag item icons when they are rendered as simple scaled GUI items.
- Fixes the toolbox screen's lid preview rendering slightly larger than the rest of the toolbox model.
- Fixes Ponder water/translucency issues around Create kinetic previews such as water wheels by rendering kinetic block entities directly into the active Ponder scene buffer instead of relying on the normal queued block-entity path.
- Prevents Create Fly's old GL framebuffer and OIT composite paths from touching GL-only state when VulkanMod is active.

## Scope

This mod is client-only and currently targets compatibility issues between:

- [VulkanMod](https://modrinth.com/mod/vulkanmod)
- [Create Fly](https://modrinth.com/mod/create-fly)

It does not add a Vulkan-native Flywheel backend yet. The current fixes are focused on getting existing Create and Create Fly rendering paths to behave correctly under VulkanMod.
