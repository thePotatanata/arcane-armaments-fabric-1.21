package net.potatanata.arcanearmaments.common.item.custom.weapons;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SoulStaffItem extends SwordItem {
    public SoulStaffItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (!world.isClient() && user instanceof PlayerEntity player) {

            player.spawnSweepAttackParticles();

            VexEntity wisp = new VexEntity(EntityType.VEX, world);

            wisp.setPos(user.getEyePos().x, user.getEyePos().y, user.getEyePos().z);

            world.spawnEntity(wisp);
        }
        return super.use(world, user, hand);
    }

}
