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
                if (!plugin.configMap.get("subTitle").equals("") && !plugin.configMap.get("title").equals("")) {
                    event.getPlayer().clearTitle();
                    event.getPlayer().sendTitlePart(TitlePart.TITLE, Component.text(ChatColor.RED + (String) plugin.configMap.get("title")));
                    event.getPlayer().sendTitlePart(TitlePart.SUBTITLE, Component.text( (String) plugin.configMap.get("subTitle")));
                }
                if (!plugin.configMap.get("message").equals("")) {
                    event.getPlayer().sendMessage(ChatColor.RED + (String) plugin.configMap.get("message"));
                }
            }
        }.runTaskTimer(plugin, 0, (int) plugin.configMap.get("interval"));
    }
}
