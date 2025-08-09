package net.potatanata.arcanearmaments.common.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.potatanata.arcanearmaments.ArcaneArmaments;

import static net.potatanata.arcanearmaments.ArcaneArmaments.MOD_ID;

public class ModItemGroups {
    public static final ItemGroup ARCANE_ARMAMENTS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MOD_ID, "arcane_armaments"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.FREE_SOUL))
                    .displayName(Text.translatable("itemgroup.arcanearmaments.arcane_armaments"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.FREE_SOUL);
                        entries.add(ModItems.SOUL_SHARD);
                        entries.add(ModItems.EYE_OF_THE_FORGOTTEN);
                        entries.add(ModItems.DREAD_STEEL);
                        entries.add(ModItems.WITHER_BONE);

                    }).build());

    public static final ItemGroup ARCANE_ARMAMENTS_WEAPON_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MOD_ID, "arcane_armaments_weapon"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.SOUL_SCYTHE))
                    .displayName(Text.translatable("itemgroup.arcanearmaments.arcane_armaments_weapon"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.SOUL_SCYTHE);
                        entries.add(ModItems.SOUL_STAFF);
                        entries.add(ModItems.DREADED_BLADE);

                    }).build());



    public static void registerItemGroups() {
        ArcaneArmaments.LOGGER.info("Registering Item Groups for " + MOD_ID);
    }
}
