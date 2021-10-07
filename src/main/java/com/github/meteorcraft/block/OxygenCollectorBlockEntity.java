package com.github.meteorcraft.block;

import com.github.meteorcraft.MeteorBlockEntityTypes;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class OxygenCollectorBlockEntity extends BlockEntity {

    public final long CAPACITY = 50000;
    public final long MAX_INSERT = 100;
    public final long MAX_EXTRACT = 100;

    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(CAPACITY, MAX_INSERT, MAX_EXTRACT) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }

        @Override
        public long insert(long maxAmount, TransactionContext transaction) {
            long ins =  super.insert(maxAmount, transaction);
            if (amount+ins >= 50000) {
                amount = 50000;
            } else {
                amount += ins;
            }
            System.out.println("Inserting : "+ins+", Amount : "+amount);
            return ins;
        }
    };

    public OxygenCollectorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MeteorBlockEntityTypes.OXYGEN_COLLECTOR, blockPos, blockState);
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> energyStorage, MeteorBlockEntityTypes.OXYGEN_COLLECTOR);
    }

    public void remove() {
    }




}
