package me.squander.backpack.items;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BackpackInventory extends ItemStackHandler implements ICapabilityProvider {
    private final LazyOptional<IItemHandler> lazyOptional = LazyOptional.of(() -> this);
    protected ItemStack container;

    public BackpackInventory(ItemStack container, int size){
        this.container = container;
        this.setSize(size);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? lazyOptional.cast() : LazyOptional.empty();
    }
}
