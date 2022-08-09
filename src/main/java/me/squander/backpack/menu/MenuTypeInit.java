package me.squander.backpack.menu;

import me.squander.backpack.BackpackMod;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypeInit {
    public static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(ForgeRegistries.CONTAINERS, BackpackMod.MODID);

    public static RegistryObject<MenuType<BackpackMenu>> BACKPACK = MENU.register("backpack", () ->
            IForgeMenuType.create(BackpackMenu::new));

    public static RegistryObject<MenuType<ExtraStorageMenu>> EXTRA_STORAGE = MENU.register("extra_storage", () ->
            IForgeMenuType.create(ExtraStorageMenu::new));
}
