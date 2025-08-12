package net.potatanata.arcanearmaments.common.item.custom.weapons;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.potatanata.arcanearmaments.common.entity.ModEntities;
import net.potatanata.arcanearmaments.common.entity.ScytheSweepEntity;

import java.util.List;

public class SoulScytheItem extends SwordItem {
    public SoulScytheItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (!world.isClient() && user instanceof PlayerEntity player) {

            player.spawnSweepAttackParticles();

            ScytheSweepEntity sweep = new ScytheSweepEntity(ModEntities.SCYTHE_SWEEP, world);

            sweep.refreshPositionAndAngles(user.getEyePos().x, user.getEyePos().y, user.getEyePos().z, user.getYaw(), user.getPitch());

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
