package me.squander.backpack.menu;

import me.squander.backpack.handlers.IItemHandlerExtended;
import me.squander.backpack.handlers.ItemStackHandlerExtended;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ExtraStorageMenu extends AbstractContainerMenu {
    public ExtraStorageMenu(int pContainerId, Inventory inventory, FriendlyByteBuf buffer) {
        this(pContainerId, inventory, new ItemStackHandlerExtended(buffer.readVarInt()));
    }

    public ExtraStorageMenu(int pContainerId, Inventory inventory, IItemHandlerExtended handler) {
        super(MenuTypeInit.EXTRA_STORAGE.get(), pContainerId);
        int i = (1 - 4) * 18;

        for(int x = 0; x < 9; ++x) {
            this.addSlot(new SlotItemHandler(handler, x, 8 + x * 18, 18));
        }

        this.addPlayerInventory(inventory, i);
        this.addPlayerHotbar(inventory, i);
    }

    private void addPlayerInventory(Inventory inventory, int i){
        for(int y = 0; y < 3; ++y) {
            for(int x = 0; x < 9; ++x) {
                this.addSlot(new Slot(inventory, x + y * 9 + 9, 8 + x * 18, 103 + y * 18 + i));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory, int i){
        for(int x = 0; x < 9; ++x) {
            this.addSlot(new Slot(inventory, x, 8 + x * 18, 161 + i));
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
