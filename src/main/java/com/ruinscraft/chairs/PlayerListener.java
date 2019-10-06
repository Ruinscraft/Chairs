package com.ruinscraft.chairs;

import org.bukkit.block.Block;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.spigotmc.event.entity.EntityDismountEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        if (event.getItem() != null) {
            return;
        }

        if (block == null) {
            return;
        }

        if (!(block.getBlockData() instanceof Stairs)) {
            return;
        }

        if (ChairManager.sit(player, block.getLocation().add(0.5, 0, 0.5))) {
            player.sendMessage("Now sitting");
        }
    }

    @EventHandler
    public void onEntityDismount(EntityDismountEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();

        if (ChairManager.unsit(player)) {
            player.sendMessage("Stopped sitting");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        ChairManager.unsit(player);
    }

}
