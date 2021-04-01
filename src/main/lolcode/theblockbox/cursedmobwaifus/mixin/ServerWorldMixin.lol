HAI 1.2
CAN I HAS net.minecraft.entity.Entity?
CAN I HAS net.minecraft.entity.mob.HostileEntity?
CAN I HAS net.minecraft.entity.mob.MobEntity?
CAN I HAS net.minecraft.server.world.ServerWorld?
CAN I HAS org.spongepowered.asm.mixin.injection.At?
CAN I HAS org.spongepowered.asm.mixin.injection.Inject?
CAN I HAS org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable?
CAN I HAS theblockbox.cursedmobwaifus.Config?
CAN I HAS theblockbox.cursedmobwaifus.CursedMobWaifuEntity?

I IZ Mixin YR ServerWorld_class MKAY

HOW IZ I spawnEntity YR entity AN YR cir
I HAS A random ITZ I IZ entity_getRandom MKAY
I HAS A randomInt ITZ I IZ random_nextInt YR 100 MKAY
BOTH OF entity_instanceof HostileEntity AN I IZ DIFFRINT randomInt AN BIGGR OF randomInt AN Config_instance_percentChanceOfWaifu MKAY
O RLY?
	YA RLY
		I HAS A world ITZ _this
		I HAS A waifu ITZ I IZ CursedMobWaifuEntity_new YR world AN YR entity MKAY
		I IZ world_spawnEntity YR waifu MKAY
OIC
IF U SAY SO
KTHXBYE