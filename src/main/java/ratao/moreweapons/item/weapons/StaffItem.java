package ratao.moreweapons.item.weapons;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class StaffItem extends Item {
    private static final int MAX_CHARGE = 40; // ~2 segundos para carregar

    private static final double TARGET_SEARCH_RADIUS = 3.0; // raio de busca ao redor do ponto alvo
    private static final double FORWARD_DISTANCE = 5.0;

    public StaffItem(Settings settings) {
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
        return UseAction.BLOCK;
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
            // Invoca o raio no alvo
            summonLightning(world, target);

            // Danifica o item
            stack.damage(1, user, EquipmentSlot.MAINHAND);

            // Adiciona um cooldown para não ser usado repetidamente (ex: 3 segundos)
            player.getItemCooldownManager().set(this, 60);

            // Adiciona som para o jogador
            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
    }

    /**
     * Encontra a entidade viva mais próxima em um raio ao redor do jogador.
     */
    private LivingEntity findClosestEnemy(World world, PlayerEntity player) {
        // Pega a posição dos olhos e direção do jogador
        Vec3d eyePos = player.getEyePos();                // posição dos olhos
        Vec3d lookDir = player.getRotationVec(1.0F).normalize(); // direção que ele está olhando

        // Calcula o ponto alvo a 5 blocos na direção que o jogador olha
        Vec3d targetPoint = eyePos.add(lookDir.multiply(FORWARD_DISTANCE));

        // Cria uma caixa de busca centrada no ponto alvo
        Box searchBox = new Box(
                targetPoint.x - TARGET_SEARCH_RADIUS,
                targetPoint.y - TARGET_SEARCH_RADIUS,
                targetPoint.z - TARGET_SEARCH_RADIUS,
                targetPoint.x + TARGET_SEARCH_RADIUS,
                targetPoint.y + TARGET_SEARCH_RADIUS,
                targetPoint.z + TARGET_SEARCH_RADIUS
        );

        // Busca entidades vivas dentro do raio
        List<LivingEntity> nearbyEntities = world.getNonSpectatingEntities(LivingEntity.class, searchBox);

        LivingEntity closestTarget = null;
        double minDistanceSq = Double.MAX_VALUE;

        for (LivingEntity entity : nearbyEntities) {
            if (entity == player || !entity.isAlive()) continue;

            // mede a distância a partir do ponto alvo, não do jogador
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