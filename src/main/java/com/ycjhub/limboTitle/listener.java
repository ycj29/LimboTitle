package com.ycjhub.limboTitle;

import com.loohp.limbo.events.EventHandler;
import com.loohp.limbo.events.Listener;
import com.loohp.limbo.events.player.PlayerJoinEvent;
import com.loohp.limbo.events.player.PlayerMoveEvent;
import com.loohp.limbo.scheduler.LimboRunnable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.TitlePart;
import net.md_5.bungee.api.ChatColor;

public class listener implements Listener {
    private LimboTitle plugin;

    public listener(LimboTitle plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new LimboRunnable(){
            @Override
            public void run() {
                event.getPlayer().clearTitle();
                event.getPlayer().sendTitlePart(TitlePart.TITLE, Component.text(ChatColor.RED + (String) plugin.configMap.get("title")));
                event.getPlayer().sendTitlePart(TitlePart.SUBTITLE, Component.text( (String) plugin.configMap.get("subTitle")));
            }
        }.runTaskTimer(plugin, 0, 60);
    }
}
