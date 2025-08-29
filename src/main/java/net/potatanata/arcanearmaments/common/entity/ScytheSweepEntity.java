package net.potatanata.arcanearmaments.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class ScytheSweepEntity extends Entity {
    private LivingEntity owner;
    private UUID ownerUuid;
    private int lifeTicks;

    public ScytheSweepEntity(EntityType<? extends ScytheSweepEntity> type, World world) {
        super(type, world);
    }

    public ScytheSweepEntity(World world, LivingEntity owner) {
        this(ModEntities.SCYTHE_SWEEP, world);
        this.owner = owner;
        this.ownerUuid = owner.getUuid();

        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());

        Vec3d look = owner.getRotationVec(1.0F).normalize().multiply(0.6); // speed
        this.setVelocity(look);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    public void tick() {
        super.tick();

        // Move entity forward
        this.move(MovementType.SELF, this.getVelocity());

        // Damage entities in path
        if (!getWorld().isClient) {
            List<LivingEntity> targets = getWorld().getEntitiesByClass(
                    LivingEntity.class,
                    this.getBoundingBox().expand(0.5),
                    e -> e.isAlive() && e != owner
            );

            for (LivingEntity target : targets) {
                target.damage(getWorld().getDamageSources().magic(), 6.0F);
            }
        }

        // Client-side particles
        if (getWorld().isClient) {
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() - 1, getY(), getZ() - 1,0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() - 0.9, getY(), getZ() - 0.9, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() - 0.8, getY(), getZ() - 0.8, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() - 0.7, getY(), getZ() - 0.7, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() - 0.6, getY(), getZ() - 0.6, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() - 0.5, getY(), getZ() - 0.5, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() - 0.4, getY(), getZ() - 0.4, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() - 0.3, getY(), getZ() - 0.3, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() - 0.2, getY(), getZ() - 0.2, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() - 0.1, getY(), getZ() - 0.1, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX(), getY(), getZ(), 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() + 0.1, getY(), getZ()+ 0.1, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() + 0.2, getY(), getZ()+ 0.2, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() + 0.3, getY(), getZ()+ 0.3, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() + 0.4, getY(), getZ()+ 0.4, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() + 0.5, getY(), getZ()+ 0.5, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() + 0.6, getY(), getZ()+ 0.6, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() + 0.7, getY(), getZ()+ 0.7, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() + 0.8, getY(), getZ()+ 0.8, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() + 0.9, getY(), getZ()+ 0.9, 0, 0, 0);
            getWorld().addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX() + 1, getY(), getZ() + 1, 0, 0, 0);
        }

        // Despawn after 20 ticks
        lifeTicks++;
        if (lifeTicks >= 20) {
            this.discard();
        }
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        lifeTicks = nbt.getInt("LifeTicks");
        if (nbt.containsUuid("OwnerUUID")) {
            this.ownerUuid = nbt.getUuid("OwnerUUID");
            if (getWorld() instanceof ServerWorld serverWorld) {
                Entity entity = serverWorld.getEntity(this.ownerUuid);
                if (entity instanceof LivingEntity living) {
                    this.owner = living;
                }
            }
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt("LifeTicks", lifeTicks);
        if (owner != null) {
            nbt.putUuid("OwnerUUID", owner.getUuid());
        }
    }
}