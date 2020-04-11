package com.vm.examplemod;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.horse.ZombieHorseEntity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class PhoenixEntity extends AnimalEntity {

    public static final EntityType<PhoenixEntity> PHOENIX = Registry.register(Registry.ENTITY_TYPE, "phoenix", EntityType.Builder.create(PhoenixEntity::new, EntityClassification.CREATURE).size(1.2f, 1.2f).build("phoenix"));

    public PhoenixEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return new PhoenixEntity(PHOENIX, this.world);
    }
    
}
