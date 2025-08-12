package net.potatanata.arcanearmaments.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.potatanata.arcanearmaments.ArcaneArmaments;

public class ModEntities {
    public static final EntityType<ScytheSweepEntity> SCYTHE_SWEEP = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(ArcaneArmaments.MOD_ID, "scythe_sweep"),
            EntityType.Builder.create(ScytheSweepEntity::new, SpawnGroup.MISC)
                    .dimensions(2f, 0.1f).build());


    public static void registerModEntities() {
        ArcaneArmaments.LOGGER.info("Registering Mod Entities for " + ArcaneArmaments.MOD_ID);
    }

}
