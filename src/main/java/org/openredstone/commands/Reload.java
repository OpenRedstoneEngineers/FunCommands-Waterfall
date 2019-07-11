package org.openredstone.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import org.openredstone.FunCommands;
import org.openredstone.handlers.DerpHandler;
import org.openredstone.handlers.DynamicCommandHandler;

import java.io.IOException;

public class Reload extends Command {

    Plugin plugin;

    public Reload(Plugin plugin) {
        super("funreload");
        this.plugin = plugin;
    }
    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(!commandSender.hasPermission("funcommands" + this.getClass().getSimpleName())){
            commandSender.sendMessage(new TextComponent("You do not have permission to run this command!"));
            return;
        }
        DynamicCommandHandler.unRegisterCommands(plugin);

        try {
            DynamicCommandHandler.readCommands();
        } catch (IOException e) {
            FunCommands.logger.severe("Unable to read commands something is not right");
            return;
        }

        DynamicCommandHandler.registerCommands(plugin);
        DerpHandler.loadDerps();
        commandSender.sendMessage(new TextComponent("Reloaded FunCommands."));
    }
}
