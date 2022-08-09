package me.squander.backpack.items;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class EnderBackpackItem extends Item {
    public static final TranslatableComponent NAME_OF_BACKPACK = new TranslatableComponent("container.backpackmod.ender_backpack");

    public EnderBackpackItem() {
        super(ItemInit.prop());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide()){
            if(hand == InteractionHand.MAIN_HAND){
                PlayerEnderChestContainer enderContainer = player.getEnderChestInventory();
                NetworkHooks.openGui((ServerPlayer) player, this.createMenu(enderContainer));
            }
        }
        return InteractionResultHolder.sidedSuccess(player.getMainHandItem(), level.isClientSide());
    }

    private MenuProvider createMenu(PlayerEnderChestContainer container){
        return new SimpleMenuProvider((id, inv, player) -> ChestMenu.threeRows(id, inv, container), NAME_OF_BACKPACK);
    }
}
