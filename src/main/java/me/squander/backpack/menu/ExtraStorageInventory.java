package me.squander.backpack.menu;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExtraStorageInventory extends ItemStackHandler implements ICapabilityProvider {
    public static final Capability<IItemHandler> EXTRA_STORAGE = CapabilityManager.get(new CapabilityToken<>(){});
    private final LazyOptional<IItemHandler> optional = LazyOptional.of(() -> this);

    public ExtraStorageInventory(int size){
        this.setSize(size);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == EXTRA_STORAGE ? optional.cast() : LazyOptional.empty();
    }
}
