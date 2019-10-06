package com.ruinscraft.chairs;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ChairsPlugin extends JavaPlugin {

    // singleton ===============================
    private static ChairsPlugin instance;

    public static ChairsPlugin get() {
        return instance;
    }
    // singleton ===============================

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        for (Player player : getServer().getOnlinePlayers()) {
            ChairManager.unsit(player);
        }

        instance = null;
    }

}
