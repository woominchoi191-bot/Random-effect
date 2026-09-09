package me.woomin.randomportion;

import org.bukkit.Bukkit;
import org.bukkit.Registry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public final class RandomPortion extends JavaPlugin implements Listener {

    private final Random random = new Random();
    private List<PotionEffectType> potionEffects;

    // 플러그인 기능 ON/OFF 상태
    private boolean enabled = true;

    @Override
    public void onEnable() {

        // 마인크래프트에 등록된 모든 상태 효과 가져오기
        potionEffects = Registry.MOB_EFFECT.stream().toList();

        // 피해 이벤트 등록
        Bukkit.getPluginManager().registerEvents(this, this);

        getLogger().info("RandomPortion 플러그인이 활성화되었습니다!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RandomPortion 플러그인이 비활성화되었습니다!");
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerDamage(EntityDamageEvent event) {

        // 기능이 OFF면 아무것도 하지 않음
        if (!enabled) {
            return;
        }

        // 피해받은 대상이 플레이어가 아니면 무시
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        // 랜덤 상태 효과 선택
        PotionEffectType randomEffect =
                potionEffects.get(random.nextInt(potionEffects.size()));

        // 10초 ~ 60초 랜덤
        int durationSeconds = 10 + random.nextInt(51);

        // 마인크래프트 20틱 = 1초
        int durationTicks = durationSeconds * 20;

        PotionEffect effect = new PotionEffect(
                randomEffect,
                durationTicks,
                0
        );

        player.addPotionEffect(effect);

        player.sendMessage(
                "§d랜덤 효과 획득! §f" +
                        randomEffect.getKey().getKey() +
                        " §7(" + durationSeconds + "초)"
        );
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {

        // OP 또는 해당 권한이 있는 사람만 사용
        if (!sender.hasPermission("randomportion.admin")) {
            sender.sendMessage("§c이 명령어를 사용할 권한이 없습니다.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§e사용법: /randomportion <on|off>");
            return true;
        }

        if (args[0].equalsIgnoreCase("on")) {

            enabled = true;

            Bukkit.broadcastMessage(
                    "§aRandomPortion 기능이 활성화되었습니다!"
            );

            return true;
        }

        if (args[0].equalsIgnoreCase("off")) {

            enabled = false;

            Bukkit.broadcastMessage(
                    "§cRandomPortion 기능이 비활성화되었습니다!"
            );

            return true;
        }

        sender.sendMessage("§e사용법: /randomportion <on|off>");
        return true;
    }
}