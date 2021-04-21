package me.fredthedoggy.boxedeyes;

import org.bukkit.plugin.java.JavaPlugin;

public final class BoxedEyes extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EyeListener(), this);
    }
}
