package com.ruinscraft.chairs;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public final class ChairManager {

    private static Map<Player, Entity> sitting = new HashMap<>();

    public static boolean sit(Player player, Location location) {
        if (sitting.containsKey(player)) {
            return false;
        }

        World world = location.getWorld();
        Arrow arrow = (Arrow) world.spawnEntity(location, EntityType.ARROW);

        arrow.setVelocity(new Vector(0, 0, 0));
        arrow.setBounce(false);
        arrow.setGravity(false);
        arrow.setInvulnerable(true);
        arrow.setSilent(true);
        arrow.addPassenger(player);

        sitting.put(player, arrow);

        return true;
    }

    public static boolean unsit(Player player) {
        if (!sitting.containsKey(player)) {
            return false;
        }

        Entity arrow = sitting.remove(player);

        arrow.eject();
        arrow.remove();

        return true;
    }

}
