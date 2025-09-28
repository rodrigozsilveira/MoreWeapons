package ratao.moreweapons.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ratao.moreweapons.MoreWeapons;
import ratao.moreweapons.entity.weapons.SpearEntity;

public class ModEntities {

    public static final EntityType<SpearEntity> SPEAR_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MoreWeapons.MOD_ID, "netherite_spear"),
            EntityType.Builder.<SpearEntity>create(SpearEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5F, 0.5F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(10)
                    .build()
    );


    public static void registerModEntities() {
        MoreWeapons.LOGGER.info("Registrando Entidades para o mod: " + MoreWeapons.MOD_ID);
    }
}