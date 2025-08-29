package net.potatanata.arcanearmaments.common.item.custom.weapons;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.potatanata.arcanearmaments.client.ScreenShake;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DreadedCleaverItem extends SwordItem {
    // Tracks players with the active spinning effect and how long it should last
    private static final Map<UUID, Integer> activeEffects = new HashMap<>();

    public DreadedCleaverItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        // Only trigger if item is not on cooldown
        if (!player.getItemCooldownManager().isCoolingDown(this)) {
            if (world.isClient) {
                // Client-side: trigger screen shake (visual effect only)
                ScreenShake.startShake(10, 5.0F); // Shake for 10 ticks at intensity 5.0F
            }
            if (!world.isClient) {
                // Server-side: start effect timer for player (200 ticks = 10 seconds)
                activeEffects.put(player.getUuid(), 200);

                // Apply Strength II effect for 30 seconds
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,
                        600, // duration in ticks
                        1,   // amplifier (0 = level I, 1 = level II)
                        true, // ambient
                        true  // show particles
                ));

                // Play Wither spawn sound at player location
                world.playSound(
                        null, // null = audible to all nearby players
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.ENTITY_WITHER_SPAWN,
                        SoundCategory.PLAYERS,
                        1.0f, // volume
                        0.5f  // pitch
                );
            }
        }

        // Put the weapon on cooldown for 60 seconds (1200 ticks)
        player.getItemCooldownManager().set(this, 1200);
        return super.use(world, player, hand);
    }

    // Called from a tick event in the main mod class
    public static void tickEffects(ServerWorld world) {
        // Reduce remaining effect time for each player
        activeEffects.replaceAll((uuid, time) -> time - 1);

        // Remove players whose effect timer has run out
        activeEffects.entrySet().removeIf(entry -> entry.getValue() <= 0);

        // Spawn particles for all players with active effect
        for (UUID uuid : activeEffects.keySet()) {
            PlayerEntity player = world.getPlayerByUuid(uuid);
            if (player != null) {
                spawnSpinningCircle(world, player);
            }
        }
    }

    // Spawns a rotating particle circle around the player
    private static void spawnSpinningCircle(ServerWorld world, PlayerEntity player) {
        // Center coordinates at player's position
        double centerX = player.getX();
        double centerY = player.getY();
        double centerZ = player.getZ();

        double radius = 1.5; // Circle radius
        int points = 32;     // Number of points in the circle

        // Rotation changes with world time for smooth spinning animation
        double rotation = (world.getTime() % 360) * 0.15;

        for (int i = 0; i < points; i++) {
            // Calculate angle for each point in the circle
            double angle = (2 * Math.PI * i) / points + rotation;
            double xOffset = Math.cos(angle) * radius;
            double zOffset = Math.sin(angle) * radius;

            // ENCHANT particle on the edge of the circle
            world.spawnParticles(
                    ParticleTypes.ENCHANT,
                    centerX + xOffset,
                    centerY + 0.05,
                    centerZ + zOffset,
                    1, // count
                    0, 0, 0, // offset spread
                    0  // speed
            );

            // SMOKE particle closer to the center for a layered effect
            world.spawnParticles(
                    ParticleTypes.SMOKE,
                    centerX + xOffset / 2,
                    centerY + 0.05,
                    centerZ + zOffset / 2,
                    1, // count
                    0.1, 0, 0.1, // offset spread
                    0  // speed
            );
        }
    }
}