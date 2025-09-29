package ratao.moreweapons.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import org.joml.Vector3f;

public class BleedingStatusEffect extends StatusEffect {


    protected BleedingStatusEffect(StatusEffectCategory category, int color, ParticleEffect particleEffect) {
        super(category, color, particleEffect);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int tickRate = 15 >> amplifier;
        return tickRate > 0 ? duration % tickRate == 0 : true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(entity.getDamageSources().magic(), 1.0F + amplifier);

        if (entity.getWorld() instanceof ServerWorld serverWorld) {
            Vector3f redColor = new Vector3f(1.0F, 0.0F, 0.0F);
            serverWorld.spawnParticles(
                    new DustParticleEffect(redColor, 1.0F),
                    entity.getX(),
                    entity.getY() + entity.getHeight() * 0.5,
                    entity.getZ(),
                    8,      // amount of particles
                    0.2, 0.2, 0.2, // spread XYZ
                    0.02   // speed
            );
        }

        return true;
    }
}
