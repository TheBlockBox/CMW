package theblockbox.cursedmobwaifus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public final class CMWClient implements ClientModInitializer {
    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(CMW.CURSED_MOB_WAIFU, (dispatcher, context) -> {
            return new CursedMobWaifuRenderer(dispatcher);
        });
    }
}
