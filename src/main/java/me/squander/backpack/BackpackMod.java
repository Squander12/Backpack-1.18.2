package me.squander.backpack;

import me.squander.backpack.items.ItemInit;
import me.squander.backpack.menu.BackpackScreen;
import me.squander.backpack.menu.ExtraStorageMenu;
import me.squander.backpack.menu.ExtraStorageScreen;
import me.squander.backpack.menu.MenuTypeInit;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BackpackMod.MODID)
public class BackpackMod
{
    public static final String MODID = "backpackmod";

    public BackpackMod()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemInit.ITEMS.register(bus);
        MenuTypeInit.MENU.register(bus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() ->{
            MenuScreens.register(MenuTypeInit.BACKPACK.get(), BackpackScreen::new);
            MenuScreens.register(MenuTypeInit.EXTRA_STORAGE.get(), ExtraStorageScreen::new);
        });
    }
}
