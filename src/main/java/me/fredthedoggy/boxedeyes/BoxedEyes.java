package me.fredthedoggy.boxedeyes;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class BoxedEyes extends JavaPlugin {

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.addDefault("EyeLocation.X-Offset", 100.0);
        config.addDefault("EyeLocation.Z-Offset", 100.0);
        config.addDefault("EyeLocation.Y-Location", 30.0);
        config.options().copyDefaults(true);
        saveConfig();
        getServer().getPluginManager().registerEvents(new EyeListener(this), this);
    }

}
