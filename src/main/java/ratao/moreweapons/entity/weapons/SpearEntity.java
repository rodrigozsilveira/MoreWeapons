package ratao.moreweapons.entity.weapons;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ratao.moreweapons.item.ModItems;

public class SpearEntity extends TridentEntity {

    public SpearEntity(EntityType<? extends TridentEntity> entityType, World world) {
        super(entityType, world);
    }

    public SpearEntity(World world, LivingEntity owner, ItemStack stack) {
        super(world, owner, stack);
    }

    @Override
    public ItemStack asItemStack() {
        return new ItemStack(ModItems.NETHERITE_SPEAR);
    }
}