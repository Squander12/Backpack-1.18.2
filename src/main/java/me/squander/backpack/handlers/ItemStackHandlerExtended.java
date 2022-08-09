package me.squander.backpack.handlers;

import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerExtended extends ItemStackHandler implements IItemHandlerExtended {

    public ItemStackHandlerExtended(int size){
        this.setSize(size);
    }
}
