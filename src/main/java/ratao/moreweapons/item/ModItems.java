package ratao.moreweapons.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ratao.moreweapons.MoreWeapons;
import ratao.moreweapons.item.weapons.ScytheItem;
import ratao.moreweapons.item.weapons.SpearItem;

public class ModItems {

    public static final Item WOODEN_SCYTHE = registerItem("wooden_scythe",
            new ScytheItem(ToolMaterials.WOOD, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            ScytheItem.createAttributeModifiers(ToolMaterials.WOOD, 3, -3.0F))
            ));

    public static final Item IRON_SCYTHE = registerItem("iron_scythe",
            new ScytheItem(ToolMaterials.IRON, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            ScytheItem.createAttributeModifiers(ToolMaterials.IRON, 5, -3.0F))
            ));

    public static final Item STONE_SCYTHE = registerItem("stone_scythe",
            new ScytheItem(ToolMaterials.STONE, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            ScytheItem.createAttributeModifiers(ToolMaterials.STONE, 4, -3.0F))
            ));

    public static final Item GOLDEN_SCYTHE = registerItem("golden_scythe",
            new ScytheItem(ToolMaterials.GOLD, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            ScytheItem.createAttributeModifiers(ToolMaterials.GOLD, 3, -2.8F))
            ));

    public static final Item DIAMOND_SCYTHE = registerItem("diamond_scythe",
            new ScytheItem(ToolMaterials.DIAMOND, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            ScytheItem.createAttributeModifiers(ToolMaterials.DIAMOND, 6, -3.0F))
            ));

    public static final Item NETHERITE_SCYTHE = registerItem("netherite_scythe",
            new ScytheItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            ScytheItem.createAttributeModifiers(ToolMaterials.NETHERITE, 7, -3.0F))
            ));

    public static final Item WOODEN_SPEAR = registerItem("wooden_spear",
            new SpearItem(ToolMaterials.WOOD, new Item.Settings()));

    public static final Item STONE_SPEAR = registerItem("stone_spear",
            new SpearItem(ToolMaterials.STONE, new Item.Settings()));

    public static final Item IRON_SPEAR = registerItem("iron_spear",
            new SpearItem(ToolMaterials.IRON, new Item.Settings()));

    public static final Item GOLDEN_SPEAR = registerItem("gold_spear",
            new SpearItem(ToolMaterials.GOLD, new Item.Settings()));

    public static final Item DIAMOND_SPEAR = registerItem("diamond_spear",
            new SpearItem(ToolMaterials.DIAMOND, new Item.Settings()));

    public static final Item NETHERITE_SPEAR = registerItem("netherite_spear",
            new SpearItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof())); // fireproof() é importante!


    private static Item registerItem (String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(MoreWeapons.MOD_ID, name), item );
    }

    public static void registerModItems(){
        MoreWeapons.LOGGER.info("Registering Mod Items for " + MoreWeapons.MOD_ID);

    }
}
