package com.github.meteorcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Pair;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;

import java.util.HashMap;

public class OxygenCollectorBlock extends Block implements BlockEntityProvider {

    private static HashMap<BlockPos, OxygenCollectorBlockEntity> entities = new HashMap();

    public OxygenCollectorBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public OxygenCollectorBlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        Pair<String, BlockState> pair = new Pair<>(pos.toShortString(), state);
        if (entities.containsKey(pair)) {
            return entities.get(pair);
        } else {
            var entity = new OxygenCollectorBlockEntity(pos, state);
            entities.put(pos, entity);
            return entity;
        }
    }

    public void removeBlockEntity(BlockPos pos, BlockState state) {

    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        createBlockEntity(pos, state);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        removeBlockEntity(pos, state);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        removeBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        EnergyStorage storage = EnergyStorage.SIDED.find(world, pos, player.getMovementDirection());
        player.sendMessage(new LiteralText(String.valueOf(storage.getAmount())), false);
        return ActionResult.PASS;
    }
}
