package com.vm.examplemod;
import net.minecraft.client.renderer.entity.BatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class PhoenixRenderer extends MobRenderer<PhoenixEntity> {

    public PhoenixRenderer(EntityRendererManager p_i50961_1_, EntityModel p_i50961_2_, float p_i50961_3_) {
        super(p_i50961_1_, p_i50961_2_, p_i50961_3_);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }
}
