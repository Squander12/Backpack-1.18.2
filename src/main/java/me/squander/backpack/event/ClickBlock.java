package me.squander.backpack.event;

import me.squander.backpack.BackpackMod;
import me.squander.backpack.menu.ExtraStorageInventory;
import me.squander.backpack.menu.ExtraStorageMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;

@Mod.EventBusSubscriber(modid = BackpackMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClickBlock {

    @SubscribeEvent
    public static void onClickBlock(PlayerInteractEvent.RightClickBlock event){
        Level level = event.getWorld();
        BlockPos pos = event.getPos();
        Player player = event.getPlayer();
        if(!level.isClientSide()){
            if(level.getBlockState(pos).getBlock() == Blocks.SAND){
                player.getCapability(ExtraStorageInventory.EXTRA_STORAGE).ifPresent(handler -> {
                    NetworkHooks.openGui((ServerPlayer) player, new SimpleMenuProvider((id, inv, plr) ->
                            new ExtraStorageMenu(id, inv, handler), new TextComponent("EXTRA STORAGE")), buffer -> buffer.writeVarInt(9));
                });
            }
        }
    }
}
