package com.elfmcys.yesstevemodel.client.compat.touhoulittlemaid.event;

import com.elfmcys.yesstevemodel.client.compat.touhoulittlemaid.capability.MaidCapabilityProvider;
import com.github.tartaricacid.touhoulittlemaid.compat.ysm.event.YsmMaidClientTickEvent;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

import net.minecraftforge.eventbus.api.SubscribeEvent;


public class MaidClientTickEvent {
    @SubscribeEvent
    public void onMaidClientTick(YsmMaidClientTickEvent event) {
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        if (localPlayer == null) {
            return;
        }
        EntityMaid maid = event.getMaid();
        if (localPlayer.getUUID().equals(maid.getOwnerUUID())) {
            tickMaidModel(maid);
        }
    }

    private void tickMaidModel(EntityMaid entityMaid) {
        entityMaid.getCapability(MaidCapabilityProvider.MAID_CAP).ifPresent(cap -> {
        });
    }
}