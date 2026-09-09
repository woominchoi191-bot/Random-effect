package me.woomin.randomportion;

import org.bukkit.Bukkit;
import org.bukkit.Registry;
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

    @Override
    public void onEnable() {

        // 마인크래프트에 등록된 상태 효과 전부 가져오기
        potionEffects = Registry.MOB_EFFECT.stream().toList();

        // 플레이어가 피해를 받는 이벤트 감지 시작
        Bukkit.getPluginManager().registerEvents(this, this);

        getLogger().info("RandomPortion 플러그인이 활성화되었습니다!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RandomPortion 플러그인이 비활성화되었습니다!");
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerDamage(EntityDamageEvent event) {

        // 피해를 받은 대상이 플레이어가 아니면 아무것도 안 함
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        // 모든 상태 효과 중 하나 랜덤 선택
        PotionEffectType randomEffect =
                potionEffects.get(random.nextInt(potionEffects.size()));

        // 200틱 = 10초
        // 0 = 효과 레벨 I
        PotionEffect effect = new PotionEffect(
                randomEffect,
                200,
                0
        );

        player.addPotionEffect(effect);

        player.sendMessage(
                "§d랜덤 효과 획득! §f" +
                        randomEffect.getKey().getKey()
        );
    }
}