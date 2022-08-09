package me.squander.backpack.items;

import me.squander.backpack.menu.BackpackMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class BackpackItem extends Item {
    private final BackpackType type;

    public BackpackItem(BackpackType typeOfBackpack) {
        super(ItemInit.prop());
        this.type = typeOfBackpack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getMainHandItem();
        if(!level.isClientSide()){
            NetworkHooks.openGui((ServerPlayer) player, this.createMenu(stack), buffer -> {
                buffer.writeItem(stack);
                buffer.writeEnum(this.type);
            });
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private MenuProvider createMenu(ItemStack stack){
        return new SimpleMenuProvider((id, inv, player) ->  new BackpackMenu(id, inv, stack, this.type),
                new TextComponent(stack.getHoverName().getString()));
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return !ItemStack.isSame(oldStack, newStack);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new BackpackInventory(stack, this.type.getSizeOfBackpack());
    }
}
