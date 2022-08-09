package me.squander.backpack.event;

import me.squander.backpack.BackpackMod;
import me.squander.backpack.handlers.IItemHandlerExtended;
import me.squander.backpack.menu.ExtraStorageInventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;

@Mod.EventBusSubscriber(modid = BackpackMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AttachCapabilities {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            event.addCapability(new ResourceLocation(BackpackMod.MODID, "extra_storage"), new ExtraStorageInventory(ExtraStorageInventory.SIZE_OF_CONTAINER));
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(IItemHandlerExtended.class);
    }
}
