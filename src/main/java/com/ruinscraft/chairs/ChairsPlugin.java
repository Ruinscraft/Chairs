package com.ruinscraft.chairs;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ChairsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        for (Player player : getServer().getOnlinePlayers()) {
            ChairManager.unsit(player);
        }
    }

}
