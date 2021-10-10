package com.github.meteorcraft.mixin;

import com.github.meteorcraft.MeteorWorlds;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract boolean canMoveVoluntarily();

    @Shadow
    public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow
    protected abstract boolean shouldSwimInFluids();

    @Shadow
    public abstract boolean canWalkOnFluid(Fluid fluid);

    @Shadow
    protected abstract float getBaseMovementSpeedMultiplier();

    @Shadow
    public abstract float getMovementSpeed();

    @Shadow
    public abstract boolean isClimbing();

    @Shadow
    public abstract Vec3d method_26317(double d, boolean bl, Vec3d vec3d);

    @Shadow
    public abstract boolean isFallFlying();

    @Shadow
    protected abstract SoundEvent getFallSound(int distance);

    @Shadow
    public abstract Vec3d method_26318(Vec3d vec3d, float f);

    @Shadow
    @Nullable
    public abstract StatusEffectInstance getStatusEffect(StatusEffect effect);

    @Shadow
    public abstract boolean hasNoDrag();

    @Shadow
    public abstract void updateLimbs(LivingEntity entity, boolean flutter);

    /**
     * @author WinCho
     * @reason Jump higher on the moon.
     */
    @Overwrite
    public float getJumpVelocity() {
        return 0.42F * this.getJumpVelocityMultiplier() + (MeteorWorlds.isMoon(world) ? 0.3f : 0);
    }

    /**
     * @author WinCho
     * @reason It falls more slowly from the moon.
     */
    @Overwrite
    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() || this.isLogicalSideForUpdatingMovement()) {
            double d = 0.08D;
            boolean bl = this.getVelocity().y <= 0.0D;
            if (bl && this.hasStatusEffect(StatusEffects.SLOW_FALLING)) {
                d = 0.01D;
                this.fallDistance = 0.0F;
            }
            if (bl && MeteorWorlds.isMoon(world)) {
                d = 0.03;
                this.fallDistance = 0.0F;
            }

            FluidState fluidState = this.world.getFluidState(this.getBlockPos());
            float j;
            double e;
            if (this.isTouchingWater() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState.getFluid())) {
                e = this.getY();
                j = this.isSprinting() ? 0.9F : this.getBaseMovementSpeedMultiplier();
                float g = 0.02F;
                float h = (float) EnchantmentHelper.getDepthStrider((LivingEntity) (Entity) this);
                if (h > 3.0F) {
                    h = 3.0F;
                }

                if (!this.onGround) {
                    h *= 0.5F;
                }

                if (h > 0.0F) {
                    j += (0.54600006F - j) * h / 3.0F;
                    g += (this.getMovementSpeed() - g) * h / 3.0F;
                }

                if (this.hasStatusEffect(StatusEffects.DOLPHINS_GRACE)) {
                    j = 0.96F;
                }

                this.updateVelocity(g, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                Vec3d vec3d = this.getVelocity();
                if (this.horizontalCollision && this.isClimbing()) {
                    vec3d = new Vec3d(vec3d.x, 0.2D, vec3d.z);
                }

                this.setVelocity(vec3d.multiply((double) j, 0.800000011920929D, (double) j));
                Vec3d vec3d2 = this.method_26317(d, bl, this.getVelocity());
                this.setVelocity(vec3d2);
                if (this.horizontalCollision && this.doesNotCollide(vec3d2.x, vec3d2.y + 0.6000000238418579D - this.getY() + e, vec3d2.z)) {
                    this.setVelocity(vec3d2.x, 0.30000001192092896D, vec3d2.z);
                }
            } else if (this.isInLava() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState.getFluid())) {
                e = this.getY();
                this.updateVelocity(0.02F, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                Vec3d vec3d4;
                if (this.getFluidHeight(FluidTags.LAVA) <= this.getSwimHeight()) {
                    this.setVelocity(this.getVelocity().multiply(0.5D, 0.800000011920929D, 0.5D));
                    vec3d4 = this.method_26317(d, bl, this.getVelocity());
                    this.setVelocity(vec3d4);
                } else {
                    this.setVelocity(this.getVelocity().multiply(0.5D));
                }

                if (!this.hasNoGravity()) {
                    this.setVelocity(this.getVelocity().add(0.0D, -d / 4.0D, 0.0D));
                }

                vec3d4 = this.getVelocity();
                if (this.horizontalCollision && this.doesNotCollide(vec3d4.x, vec3d4.y + 0.6000000238418579D - this.getY() + e, vec3d4.z)) {
                    this.setVelocity(vec3d4.x, 0.30000001192092896D, vec3d4.z);
                }
            } else if (this.isFallFlying()) {
                Vec3d vec3d5 = this.getVelocity();
                if (vec3d5.y > -0.5D) {
                    this.fallDistance = 1.0F;
                }

                Vec3d vec3d6 = this.getRotationVector();
                j = this.getPitch() * 0.017453292F;
                double k = Math.sqrt(vec3d6.x * vec3d6.x + vec3d6.z * vec3d6.z);
                double l = vec3d5.horizontalLength();
                double m = vec3d6.length();
                float n = MathHelper.cos(j);
                n = (float) ((double) n * (double) n * Math.min(1.0D, m / 0.4D));
                vec3d5 = this.getVelocity().add(0.0D, d * (-1.0D + (double) n * 0.75D), 0.0D);
                double q;
                if (vec3d5.y < 0.0D && k > 0.0D) {
                    q = vec3d5.y * -0.1D * (double) n;
                    vec3d5 = vec3d5.add(vec3d6.x * q / k, q, vec3d6.z * q / k);
                }

                if (j < 0.0F && k > 0.0D) {
                    q = l * (double) (-MathHelper.sin(j)) * 0.04D;
                    vec3d5 = vec3d5.add(-vec3d6.x * q / k, q * 3.2D, -vec3d6.z * q / k);
                }

                if (k > 0.0D) {
                    vec3d5 = vec3d5.add((vec3d6.x / k * l - vec3d5.x) * 0.1D, 0.0D, (vec3d6.z / k * l - vec3d5.z) * 0.1D);
                }

                this.setVelocity(vec3d5.multiply(0.9900000095367432D, 0.9800000190734863D, 0.9900000095367432D));
                this.move(MovementType.SELF, this.getVelocity());
                if (this.horizontalCollision && !this.world.isClient) {
                    q = this.getVelocity().horizontalLength();
                    double r = l - q;
                    float s = (float) (r * 10.0D - 3.0D);
                    if (s > 0.0F) {
                        this.playSound(this.getFallSound((int) s), 1.0F, 1.0F);
                        this.damage(DamageSource.FLY_INTO_WALL, s);
                    }
                }

                if (this.onGround && !this.world.isClient) {
                    this.setFlag(7, false);
                }
            } else {
                BlockPos blockPos = this.getVelocityAffectingPos();
                float t = this.world.getBlockState(blockPos).getBlock().getSlipperiness();
                j = this.onGround ? t * 0.91F : 0.91F;
                Vec3d vec3d7 = this.method_26318(movementInput, t);
                double v = vec3d7.y;
                if (this.hasStatusEffect(StatusEffects.LEVITATION)) {
                    v += (0.05D * (double) (this.getStatusEffect(StatusEffects.LEVITATION).getAmplifier() + 1) - vec3d7.y) * 0.2D;
                    this.fallDistance = 0.0F;
                } else if (this.world.isClient && !this.world.isChunkLoaded(blockPos)) {
                    if (this.getY() > (double) this.world.getBottomY()) {
                        v = -0.1D;
                    } else {
                        v = 0.0D;
                    }
                } else if (!this.hasNoGravity()) {
                    v -= d;
                }

                if (this.hasNoDrag()) {
                    this.setVelocity(vec3d7.x, v, vec3d7.z);
                } else {
                    this.setVelocity(vec3d7.x * (double) j, v * 0.9800000190734863D, vec3d7.z * (double) j);
                }
            }
        }

        this.updateLimbs((LivingEntity) (Entity) this, this instanceof Flutterer);
    }
}
