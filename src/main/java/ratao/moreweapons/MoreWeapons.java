package ratao.moreweapons;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratao.moreweapons.item.ModItemGroups;
import ratao.moreweapons.item.ModItems;

public class MoreWeapons implements ModInitializer {
	public static final String MOD_ID = "moreweapons";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
	}
}