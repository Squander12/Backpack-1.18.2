package me.squander.backpack.items;

public enum BackpackType {
    SMALL(15, 5, 3, 44, 16, "backpack_small"),
    MEDIUM(27, 9, 3, 8, 18, "backpack_medium"),
    LARGE(45, 9, 5, 8, 18, "backpack_large");

    private final int sizeOfBackpack;
    private final int sizeX, sizeY;
    private final int slotX, slotY;
    private final String loc;

    BackpackType(int sizeOfBackpack, int sizeX, int sizeY, int slotX, int slotY, String loc) {
        this.sizeOfBackpack = sizeOfBackpack;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.slotX = slotX;
        this.slotY = slotY;
        this.loc = loc;
    }

    public int getSizeOfBackpack() {
        return sizeOfBackpack;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getSlotX() {
        return slotX;
    }

    public int getSlotY() {
        return slotY;
    }

    public String getLoc() {
        return loc;
    }
}
