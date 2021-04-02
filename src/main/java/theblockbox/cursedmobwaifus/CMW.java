package theblockbox.cursedmobwaifus;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class CMW implements ModInitializer {
    public static final String MODID = "cursedmobwaifus";
    public static final EntityType<CursedMobWaifuEntity> CURSED_MOB_WAIFU = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MODID, "cursedmobwaifu"),
            FabricEntityTypeBuilder.<CursedMobWaifuEntity>create(SpawnGroup.CREATURE, CursedMobWaifuEntity::new)
                    .dimensions(EntityDimensions.changing(0.6f, 1.8f)).build()
    );

    @Override
    public void onInitialize() {
        Config.init();
        FabricDefaultAttributeRegistry.register(CURSED_MOB_WAIFU, CursedMobWaifuEntity.createCursedMobWaifuAttributes());
    }
}