package dev.mrsterner.morium.mixin;

import dev.mrsterner.morium.common.registry.MoriumObjects;
import dev.mrsterner.morium.common.registry.MoriumTags;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    private ServerPlayerEntity interactPlayer;
    private BlockState interactBlock;

    @Inject(method = "interactBlock", at = @At("HEAD"))
    private void getParameters(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        interactPlayer = player;
        interactBlock = world.getBlockState(hitResult.getBlockPos());
    }

    @ModifyVariable(method = "interactBlock", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/server/network/ServerPlayerEntity;shouldCancelInteraction()Z"))
    private boolean allowSneakRightClick(boolean original) {
        System.out.println(MoriumTags.JAR.contains(interactPlayer.getMainHandStack().getItem()));
        if (interactPlayer.isSneaking() && (interactBlock.isOf(MoriumObjects.JAR) && MoriumTags.JAR.contains(interactPlayer.getMainHandStack().getItem()))) {
            System.out.println("allowd");
            return false;
        }
        return original;
    }
}
