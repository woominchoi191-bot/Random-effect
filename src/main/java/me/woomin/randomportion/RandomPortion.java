package me.woomin.randomportion;

import org.bukkit.plugin.java.JavaPlugin;

public final class RandomPortion extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("RandomPortion 플러그인이 활성화되었습니다!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RandomPortion 플러그인이 비활성화되었습니다!");
    }
}