package theblockbox.cursedmobwaifus;

import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.UUID;

public class CursedMobWaifuEntity extends TameableEntity {
    public static final TrackedData<Integer> TYPE = DataTracker.registerData(CursedMobWaifuEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public CursedMobWaifuEntity(EntityType<? extends CursedMobWaifuEntity> entityType, World world) {
        super(entityType, world);
        this.pitch += 1.0F;
    }

    public CursedMobWaifuEntity(World world, MobEntity base) {
        this(CMW.CURSED_MOB_WAIFU, world);
        this.setOwnerMob(base);
        this.updatePosition(base.getX(), base.getY(), base.getZ());
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(CursedMobWaifuEntity.TYPE, -1);
    }

    public EntityType<?> getOwnerType() {
        return Registry.ENTITY_TYPE.get(dataTracker.get(TYPE));
    }

    private void setOwnerType(EntityType<?> type) {
        this.dataTracker.set(TYPE, Registry.ENTITY_TYPE.getRawId(type));
    }

    public void setOwnerMob(MobEntity owner) {
        // TODO: Change
        this.setOwnerType(owner.getType());
        this.setTamed(true);
        this.setOwnerUuid(owner.getUuid());
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }

    @Override
    protected SoundEvent getFallSound(int distance) {
        return (distance > 4) ? SoundEvents.ENTITY_PLAYER_BIG_FALL : SoundEvents.ENTITY_PLAYER_SMALL_FALL;
    }

    @Override
    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_PLAYER_SPLASH;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_PLAYER_SWIM;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        if (source == DamageSource.ON_FIRE) {
            return SoundEvents.ENTITY_PLAYER_HURT_ON_FIRE;
        } else if (source == DamageSource.DROWN) {
            return SoundEvents.ENTITY_PLAYER_HURT_DROWN;
        } else {
            return source == DamageSource.SWEET_BERRY_BUSH ? SoundEvents.ENTITY_PLAYER_HURT_SWEET_BERRY_BUSH : SoundEvents.ENTITY_PLAYER_HURT;
        }
    }

    @Override
    public SoundEvent getEatSound(ItemStack stack) {
        return SoundEvents.ENTITY_PLAYER_BURP;
    }

    @Override
    protected SoundEvent getHighSpeedSplashSound() {
        return SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(4, new PounceAtTargetGoal(this, 0.4F));
        this.goalSelector.add(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.add(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(10, new LookAroundGoal(this));
        this.targetSelector.add(1, new TrackOwnerAttackerGoal(this));
        this.targetSelector.add(2, new AttackWithOwnerGoal(this));
    }

    public static DefaultAttributeContainer.Builder createCursedMobWaifuAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
    }

    public boolean isTameableWith(Item item) {
        // Test if flower
        // Sorry modders who don't extend that class for flowers
        return (item instanceof BlockItem) && (((BlockItem) item).getBlock() instanceof FlowerBlock);
    }

    @Override
    public LivingEntity getOwner() {
        UUID ownerUUID = getOwnerUuid();
        if (ownerUUID == null) {
            return null;
        } else {
            PlayerEntity player = this.world.getPlayerByUuid(ownerUUID);
            if ((player == null) && (this.world instanceof ServerWorld)) {
                return (LivingEntity) ((ServerWorld) this.world).getEntity(ownerUUID);
            } else {
                return player;
            }
        }
    }

    @Override
    public boolean isTamed() {
        boolean tamed = super.isTamed();
        if (tamed && (this.getOwner() == null)) {
            // If the owner doesn't exist, remove it
            this.setTamed(false);
            return false;
        } else {
            return tamed;
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            this.setSitting(false);
            return super.damage(source, amount);
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        boolean tamed = this.isTamed();
        boolean tameable = isTameableWith(itemStack.getItem());
        boolean isOwner = this.isOwner(player);
        if (this.world.isClient) {
            return (isOwner || (!tamed && tameable)) ? ActionResult.CONSUME : ActionResult.PASS;
        } else if (isOwner) {
            ActionResult result = super.interactMob(player, hand);
            if (!result.isAccepted()) {
                // Sitting
                this.setSitting(!this.isSitting());
                this.jumping = false;
                this.navigation.stop();
                this.setTarget(null);
                return ActionResult.SUCCESS;
            } else {
                return result;
            }
        } else if (tameable) {
            // Remove item from stack
            if (!player.abilities.creativeMode)
                itemStack.decrement(1);

            if (!tamed) {
                // 33% chance of taming
                if (this.random.nextInt(3) == 0) {
                    // Marriage
                    this.setOwner(player);
                    this.navigation.stop();
                    this.setSitting(true);
                    this.setTarget(null);
                    this.world.sendEntityStatus(this, (byte) 7);
                } else {
                    // Rejection
                    this.world.sendEntityStatus(this, (byte) 6);
                }
                return ActionResult.SUCCESS;
            } else if (this.getHealth() < this.getMaxHealth()) {
                // If tamed, only perform healing (one heart)
                this.heal(2);
                return ActionResult.SUCCESS;
            }
        }
        return super.interactMob(player, hand);
    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public boolean shouldRenderName() {
        return this.hasCustomName();
    }

    @Override
    public boolean isInSneakingPose() {
        return this.isInSittingPose();
    }

    @Override
    public boolean isSneaking() {
        return this.isSitting();
    }

    @Override
    public void setSneaking(boolean sneaking) {
        this.setSitting(sneaking);
    }

    @Override
    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        super.writeCustomDataToTag(tag);
        tag.putString("CMWOwnerType", Registry.ENTITY_TYPE.getId(this.getOwnerType()).toString());
    }

    @Override
    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        if(tag.contains("CMWOwnerType", 8)) {
            this.setOwnerType(Registry.ENTITY_TYPE.get(new Identifier(tag.getString("CMWOwnerType"))));
        }
    }
}
