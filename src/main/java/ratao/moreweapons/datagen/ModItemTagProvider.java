package ratao.moreweapons.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import ratao.moreweapons.item.ModItems; // Importe sua classe de itens

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

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(ModTags.Items.DAGGERS)
                .addTag(ModTags.Items.SCYTHES);

        // Enchantments
        getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE)
                .addTag(ModTags.Items.SCYTHES);
        getOrCreateTagBuilder(ItemTags.WEAPON_ENCHANTABLE)
                .addTag(ModTags.Items.DAGGERS);
    }
}