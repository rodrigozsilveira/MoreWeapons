package ratao.moreweapons.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ratao.moreweapons.MoreWeapons;
import ratao.moreweapons.item.weapons.*;

public class ModItems {

    public static final Item WOODEN_SCYTHE = registerItem("wooden_scythe",
            new ScytheItem(ToolMaterials.WOOD, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            ScytheItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -3.4F))
            ));
    public static final Item STONE_SCYTHE = registerItem("stone_scythe",
            new ScytheItem(ToolMaterials.STONE, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            ScytheItem.createAttributeModifiers(ToolMaterials.STONE, 5, -3.4F))
            ));

    public static final Item IRON_SCYTHE = registerItem("iron_scythe",
            new ScytheItem(ToolMaterials.IRON, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            ScytheItem.createAttributeModifiers(ToolMaterials.IRON, 5, -3.4F))
            ));

    public static final Item GOLDEN_SCYTHE = registerItem("golden_scythe",
            new ScytheItem(ToolMaterials.GOLD, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            ScytheItem.createAttributeModifiers(ToolMaterials.GOLD, 3, -2.8F))
            ));

    public static final Item DIAMOND_SCYTHE = registerItem("diamond_scythe",
            new ScytheItem(ToolMaterials.DIAMOND, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            ScytheItem.createAttributeModifiers(ToolMaterials.DIAMOND, 5, -3.4F))
            ));

    public static final Item NETHERITE_SCYTHE = registerItem("netherite_scythe",
            new ScytheItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            ScytheItem.createAttributeModifiers(ToolMaterials.NETHERITE, 5, -3.4F))
            ));

    // === DAGGERS ===
    public static final Item WOODEN_DAGGER = registerItem("wooden_dagger",
            new DaggerItem(ToolMaterials.WOOD, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            DaggerItem.createAttributeModifiers(ToolMaterials.WOOD, 2, 0.0F))
                    ,false
            ));

    public static final Item STONE_DAGGER = registerItem("stone_dagger",
            new DaggerItem(ToolMaterials.STONE, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            DaggerItem.createAttributeModifiers(ToolMaterials.STONE, 2, 0.0F))
                    ,false
            ));

    public static final Item IRON_DAGGER = registerItem("iron_dagger",
            new DaggerItem(ToolMaterials.IRON, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            DaggerItem.createAttributeModifiers(ToolMaterials.IRON, 2, 0.0F))
                    ,true
            ));

    public static final Item GOLDEN_DAGGER = registerItem("golden_dagger",
            new DaggerItem(ToolMaterials.GOLD, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            DaggerItem.createAttributeModifiers(ToolMaterials.GOLD, 2, 0.2F)) // gold: +velocidade
                    ,true
            ));

    public static final Item DIAMOND_DAGGER = registerItem("diamond_dagger",
            new DaggerItem(ToolMaterials.DIAMOND, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            DaggerItem.createAttributeModifiers(ToolMaterials.DIAMOND, 2, 0.0F))
                    ,true
            ));

    public static final Item NETHERITE_DAGGER = registerItem("netherite_dagger",
            new DaggerItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            DaggerItem.createAttributeModifiers(ToolMaterials.NETHERITE, 2, 0.0F))
                    ,true
            ));

    public static final Item WOODEN_KATANA = registerItem("wooden_katana",
            new KatanaItem(ToolMaterials.WOOD, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            KatanaItem.createAttributeModifiers(ToolMaterials.WOOD, 2, -1.2F))
            ));

    public static final Item STONE_KATANA = registerItem("stone_katana",
            new KatanaItem(ToolMaterials.STONE, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            KatanaItem.createAttributeModifiers(ToolMaterials.STONE, 2, -1.2F))
            ));

    public static final Item IRON_KATANA = registerItem("iron_katana",
            new KatanaItem(ToolMaterials.IRON, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            KatanaItem.createAttributeModifiers(ToolMaterials.IRON, 2, -1.2F))
            ));

    public static final Item GOLDEN_KATANA = registerItem("golden_katana",
            new KatanaItem(ToolMaterials.GOLD, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            KatanaItem.createAttributeModifiers(ToolMaterials.GOLD, 2, -1.0F))
            ));

    public static final Item DIAMOND_KATANA = registerItem("diamond_katana",
            new KatanaItem(ToolMaterials.DIAMOND, new Item.Settings()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            KatanaItem.createAttributeModifiers(ToolMaterials.DIAMOND, 2, -1.2F))
            ));

    public static final Item NETHERITE_KATANA = registerItem("netherite_katana",
            new KatanaItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof()
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            KatanaItem.createAttributeModifiers(ToolMaterials.NETHERITE, 2, -1.2F))
            ));

    public static final Item LIGHTNING_STAFF = registerItem("lightning_staff",
            new LightningStaffItem(new Item.Settings())
            );

    public static final Item FIRE_STAFF = registerItem("fire_staff",
            new FireStaffItem(new Item.Settings())
    );

    public static final Item EMPTY_STONE = registerItem("empty_stone",
            new Item(new Item.Settings().fireproof()));

    public static final Item FIRE_STONE = registerItem("fire_stone",
            new Item(new Item.Settings().fireproof())
    );

    public static final Item LIGHTNING_STONE = registerItem("lightning_stone",
            new Item(new Item.Settings().fireproof())
    );

    private static Item registerItem (String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(MoreWeapons.MOD_ID, name), item );
    }

    public static void registerModItems(){
        MoreWeapons.LOGGER.info("Registering Mod Items for " + MoreWeapons.MOD_ID);

    }
}
