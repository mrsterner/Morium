package dev.mrsterner.alchrimea.mixin;


import dev.mrsterner.alchrimea.common.body.BodyParts;
import dev.mrsterner.alchrimea.common.registry.AlchrimeaComponents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(PlayerScreenHandler.class)
public class PlayerScreenHandlerMixin extends ScreenHandler {
    protected PlayerScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Shadow private PlayerEntity owner;

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
    @Inject(method = "canInsertIntoSlot(Lnet/minecraft/item/ItemStack;Lnet/minecraft/screen/slot/Slot;)Z", at = @At("RETURN"), cancellable = true)
    private void e(ItemStack stack, Slot slot, CallbackInfoReturnable<Boolean> cir){
        if(!AlchrimeaComponents.BODY_COMPONENT.get(owner).hasBodyPart(BodyParts.LEFTARM)) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }

    @Inject(method = "transferSlot", at = @At("HEAD"), cancellable = true)
    private void a(PlayerEntity player, int index, CallbackInfoReturnable<ItemStack> cir){
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(itemStack);

            if (equipmentSlot == EquipmentSlot.OFFHAND && !(this.slots.get(45)).hasStack() && AlchrimeaComponents.BODY_COMPONENT.get(player).hasBodyPart(BodyParts.LEFTARM)) {
                if (!this.insertItem(itemStack2, 45, 46, false)) {
                    cir.setReturnValue(ItemStack.EMPTY);
                }
            }
            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                cir.setReturnValue(ItemStack.EMPTY);
            }

            slot.onTakeItem(player, itemStack2);
            if (index == 0) {
                player.dropItem(itemStack2, false);
            }
        }
        cir.setReturnValue(itemStack);
    }
}
