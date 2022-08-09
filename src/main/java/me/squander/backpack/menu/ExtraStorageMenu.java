package me.squander.backpack.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ExtraStorageMenu extends AbstractContainerMenu {
    public ExtraStorageMenu(int pContainerId, Inventory inventory, FriendlyByteBuf buffer) {
        this(pContainerId, inventory, new ItemStackHandler(buffer.readVarInt()));
    }

    public ExtraStorageMenu(int pContainerId, Inventory inventory, IItemHandler handler) {
        super(MenuTypeInit.EXTRA_STORAGE.get(), pContainerId);

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new SlotItemHandler(handler, i, 1 + i * 18, 1));
        }

        this.addPlayerInventory(inventory);
        this.addPlayerHotbar(inventory);
    }

    private void addPlayerInventory(Inventory inventory){
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory){
        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }
}
