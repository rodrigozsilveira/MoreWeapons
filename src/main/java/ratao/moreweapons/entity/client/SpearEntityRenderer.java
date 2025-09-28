package ratao.moreweapons.entity.client;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.TridentEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import ratao.moreweapons.entity.weapons.SpearEntity;
import ratao.moreweapons.item.ModItems;

public class SpearEntityRenderer extends EntityRenderer<SpearEntity> {

    public SpearEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(SpearEntity entity) {
        if (entity.asItemStack().isOf(ModItems.NETHERITE_SPEAR)) {
            return Identifier.of("moreweapons", "textures/entity/netherite_spear.png");
        } else if (entity.asItemStack().isOf(ModItems.IRON_SPEAR)) {
            return Identifier.of("moreweapons", "textures/entity/iron_spear.png");
        } else {
            return Identifier.of("moreweapons", "textures/entity/wooden_spear.png");
        }
    }
}
