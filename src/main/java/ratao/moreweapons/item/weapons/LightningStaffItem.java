package ratao.moreweapons.item.weapons;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public class LightningStaffItem extends Item {
    private static final int MAX_CHARGE = 40;

    private static final double TARGET_SEARCH_RADIUS = 3.0;
    private static final double FORWARD_DISTANCE = 5.0;

    public LightningStaffItem(Settings settings) {
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

        if (chargeTime <= 15) {
            for (int i = 0; i < 3; i++) {
                world.addParticle(ParticleTypes.ENCHANT,
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

        int chargeTime = MAX_CHARGE - remainingUseTicks;
        if (chargeTime < 10) {
            return;
        }

        LivingEntity target = findClosestEnemy(world, player);

        if (target != null) {
            summonLightning(world, target);

            // item durability
            stack.damage(1, user, EquipmentSlot.MAINHAND);

            // 3 second cooldown
            player.getItemCooldownManager().set(this, 60);

            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
    }

    private LivingEntity findClosestEnemy(World world, PlayerEntity player) {
        // player eye position, and direction
        Vec3d eyePos = player.getEyePos();
        Vec3d lookDir = player.getRotationVec(1.0F).normalize();

        // set the center point of rect search 5 blocks in front of player
        Vec3d targetPoint = eyePos.add(lookDir.multiply(FORWARD_DISTANCE));

        // rect search
        Box searchBox = new Box(
                targetPoint.x - TARGET_SEARCH_RADIUS,
                targetPoint.y - TARGET_SEARCH_RADIUS,
                targetPoint.z - TARGET_SEARCH_RADIUS,
                targetPoint.x + TARGET_SEARCH_RADIUS,
                targetPoint.y + TARGET_SEARCH_RADIUS,
                targetPoint.z + TARGET_SEARCH_RADIUS
        );

        List<LivingEntity> nearbyEntities = world.getNonSpectatingEntities(LivingEntity.class, searchBox);

        LivingEntity closestTarget = null;
        double minDistanceSq = Double.MAX_VALUE;

        for (LivingEntity entity : nearbyEntities) {
            if (entity == player || !entity.isAlive()) continue;

            // distance from rect center point
            double distanceSq = entity.squaredDistanceTo(targetPoint);
            if (distanceSq < minDistanceSq && player.canSee(entity)) {
                minDistanceSq = distanceSq;
                closestTarget = entity;
            }
        }

        return closestTarget;
    }


    private void summonLightning(World world, LivingEntity target) {
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightning.setPosition(target.getPos());
        world.spawnEntity(lightning);
    }

}