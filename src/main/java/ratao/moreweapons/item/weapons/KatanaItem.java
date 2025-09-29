package ratao.moreweapons.item.weapons;

import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class KatanaItem extends SwordItem {

    public KatanaItem(ToolMaterial material, Settings settings) {
        super(material, settings.component(DataComponentTypes.TOOL, createToolComponent()));
    }

    private static ToolComponent createToolComponent() {
        return new ToolComponent(
                List.of(
                        ToolComponent.Rule.ofAlwaysDropping(List.of(Blocks.COBWEB), 15.0F),
                        ToolComponent.Rule.of(BlockTags.SWORD_EFFICIENT, 1.5F),
                        ToolComponent.Rule.of(BlockTags.LEAVES, 2.0F),
                        ToolComponent.Rule.of(BlockTags.CROPS, 2.0F)
                ),
                1.0F,
                2
        );
    }

    public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, int baseAttackDamage, float attackSpeed) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, baseAttackDamage + material.getAttackDamage(), EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            Vec3d lookDir = user.getMovement().normalize();

            double dashStrength = 1.2;

            user.addVelocity(
                    lookDir.x * dashStrength,
                    0.2,
                    lookDir.z * dashStrength
            );

            user.velocityModified = true;

            user.getItemCooldownManager().set(this, 20);

            world.playSound(
                    null,
                    user.getBlockPos(),
                    SoundEvents.ENTITY_ENDER_DRAGON_FLAP,
                    SoundCategory.PLAYERS,
                    0.5F,
                    1.2F
            );

            ((ServerWorld)world).spawnParticles(
                    ParticleTypes.CLOUD,
                    user.getX(), user.getY(), user.getZ(),
                    20,
                    0.3, 0.1, 0.3,
                    0.05
            );
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }




}