package com.github.creoii.survivality.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Wearable;
import net.minecraft.state.property.BooleanProperty;

public class SurvivalityUtils {
    public static final BooleanProperty SNOWY = BooleanProperty.of("snowy");

    public static void swapArmor(PlayerEntity player) {
        if (!player.shouldCancelInteraction()) {
            PlayerInventory inventory = player.getInventory();
            ItemStack selected = inventory.getStack(inventory.selectedSlot);
            if (selected.getItem() instanceof Wearable) {
                EquipmentSlot slot = MobEntity.getPreferredEquipmentSlot(selected);
                ItemStack ret = inventory.getArmorStack(slot.getEntitySlotId());
                inventory.main.set(inventory.selectedSlot, ret);
                inventory.armor.set(slot.getEntitySlotId(), selected);
                player.processEquippedStack(selected);
                player.onEquipStack(slot, selected, ret);
            }
        }
    }
}
