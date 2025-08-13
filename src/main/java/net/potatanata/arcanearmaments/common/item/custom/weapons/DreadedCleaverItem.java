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
    private static final Map<UUID, Integer> activeEffects = new HashMap<>();


    public DreadedCleaverItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!player.getItemCooldownManager().isCoolingDown(this)) {
            if (world.isClient) {
                ScreenShake.startShake(10, 5.0F); // 10 ticks, intensity 2
            }
            if (!world.isClient) {
                activeEffects.put(player.getUuid(), 200);
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,
                        600,
                        1,
                        true,
                        true));

                world.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.ENTITY_PLAYER_LEVELUP,
                        SoundCategory.PLAYERS,
                        1.0f,
                        1.0f
                );
            }

        }

        player.getItemCooldownManager().set(this, 1200);
        return super.use(world, player, hand);
    }

    // Called from tick event in main mod class
    public static void tickEffects(ServerWorld world) {
        // Reduce timers and remove expired
        activeEffects.replaceAll((uuid, time) -> time - 1);
        activeEffects.entrySet().removeIf(entry -> entry.getValue() <= 0);

        // Spawn particles for all active players
        for (UUID uuid : activeEffects.keySet()) {
            PlayerEntity player = world.getPlayerByUuid(uuid);
            if (player != null) {
                spawnSpinningCircle(world, player);
            }
        }
    }

    private static void spawnSpinningCircle(ServerWorld world, PlayerEntity player) {
        double centerX = player.getX();
        double centerY = player.getY();
        double centerZ = player.getZ();

        double radius = 1.5;
        int points = 32;

        // Rotation based on world time for smooth spinning
        double rotation = (world.getTime() % 360) * 0.15;

        for (int i = 0; i < points; i++) {
            double angle = (2 * Math.PI * i) / points + rotation;
            double xOffset = Math.cos(angle) * radius;
            double zOffset = Math.sin(angle) * radius;

            world.spawnParticles(
                    ParticleTypes.ENCHANT,
                    centerX + xOffset,
                    centerY + 0.05,
                    centerZ + zOffset,
                    1, // count
                    0, 0, 0, // offset spread
                    0  // speed
            );

            world.spawnParticles(
                    ParticleTypes.SMOKE,
                    centerX + xOffset / 2,
                    centerY + 0.05,
                    centerZ + zOffset / 2,
                    1, // count
                    0.1, 0, 0.1, // offset spread
                    0  // speed
            );

            world.spawnParticles(
                    ParticleTypes.ASH,
                    centerX + xOffset / 1.5,
                    centerY + 2,
                    centerZ + zOffset / 1.5,
                    2, // count
                    0.1, 0, 0.1, // offset spread
                    0  // speed
            );
        }
    }
}
