package ratao.moreweapons.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import ratao.moreweapons.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Items.DAGGERS)
                .add(ModItems.NETHERITE_DAGGER)
                .add(ModItems.DIAMOND_DAGGER)
                .add(ModItems.GOLDEN_DAGGER)
                .add(ModItems.IRON_DAGGER)
                .add(ModItems.STONE_DAGGER)
                .add(ModItems.WOODEN_DAGGER);

        getOrCreateTagBuilder(ModTags.Items.SCYTHES)
                .add(ModItems.NETHERITE_SCYTHE)
                .add(ModItems.DIAMOND_SCYTHE)
                .add(ModItems.GOLDEN_SCYTHE)
                .add(ModItems.IRON_SCYTHE)
                .add(ModItems.STONE_SCYTHE)
                .add(ModItems.WOODEN_SCYTHE);

        getOrCreateTagBuilder(ModTags.Items.KATANAS)
                .add(ModItems.NETHERITE_KATANA)
                .add(ModItems.DIAMOND_KATANA)
                .add(ModItems.GOLDEN_KATANA)
                .add(ModItems.IRON_KATANA)
                .add(ModItems.STONE_KATANA)
                .add(ModItems.WOODEN_KATANA);

        getOrCreateTagBuilder(ModTags.Items.MAGIC_STONES)
                .add(ModItems.FIRE_STONE)
                .add(ModItems.LIGHTNING_STONE);

        getOrCreateTagBuilder(ModTags.Items.STAFFS)
                .add(ModItems.LIGHTNING_STAFF)
                .add(ModItems.FIRE_STAFF);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(ModTags.Items.DAGGERS)
                .addTag(ModTags.Items.SCYTHES)
                .addTag(ModTags.Items.STAFFS)
                .addTag(ModTags.Items.KATANAS);

        getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE)
                .addTag(ModTags.Items.SCYTHES)
                .addTag(ModTags.Items.KATANAS);

        getOrCreateTagBuilder(ItemTags.WEAPON_ENCHANTABLE)
                .addTag(ModTags.Items.DAGGERS);
    }
}