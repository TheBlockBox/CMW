package theblockbox.cursedmobwaifus;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class CursedMobWaifuRenderer extends MobEntityRenderer<CursedMobWaifuEntity, PlayerEntityModel<CursedMobWaifuEntity>> {
    public static final Identifier DEFAULT = new Identifier(CMW.MODID, "textures/entity/cursedmobwaifu/default.png");
    public static final Lazy<Map<EntityType<?>, Identifier>> TEXTURES = new Lazy<>(() -> {
        Map<EntityType<?>, Identifier> map = new HashMap<>();
        String modid = CMW.MODID;
        addTexture(EntityType.ZOMBIE, modid, map);
        addTexture(EntityType.SKELETON, modid, map);
        addTexture(EntityType.ENDERMAN, modid, map);
        addTexture(EntityType.SPIDER, modid, map);
		addTexture(EntityType.CREEPER, modid, map);
        return map;
    });

    public static void addTexture(EntityType<?> type, String modid, Map<EntityType<?>, Identifier> map) {
        map.put(type, new Identifier(modid, "textures/entity/cursedmobwaifu/" + Registry.ENTITY_TYPE.getId(type).getPath() + ".png"));
    }

    public static void addTexture(EntityType<?> type) {
        Identifier typeIdentifier = Registry.ENTITY_TYPE.getId(type);
        TEXTURES.get().put(type, new Identifier(typeIdentifier.getNamespace(), "textures/entity/cursedmobwaifu/" + typeIdentifier.getPath() + ".png"));
    }

    public CursedMobWaifuRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new PlayerEntityModel<>(0.0f, true), 0.5f);
    }

    @Override
    public Vec3d getPositionOffset(CursedMobWaifuEntity entity, float tickDelta) {
        return entity.isInSneakingPose() ? new Vec3d(0.0D, -0.125D, 0.0D) : super.getPositionOffset(entity, tickDelta);
    }

    @Override
    public void render(CursedMobWaifuEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(this.model != null && entity != null) {
            this.model.sneaking = entity.isInSneakingPose();
            super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
        }
    }

    @Override
    public Identifier getTexture(CursedMobWaifuEntity entity) {
        return TEXTURES.get().getOrDefault(entity.getOwnerType(), DEFAULT);
    }
}
