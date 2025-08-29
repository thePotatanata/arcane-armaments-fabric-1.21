package net.potatanata.arcanearmaments.common.item;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.potatanata.arcanearmaments.ArcaneArmaments;
import net.potatanata.arcanearmaments.common.item.custom.FreeSoulItem;
import net.potatanata.arcanearmaments.common.item.custom.weapons.DreadedCleaverItem;
import net.potatanata.arcanearmaments.common.item.custom.weapons.SoulScytheItem;
import net.potatanata.arcanearmaments.common.item.custom.weapons.SoulStaffItem;

import static net.potatanata.arcanearmaments.ArcaneArmaments.MOD_ID;

public class ModItems {

    public static final Item SOUL_SHARD = registerItem("soul_shard", new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item FREE_SOUL = registerItem("free_soul", new FreeSoulItem(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item EYE_OF_THE_FORGOTTEN = registerItem("eye_of_the_forgotten", new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item DREAD_STEEL = registerItem("dread_steel", new Item(new Item.Settings()));
    public static final Item WITHER_BONE = registerItem("wither_bone", new Item(new Item.Settings().fireproof()));
    public static final Item ETHEREAL_CORE = registerItem("ethereal_core", new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item ARCANE_STEEL = registerItem("arcane_steel", new Item(new Item.Settings()));
    public static final Item ARCANE_TEAR = registerItem("arcane_tear", new Item(new Item.Settings()));

    public static final Item SOUL_STAFF = registerItem("soul_staff",
            new SoulStaffItem(ToolMaterials.NETHERITE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 1, -2.0f))));

    public static final Item SOUL_SCYTHE = registerItem("soul_scythe",
            new SoulScytheItem(ToolMaterials.NETHERITE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 5, -2.6f))));

    public static final Item DREADED_CLEAVER = registerItem("dreaded_cleaver",
            new DreadedCleaverItem(ToolMaterials.NETHERITE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 7, -3f))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }

    public static void registerModItems() {
        ArcaneArmaments.LOGGER.info("Registering Mod Items For " + MOD_ID);
    }
}
