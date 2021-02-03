package com.molean.railteleport;

import org.bukkit.plugin.java.JavaPlugin;

public final class RailTeleportPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new RailTeleportHandler();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
