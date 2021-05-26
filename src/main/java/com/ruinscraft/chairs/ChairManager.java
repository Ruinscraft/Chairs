package com.ruinscraft.chairs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public final class ChairManager {

    private static Map<Player, ChairData> sitting = new HashMap<>();

    public static boolean sit(Player player, Block block) {
        if (!(block.getBlockData() instanceof Stairs)) {
            return false;
        }

        if (sitting.containsKey(player)) {
            return false;
        }

        Stairs stairs = (Stairs) block.getBlockData();
        Location location = block.getLocation();

        location.setDirection(stairs.getFacing().getDirection().multiply(-1));
        location = location.add(0.5, -1.1, 0.5);
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        armorStand.setVelocity(new Vector(0, 0, 0));
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        armorStand.setSilent(true);
        armorStand.setAI(false);
        armorStand.setMarker(false);
        armorStand.setVisible(false);
        armorStand.addPassenger(player);

        ChairData chairData = new ChairData(armorStand, block);

        sitting.put(player, chairData);

        if (player.isOnline()) {
            player.sendMessage(ChatColor.GOLD + "You are now sitting.");
        }

        return true;
    }

    public static boolean unsit(Player player) {
        if (!sitting.containsKey(player)) {
            return false;
        }

        ChairData chairData = sitting.remove(player);

        chairData.getSittingOn().eject();
        chairData.getSittingOn().remove();

        if (player.isOnline()) {
            player.sendMessage(ChatColor.GOLD + "You are no longer sitting.");
        }

        return true;
    }

    public static ChairData getChairData(Player player) {
        return sitting.get(player);
    }

}
