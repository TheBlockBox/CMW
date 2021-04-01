HAI 1.2
CAN I HAS net.minecraft.block.FlowerBlock?
CAN I HAS net.minecraft.entity.EntityType?
CAN I HAS net.minecraft.entity.LivingEntity?
CAN I HAS net.minecraft.entity.ai.goal.*?
CAN I HAS net.minecraft.entity.attribute.DefaultAttributeContainer?
CAN I HAS net.minecraft.entity.attribute.EntityAttributes?
CAN I HAS net.minecraft.entity.damage.DamageSource?
CAN I HAS net.minecraft.entity.mob.HostileEntity?
CAN I HAS net.minecraft.entity.mob.MobEntity?
CAN I HAS net.minecraft.entity.mob.ZombieEntity?
CAN I HAS net.minecraft.entity.passive.PassiveEntity?
CAN I HAS net.minecraft.entity.passive.TameableEntity?
CAN I HAS net.minecraft.entity.player.PlayerEntity?
CAN I HAS net.minecraft.item.BlockItem?
CAN I HAS net.minecraft.item.Item?
CAN I HAS net.minecraft.item.ItemStack?
CAN I HAS net.minecraft.server.world.ServerWorld?
CAN I HAS net.minecraft.util.ActionResult?
CAN I HAS net.minecraft.util.Hand?
CAN I HAS net.minecraft.world.World?
CAN I HAS java.util.UUID?

I HAS A baseMob

HOW IZ I new YR entityType AN YR world AN YR base
I IZ super YR entityType AN YR world MKAY
baseMob R base
IF U SAY SO

HOW IZ I new YR entityType AN YR world
I IZ _this YR entityType AN YR world AN YR ZombieEntity_class MKAY
IF U SAY SO

HOW IZ I new YR world AN YR base
I IZ _this YR CMW_CURSED_MOB_WAIFU AN YR world AN YR I IZ base_getClass MKAY MKAY
I IZ setOwnerMob YR base MKAY
I HAS A x ITZ I IZ base_getX MKAY
I HAS A y ITZ I IZ base_getY MKAY
I HAS A z ITZ I IZ base_getZ MKAY
I IZ updatePosition YR x AN YR y AN YR z MKAY
IF U SAY SO

HOW IZ I setOwnerMob YR owner
I IZ setTamed YR WIN MKAY
I IZ setOwnerUuid YR I IZ owner_getUuid MKAY MKAY
IF U SAY SO

HOW IZ I initGoals
I IZ goalSelector_add YR 1 AN YR I IZ SwimGoal_new YR _this MKAY MKAY
I IZ goalSelector_add YR 2 AN YR I IZ SitGoal_new YR _this MKAY MKAY
I IZ goalSelector_add YR 4 AN YR I IZ PounceAtTargetGoal_new YR _this AN YR 0.4 MKAY MKAY
I IZ goalSelector_add YR 5 AN YR I IZ MeleeAttackGoal_new YR _this AN YR 1.0 AN YR WIN MKAY MKAY
I IZ goalSelector_add YR 6 AN YR I IZ FollowOwnerGoal_new YR _this AN YR 1.0 AN YR 10.0 AN YR 2.0 AN YR FAIL MKAY MKAY
I IZ goalSelector_add YR 8 AN YR I IZ WanderAroundFarGoal_new YR _this AN YR 1.0 MKAY MKAY
I IZ goalSelector_add YR 10 AN YR I IZ LookAtEntityGoal_new YR _this AN YR PlayerEntity_class AN YR 8.0 MKAY MKAY
I IZ goalSelector_add YR 10 AN YR I IZ LookAroundGoal_new YR _this MKAY MKAY
I IZ targetSelector_add YR 1 AN YR I IZ TrackOwnerAttackerGoal_new YR _this MKAY MKAY
I IZ targetSelector_add YR 2 AN YR I IZ AttackWithOwnerGoal_new YR _this MKAY MKAY
IF U SAY SO

HOW IZ I createCursedMobWaifuAttributes
I HAS A attribute ITZ I IZ HostileEntity_createHostileAttributes MKAY
FOUND YR I IZ attribute_add YR EntityAttributes_GENERIC_MOVEMENT_SPEED AN YR 0.25 MKAY
IF U SAY SO

HOW IZ I isTameableWith YR item
BTW Test if flower
BTW Sorry modders who don't extend that class for flowers
I HAS A block ITZ I IZ item_getBlock MKAY
FOUND YR BOTH OF I IZ item_instanceof YR BlockItem MKAY AN I IZ block_instanceof YR FlowerBlock MKAY
IF U SAY SO

HOW IZ I getOwner
I HAS A ownerUUID ITZ I IZ getOwnerUuid MKAY
BOTH SAEM ownerUUID AN NOOB
O RLY?
	YA RLY
		FOUND YR NOOB
	NO WAI
		I HAS A player ITZ I IZ world_getPlayerByUuid YR ownerUUID MKAY
		BOTH OF BOTH SAEM player AN NOOB AN I IZ world_instanceof YR ServerWorld MKAY
		O RLY?
			YA RLY
				FOUND YR I IZ world_getEntity YR ownerUUID MKAY
			NO WAI
				FOUND YR player
		OIC
OIC
IF U SAY SO

HOW IZ I isTamed
I HAS A tamed ITZ I IZ super_isTamed MKAY
BOTH OF tamed AN BOTH SAEM I IZ getOwner MKAY AN NOOB
O RLY?
	YA RLY
		I IZ setTamed YR FAIL MKAY
		FOUND YR FAIL
	NO WAI
		FOUND YR tamed
OIC
IF U SAY SO

HOW IZ I damage YR source AN YR amount
I IZ isInvulnerablTo YR source MKAY
O RLY?
	YA RLY
		FOUND YR FAIL
	NO WAI
		I IZ setSitting YR FAIL MKAY
		FOUND YR I IZ super_damage YR source AN YR amount MKAY
OIC
IF U SAY SO

HOW IZ I interactMob YR player AN YR hand
I HAS A itemStack ITZ I IZ player_getStackInHand YR hand MKAY
I HAS A tamed ITZ I IZ isTamed MKAY
I HAS A item ITZ I IZ itemStack_getItem MKAY
I HAS A tameable ITZ I IZ isTameableWith YR item MKAY
I HAS A isOwner ITZ I IZ isOwner YR player MKAY
world_isClient
O RLY?
	YA RLY
		WON OF isOwner AN BOTH OF NOT tamed AN tameable
		O RLY?
			YA RLY
				FOUND YR ActionResult_CONSUME
			NO WAI
				FOUND YR ActionResult_PASS
		OIC
	NO WAI
		FOUND YR UNCALCULAYDEDRESALT BTW ICCB TU DU DIS
OIC
IF U SAY SO

HOW IZ I createChild YR world AN YR entity
FOUND YR NOOB
IF U SAY SO

HOW IZ I shouldRenderName
FOUND YR I IZ hasCustomName MKAY
IF U SAY SO

HOW IZ I isInSneakingPose
FOUND YR I IZ isInSittingPose MKAY
IF U SAY SO

HOW IZ I isSneaking
FOUND YR I IZ isSitting MKAY
IF U SAY SO

HOW IZ I setSneaking YR sneaking
I IZ setSitting YR sneaking
IF U SAY SO

KTHXBYE