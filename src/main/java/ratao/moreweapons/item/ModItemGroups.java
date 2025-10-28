package ratao.moreweapons.item; // Use o seu pacote

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import ratao.moreweapons.MoreWeapons;

public class ModItemGroups {

    public static void registerItemGroups() {
        MoreWeapons.LOGGER.info("Registrando Item Groups para " + MoreWeapons.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {

            content.addAfter(Items.NETHERITE_SWORD,
                    ModItems.WOODEN_SCYTHE,
                    ModItems.STONE_SCYTHE,
                    ModItems.IRON_SCYTHE,
                    ModItems.GOLDEN_SCYTHE,
                    ModItems.DIAMOND_SCYTHE,
                    ModItems.NETHERITE_SCYTHE
            );

            content.addAfter(ModItems.NETHERITE_SCYTHE,
                    ModItems.WOODEN_DAGGER,
                    ModItems.STONE_DAGGER,
                    ModItems.IRON_DAGGER,
                    ModItems.GOLDEN_DAGGER,
                    ModItems.DIAMOND_DAGGER,
                    ModItems.NETHERITE_DAGGER
            );

            content.addAfter(ModItems.NETHERITE_DAGGER,
                    ModItems.WOODEN_KATANA,
                    ModItems.STONE_KATANA,
                    ModItems.IRON_KATANA,
                    ModItems.GOLDEN_KATANA,
                    ModItems.DIAMOND_KATANA,
                    ModItems.NETHERITE_KATANA
            );

            content.addAfter(ModItems.NETHERITE_DAGGER,
                    ModItems.LIGHTNING_STAFF,
                    ModItems.FIRE_STAFF);

        });
    }
}