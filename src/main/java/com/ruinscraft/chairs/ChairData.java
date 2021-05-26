package com.ruinscraft.chairs;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class ChairData {

    private final Entity sittingOn;
    private final Block chairBlock;

    public ChairData(Entity sittingOn, Block chairBlock) {
        this.sittingOn = sittingOn;
        this.chairBlock = chairBlock;
    }

    public Entity getSittingOn() {
        return sittingOn;
    }

    public Block getChairBlock() {
        return chairBlock;
    }

}
