package ratao.moreweapons.datagen;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import ratao.moreweapons.MoreWeapons;

public class ModTags {
    public static class Items {

        public static final TagKey<Item> DAGGERS = createTag("daggers");

        public static final TagKey<Item> SCYTHES = createTag("scythes");

        public static final TagKey<Item> KATANAS = createTag("katanas");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(MoreWeapons.MOD_ID, name));
        }
    }

    public static class Blocks {
    }
}