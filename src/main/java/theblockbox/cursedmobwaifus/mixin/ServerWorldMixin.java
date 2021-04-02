package theblockbox.cursedmobwaifus.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import theblockbox.cursedmobwaifus.Config;
import theblockbox.cursedmobwaifus.CursedMobWaifuEntity;

@org.spongepowered.asm.mixin.Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Inject(method = "spawnEntity", at = @At("HEAD"))
    private void spawnEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if ((entity instanceof HostileEntity) && (((HostileEntity) entity).getRandom().nextInt(100) < Config.instance.percentChanceOfWaifu)) {
            ServerWorld world = (ServerWorld) (Object) this;
            CursedMobWaifuEntity waifu = new CursedMobWaifuEntity(world, (MobEntity) entity);
            world.spawnEntity(waifu);
        }
    }
}
