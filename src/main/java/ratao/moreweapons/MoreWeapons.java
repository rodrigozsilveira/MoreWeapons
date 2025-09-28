package ratao.moreweapons;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratao.moreweapons.datagen.ModItemTagProvider;
import ratao.moreweapons.effect.BleedingStatusEffect;
import ratao.moreweapons.effect.ModEffects;
import ratao.moreweapons.item.ModItemGroups;
import ratao.moreweapons.item.ModItems;

public class MoreWeapons implements ModInitializer {
	public static final String MOD_ID = "moreweapons";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
        ModEffects.registerEffects();
	}
}