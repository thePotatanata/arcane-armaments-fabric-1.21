package net.potatanata.arcanearmaments.common.entity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.potatanata.arcanearmaments.ArcaneArmaments;

public class ModEntities {

    public static final EntityType<ScytheSweepEntity> SCYTHE_SWEEP = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(ArcaneArmaments.MOD_ID, "scythe_sweep"),
            FabricEntityTypeBuilder.<ScytheSweepEntity>create(SpawnGroup.MISC, ScytheSweepEntity::new)
                    .dimensions(EntityDimensions.fixed(2f, 0.1f))
                    .trackRangeChunks(8)
                    .trackedUpdateRate(1)
                    .build()
    );

    public static void registerModEntities() {
        ArcaneArmaments.LOGGER.info("Registering Mod Entities for " + ArcaneArmaments.MOD_ID);
    }

}
