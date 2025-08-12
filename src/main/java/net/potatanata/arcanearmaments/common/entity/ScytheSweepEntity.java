package net.potatanata.arcanearmaments.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MarkerEntity;

import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class ScytheSweepEntity extends PathAwareEntity {

    public ScytheSweepEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
}
