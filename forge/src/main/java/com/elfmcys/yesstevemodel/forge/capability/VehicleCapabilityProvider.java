package com.elfmcys.yesstevemodel.forge.capability;
import com.elfmcys.yesstevemodel.capability.VehicleCapability;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class VehicleCapabilityProvider implements ICapabilityProvider {

    public static Capability<VehicleCapability> VEHICLE_CAP = CapabilityManager.get(new CapabilityToken<VehicleCapability>() {
    });

    private VehicleCapability capability;

    private Entity entity;

    public VehicleCapabilityProvider(Entity entity) {
        this.entity = entity;
    }

    public VehicleCapability getOrCreateCapability() {
        if (this.capability == null) {
            this.capability = new VehicleCapability(this.entity);
            this.entity = null;
        }
        return this.capability;
    }

    @NotNull
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        return VEHICLE_CAP.orEmpty(capability, this.capability == null ? LazyOptional.empty() : LazyOptional.of(() -> {
            return this.capability;
        }));
    }
}
