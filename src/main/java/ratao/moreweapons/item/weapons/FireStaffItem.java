package ratao.moreweapons.item.weapons;// package ratao.moreweapons.item.weapons; // (seu package)

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.WitherSkullEntity; // <--- IMPORTANTE
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes; // Para os novos efeitos
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents; // Mudei os sons
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.util.math.random.Random;

public class FireStaffItem extends Item { // Note: Mudei para estender Item
    private static final int MAX_CHARGE = 40; // Mesmo tempo de carga

    public FireStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return MAX_CHARGE;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient) {
            super.usageTick(world, user, stack, remainingUseTicks);
            return;
        }

        int chargeTime = MAX_CHARGE - remainingUseTicks;

        if (chargeTime < 10) {
            super.usageTick(world, user, stack, remainingUseTicks);
            return;
        }

        Random random = world.getRandom();


        // setup for particle origin pos
        Vec3d eyePos = user.getEyePos();
        Vec3d lookVec = user.getRotationVec(1.0F); // Vetor para FRENTE
        Vec3d rightVec = lookVec.crossProduct(new Vec3d(0, 1, 0)).normalize();
        Vec3d upVec = rightVec.crossProduct(lookVec);

        // particle origin
        double forwardOffset = 0.6; // how forward to the eyes pos
        double sideOffset = 0.35;   // how right to the eyes pos
        double upOffset = 0.1;   // how up to the eyes pos

        Vec3d particleOrigin = eyePos
                .add(lookVec.multiply(forwardOffset)) // goes forward
                .add(rightVec.multiply(sideOffset))  // goes right
                .add(upVec.multiply(upOffset));      // goes up

        if (chargeTime < 15) {
            for (int i = 0; i < 3; i++) {
                world.addParticle(ParticleTypes.LAVA,
                        particleOrigin.x + (random.nextDouble() - 0.5) * 0.5,
                        particleOrigin.y + (random.nextDouble() - 0.5) * 0.5,
                        particleOrigin.z + (random.nextDouble() - 0.5) * 0.5,
                        0.0, 0.01, 0.0);
            }
        }
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (world.isClient || !(user instanceof PlayerEntity player)) {
            return;
        }

        // Charge counter
        int chargeTime = MAX_CHARGE - remainingUseTicks;
        if (chargeTime < 10) {
            return; // not charged enough
        }

        Vec3d lookVec = player.getRotationVec(1.0F);
        double speed = 1.8;  // projectile speed

        FireballEntity fireball = new FireballEntity(
                world,
                player,
                lookVec.multiply(speed),
                1
        );

        fireball.setPosition(
                player.getEyePos().x,
                player.getEyePos().y - 0.1,
                player.getEyePos().z
        );

        world.spawnEntity(fireball);

        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F);

        // Durability
        stack.damage(1, user, EquipmentSlot.MAINHAND);

        // Cooldown
        player.getItemCooldownManager().set(this, 60);
    }
}