package net.potatanata.arcanearmaments.common.item.custom.weapons;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class SoulScytheItem extends SwordItem {
    public SoulScytheItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (!world.isClient() && user instanceof PlayerEntity player) {

            player.spawnSweepAttackParticles();

            CowEntity sweep = new CowEntity(EntityType.COW, world);

            sweep.setPos(user.getEyePos().x, user.getEyePos().y, user.getEyePos().z);
            sweep.setForwardSpeed(100f);

            world.spawnEntity(sweep);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.arcamearmaments.soul_scythe.shift_down"));
        }
        else {
            tooltip.add(Text.translatable("tooltip.arcamearmaments.soul_scythe"));
        }
    }
}
