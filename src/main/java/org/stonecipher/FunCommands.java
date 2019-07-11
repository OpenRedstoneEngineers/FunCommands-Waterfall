package org.stonecipher;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginDescription;
import org.stonecipher.commands.*;
import org.stonecipher.handlers.DerpHandler;
import org.stonecipher.handlers.DynamicCommandHandler;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class FunCommands extends Plugin {

    public static ProxyServer proxy;
    public static Logger logger;
    public static File pluginFolder;
    public static PluginDescription pluginDescription;

    @Override
    public void onEnable() {

        proxy = getProxy();
        logger = getLogger();
        pluginFolder = getDataFolder();
        pluginDescription = getDescription();

        getProxy().getPluginManager().registerCommand(this, new Derp());
        getProxy().getPluginManager().registerCommand(this, new Derps());
        getProxy().getPluginManager().registerCommand(this, new FoodFight());
        getProxy().getPluginManager().registerCommand(this, new Reload(this));
        getProxy().getPluginManager().registerCommand(this, new Rename());
        getProxy().getPluginManager().registerCommand(this, new Slap());
        getProxy().getPluginManager().registerCommand(this, new Shrug());
        getProxy().getPluginManager().registerCommand(this, new Version());

        DerpHandler.loadDerps();

        try {
            DynamicCommandHandler.readCommands();
        } catch (IOException e) {
            logger.severe("Unable to read commands something is not right");
        }

        DynamicCommandHandler.registerCommands(this);

    }

    public static ProxiedPlayer getPlayer(String name) throws Exception {
        for (ProxiedPlayer proxiedPlayer : proxy.getPlayers()) {
            if (proxiedPlayer.getDisplayName().equals(name)) {
                return proxiedPlayer;
            }
        }
        throw new Exception("NoPlayerFound");
    }
}
