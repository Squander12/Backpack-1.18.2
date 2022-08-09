package me.squander.backpack.menu;

import me.squander.backpack.items.BackpackType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class BackpackMenu extends AbstractContainerMenu {
    private final ItemStack backpack;
    private final BackpackType type;

    public BackpackMenu(int pContainerId, Inventory inventory, FriendlyByteBuf buffer) {
        this(pContainerId, inventory, buffer.readItem(), buffer.readEnum(BackpackType.class));
    }

    public BackpackMenu(int pContainerId, Inventory inventory, ItemStack backpack, BackpackType type) {
        super(MenuTypeInit.BACKPACK.get(), pContainerId);
        this.backpack = backpack;
        this.type = type;

        this.backpack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            for(int i = 0; i < type.getSizeY(); ++i) {
                for(int j = 0; j < type.getSizeX(); ++j) {
                    this.addSlot(new SlotItemHandler(handler, j + i * type.getSizeX(),
                            type.getSlotX() + j * 18, type.getSlotY() + i * 18));
                }
            }
        });

        this.addPlayerInventory(inventory);
        this.addPlayerHotbar(inventory);
    }

    public BackpackType getBackpackType(){
        return this.type;
    }

    private void addPlayerInventory(Inventory inventory){
        int a = this.type == BackpackType.LARGE ? 123 : 84;
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, a + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory){
        int a = this.type == BackpackType.LARGE ? 181 : 142;
        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, a));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return player.getMainHandItem() == this.backpack;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int index) {
        ItemStack toReturn = ItemStack.EMPTY;
        Slot slot = this.getSlot(index);
        if(slot.hasItem()){
            ItemStack stack = slot.getItem();
            toReturn = stack.copy();
            if(index < type.getSizeOfBackpack()){
                if(!this.moveItemStackTo(stack, type.getSizeOfBackpack(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }else if(!this.moveItemStackTo(stack, 0, type.getSizeOfBackpack(), false)){
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return toReturn;
    }
}
