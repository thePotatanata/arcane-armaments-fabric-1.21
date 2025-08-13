package net.potatanata.arcanearmaments;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import net.potatanata.arcanearmaments.common.entity.ModEntities;
import net.potatanata.arcanearmaments.common.item.ModItems;
import net.potatanata.arcanearmaments.common.item.ModItemGroups;
import net.potatanata.arcanearmaments.common.item.custom.weapons.DreadedCleaverItem;
import net.potatanata.arcanearmaments.common.util.ModLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArcaneArmaments implements ModInitializer {
	public static final String MOD_ID = "arcanearmaments";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();

		ModEntities.registerModEntities();

		ModLootTableModifiers.modifyLootTables();

		ServerTickEvents.END_WORLD_TICK.register((ServerWorld world) -> {
			DreadedCleaverItem.tickEffects(world);
		});
	}
}