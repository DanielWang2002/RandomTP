package mc.danielwang.randomtp;

import org.bukkit.plugin.java.JavaPlugin;

public final class Randomtp extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("RandomTP Loading...");
        getLogger().info("Load Complete! by DanielWang");

        getCommand("rt").setExecutor(new rtCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
