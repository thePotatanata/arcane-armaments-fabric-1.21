package net.potatanata.arcanearmaments;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.potatanata.arcanearmaments.client.ScreenShake;
import net.potatanata.arcanearmaments.common.entity.ModEntities;

import java.util.Random;

public class ArcaneArmamentsClient implements ClientModInitializer {
    private final Random random = new Random();

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.SCYTHE_SWEEP, EmptyEntityRenderer::new);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ScreenShake.tick();
        });

        WorldRenderEvents.START.register(context -> {
            if (ScreenShake.shakeTicks > 0) {
                float offset = ScreenShake.getOffset();
                float yaw = (random.nextFloat() - 0.5F) * offset;
                float pitch = (random.nextFloat() - 0.5F) * offset;

                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc.player != null) {
                    mc.player.setYaw(mc.player.getYaw() + yaw);
                    mc.player.setPitch(mc.player.getPitch() + pitch);
                }
            }
        });
    }
}
