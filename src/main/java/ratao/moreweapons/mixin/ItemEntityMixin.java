package ratao.moreweapons.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ratao.moreweapons.item.ModItems;
import net.minecraft.util.math.random.Random;
import java.util.List;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {



    @Shadow public abstract ItemStack getStack();

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void makeInvulnerable(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

        ItemStack stack = this.getStack();

        if (stack.isOf(ModItems.EMPTY_STONE) ||
                stack.isOf(ModItems.FIRE_STONE) ||
                stack.isOf(ModItems.LIGHTNING_STONE))
        {
            cir.setReturnValue(false);
        }

    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        ItemEntity self = (ItemEntity) (Object) this;
        ItemStack stack = self.getStack();
        World world = self.getWorld();

        if (world.isClient) return;

        if (stack.isOf(ModItems.EMPTY_STONE)) {
            Random random = self.getWorld().getRandom();
            Vec3d particleOrigin = self.getPos();
            // ⚡ Verifica raio próximo
            Box area = self.getBoundingBox().expand(2.0);
            List<LightningEntity> bolts = world.getEntitiesByType(
                    EntityType.LIGHTNING_BOLT,
                    area,
                    EntityPredicates.VALID_ENTITY
            );

            if (!bolts.isEmpty()) {
                transformItem(self, ModItems.LIGHTNING_STONE, stack.getCount());

                world.playSound(
                        null,
                        self.getX(), self.getY(), self.getZ(),
                        SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER,
                        SoundCategory.WEATHER,
                        1.0f,
                        1.0f
                );

                world.addParticle(ParticleTypes.ENCHANT,
                        particleOrigin.x + (random.nextDouble() - 0.5) * 0.5,
                        particleOrigin.y + (random.nextDouble() - 0.5) * 0.5,
                        particleOrigin.z + (random.nextDouble() - 0.5) * 0.5,
                        0.0, 0.01, 0.0);
                return;
            }

            if (self.isInLava()
                    || world.getBlockState(self.getBlockPos()).isOf(Blocks.FIRE)
                    || world.getBlockState(self.getBlockPos().down()).isOf(Blocks.FIRE)
                    || world.getBlockState(self.getBlockPos()).isOf(Blocks.SOUL_FIRE)
                    || world.getBlockState(self.getBlockPos().down()).isOf(Blocks.SOUL_FIRE)) {
                transformItem(self, ModItems.FIRE_STONE, stack.getCount());

                for (int i = 0; i < 10; i++) {
                    world.addParticle(
                            ParticleTypes.FLAME, // ou ParticleTypes.LAVA
                            particleOrigin.x + (random.nextDouble() - 0.5) * 0.5,
                            particleOrigin.y + 0.1 + (random.nextDouble() - 0.5) * 0.5,
                            particleOrigin.z + (random.nextDouble() - 0.5) * 0.5,
                            0.0, 0.05, 0.0 // velocidade das partículas
                    );
                }

                world.playSound(
                        null, // todos os players próximos ou só quem está perto
                        self.getX(), self.getY(), self.getZ(),
                        SoundEvents.ENTITY_GENERIC_BURN,
                        SoundCategory.BLOCKS,
                        1.0f, // volume
                        1.0f  // pitch
                );

                return;
            }
        }
    }

    private void transformItem(ItemEntity original, Item newItem, int count) {
        World world = original.getWorld();
        if (world.isClient) return;

        ItemStack newStack = new ItemStack(newItem, count);
        var vel = original.getVelocity();

        ItemEntity newEntity = new ItemEntity(
                world,
                original.getX(),
                original.getY() + 0.5,
                original.getZ(),
                newStack,
                vel.getX(),
                0.1,
                vel.getZ()
        );

        newEntity.setPickupDelay(10);
        newEntity.setToDefaultPickupDelay();

        world.spawnEntity(newEntity);
        original.discard();
    }
}
