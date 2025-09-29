package ratao.moreweapons.item.weapons;

import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

import java.util.List;

public class ScytheItem extends SwordItem {

    private static final Identifier ATTACK_RANGE_MODIFIER_ID = Identifier.of("moreweapons", "attack_range");

    public ScytheItem(ToolMaterial material, Settings settings) {
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
                        new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                baseAttackDamage + material.getAttackDamage(),
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                // Attack Speed
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(
                                BASE_ATTACK_SPEED_MODIFIER_ID,
                                attackSpeed,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(
                                ATTACK_RANGE_MODIFIER_ID,
                                1.0, // +1 block range
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient) {
            // Increase sweep attack radius
            double radius = 1.7D; // Default = 1.0D
            List<LivingEntity> entities = attacker.getWorld()
                    .getEntitiesByClass(LivingEntity.class,
                            target.getBoundingBox().expand(radius),
                            e -> e != attacker && e.isAlive() && !attacker.isTeammate(e));

            for (LivingEntity e : entities) {
                e.damage(attacker.getDamageSources().playerAttack((PlayerEntity) attacker), 4.0F);
            }
        }
        return super.postHit(stack, target, attacker);
    }

}