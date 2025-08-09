package net.potatanata.arcanearmaments.common.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class FreeSoulItem extends Item {
    public FreeSoulItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.arcamearmaments.free_soul.shift_down.1"));
            tooltip.add(Text.translatable("tooltip.arcamearmaments.free_soul.shift_down.2"));
        }
        else {
            tooltip.add(Text.translatable("tooltip.arcamearmaments.free_soul"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
