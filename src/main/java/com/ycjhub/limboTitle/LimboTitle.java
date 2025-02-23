package com.ycjhub.limboTitle;

import com.loohp.limbo.events.Listener;
import com.loohp.limbo.plugins.LimboPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;

public final class LimboTitle extends LimboPlugin {
    protected Map<String, Object> configMap;

    @Override
    public void onEnable() {
        getDataFolder().mkdirs();
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            ClassLoader loader = this.getClass().getClassLoader();
            InputStream defaultConfig = loader.getResourceAsStream("config.yml");

            if (defaultConfig != null) {
                try {
                    Files.copy(defaultConfig, configFile.toPath());
                } catch (IOException err) {
                    throw new RuntimeException(err);
                }
            }
        }
        Yaml yaml = new Yaml();
        FileInputStream configStream = null;

        try {
            configStream = new FileInputStream(getDataFolder().toPath() + "/config.yml");
        } catch (FileNotFoundException err) {
            err.printStackTrace();
        }

        configMap = yaml.load(configStream);
        getServer().getEventsManager().registerEvents(this, new listener(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
