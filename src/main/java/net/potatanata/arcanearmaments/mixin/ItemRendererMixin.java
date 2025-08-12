package net.potatanata.arcanearmaments.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.potatanata.arcanearmaments.common.item.ModItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static net.potatanata.arcanearmaments.ArcaneArmaments.MOD_ID;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow
    @Final
    private ItemModels models;

    @Shadow
    public abstract ItemModels getModels();

    @ModifyVariable(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(value = "HEAD"),
            argsOnly = true
    )
    public BakedModel renderItem(BakedModel bakedModel, @Local(argsOnly = true) ItemStack stack, @Local(argsOnly = true) ModelTransformationMode renderMode) {
        if (stack.getItem() == ModItems.SOUL_SCYTHE && (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED)) {
            return getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(MOD_ID, "soul_scythe")));
        }
        if (stack.getItem() == ModItems.SOUL_STAFF && (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED)) {
            return getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(MOD_ID, "soul_staff")));
        }
        if (stack.getItem() == ModItems.DREADED_CLEAVER && (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED)) {
            return getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(MOD_ID, "dreaded_cleaver")));
        }

        return bakedModel;
    }

    @ModifyVariable(
            method = "getModel",
            at = @At(value = "STORE"),
            ordinal = 1
    )
    public BakedModel getHeldItemModelMixin(BakedModel bakedModel, @Local(argsOnly = true) ItemStack stack) {
        if (stack.getItem() == ModItems.SOUL_SCYTHE) {
            return this.models.getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(MOD_ID, "soul_scythe_3d")));
        }
        if (stack.getItem() == ModItems.SOUL_STAFF) {
            return this.models.getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(MOD_ID, "soul_staff_3d")));
        }
        if (stack.getItem() == ModItems.DREADED_CLEAVER) {
            return this.models.getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(MOD_ID, "dreaded_cleaver_3d")));
        }


        return bakedModel;
    }
}