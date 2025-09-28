package ratao.moreweapons.effect;

import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import ratao.moreweapons.MoreWeapons;

public class ModEffects {

    public static final RegistryEntry <StatusEffect> BLEEDING = registerStatusEffect( "bleeding",
            new BleedingStatusEffect(StatusEffectCategory.HARMFUL, 0x800000, ParticleTypes.DAMAGE_INDICATOR));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect){
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of( MoreWeapons.MOD_ID, name), statusEffect);
    }

    public static void registerEffects(){
        MoreWeapons.LOGGER.info("Registering Mod Effects for" + MoreWeapons.MOD_ID);
    }
}
