package me.squander.backpack.items;

import me.squander.backpack.BackpackMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BackpackMod.MODID);
    public static RegistryObject<Item> BACKPACK_SMALL = ITEMS.register("backpack_small", () -> new BackpackItem(BackpackType.SMALL));
    public static RegistryObject<Item> BACKPACK_MEDIUM = ITEMS.register("backpack_medium", () -> new BackpackItem(BackpackType.MEDIUM));
    public static RegistryObject<Item> BACKPACK_LARGE = ITEMS.register("backpack_large", () -> new BackpackItem(BackpackType.LARGE));
    public static RegistryObject<Item> BACKPACK_ENDER = ITEMS.register("ender_backpack", EnderBackpackItem::new);

    public static Item.Properties prop(){
        return new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1);
    }
}
