package me.squander.backpack.menu;

import me.squander.backpack.handlers.IItemHandlerExtended;
import me.squander.backpack.handlers.ItemStackHandlerExtended;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExtraStorageInventory extends ItemStackHandlerExtended implements ICapabilityProvider {
    public static final Capability<IItemHandlerExtended> EXTRA_STORAGE = CapabilityManager.get(new CapabilityToken<>(){});
    private final LazyOptional<IItemHandlerExtended> optional = LazyOptional.of(() -> this);
    public static final TranslatableComponent NAME_OF_CONTAINER = new TranslatableComponent("container.backpackmod.extra_storage");
    public static final int SIZE_OF_CONTAINER = 9;

    public ExtraStorageInventory(int size){
        super(size);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == EXTRA_STORAGE ? optional.cast() : LazyOptional.empty();
    }
}
