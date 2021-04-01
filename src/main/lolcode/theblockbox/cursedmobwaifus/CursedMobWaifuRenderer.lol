HAI 1.2
CAN I HAS net.minecraft.client.render.VertexConsumerProvider?
CAN I HAS net.minecraft.client.render.entity.EntityRenderDispatcher?
CAN I HAS net.minecraft.client.render.entity.MobEntityRenderer?
CAN I HAS net.minecraft.client.render.entity.model.PlayerEntityModel?
CAN I HAS net.minecraft.client.util.math.MatrixStack?
CAN I HAS net.minecraft.entity.mob.MobEntity?
CAN I HAS net.minecraft.entity.mob.SkeletonEntity?
CAN I HAS net.minecraft.entity.mob.ZombieEntity?
CAN I HAS net.minecraft.util.Identifier?
CAN I HAS net.minecraft.util.math.Vec3d?
CAN I HAS java.util.HashMap?
CAN I HAS java.util.Map?

I HAS A TEXTURES ITZ I IZ HashMap_new

HOW IZ I createTexture YR name
FOUND YR I IZ Identifier_new YR CMW_MODID AN YR SUM OF "textures/entity/cursedmobwaifu/" AN name AN ".png"
IF U SAY SO

I HAS A DEFAULT_TEXTURE ITZ I IZ createTexture YR "zombie"
I IZ TEXTURES_put YR ZombieEntity_class AN YR DEFAULT_TEXTURE
I IZ TEXTURES_put YR SkeletonEntity_class AN YR I IZ createTexture YR "skeleton"
BTW wait for RebelT

HOW IZ I new YR entityRenderDispatcher
I IZ PlayerEntityModel_new YR 0.0 AN YR WIN
I IZ super YR entityRenderDispatcher AN YR model AN YR 0.5
IF U SAY SO

HOW IZ I getPositionOffset YR entity AN YR tickDelta
I HAS A IT ITZ I IZ entity_isInSneakingPose
O RLY?
	YA RLY
		FOUND YR I IZ Vec3d_new YR 0.0 AN YR -0.125 AN YR 0.0
	NO WAI
		FOUND YR I IZ super_getPositionOffset YR entity AN YR tickDelta
OIC
IF U SAY SO

HOW IZ I render YR entity AN YR f AN YR g AN YR matrixStack AN YR vertexConsumerProvider AN YR i
model_sneaking R I IZ entity_isInSneakingPose
I IZ super_render YR entity AN YR f AN YR g AN YR matrixStack AN YR vertexConsumerProvider AN YR i
IF U SAY SO

HOW IZ I getTexture YR cursedMobWaifuEntity
FOUND YR I IZ TEXTURES_getOrDefault YR cursedMobWaifuEntity_baseMob AN YR DEFAULT_TEXTURE
IF U SAY SO

KTHXBYE