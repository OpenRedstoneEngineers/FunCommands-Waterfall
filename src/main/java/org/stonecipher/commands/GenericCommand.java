package org.stonecipher.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.stonecipher.FunCommands;

public class GenericCommand extends Command {

    private String command;
    private String permission;
    private String description;
    private String function;

    public GenericCommand(String command, String permission, String description, String function) {
        super(command);
        this.command = command;
        this.permission = permission;
        this.description = description;
        this.function = function;
    }

    public String getCommand()  {
        return command;
    }

    public String getPermission() {
        return permission;
    }

    public String getDescription() {
        return description;
    }

    public String getFuction() {
        return function;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if(!getPermission().equals("") && !commandSender.hasPermission(getPermission())){
            commandSender.sendMessage(new TextComponent("You do not have permission to run this command!"));
            return;
        }

        String function = getFuction();

        // I'll fix this laaaaaateerrrrr
        if(function.contains("<arg-8>") & strings.length<9){
            commandSender.sendMessage(new TextComponent("This Command requires 9 args. " + getDescription()));
        }else{
            if(function.contains("<arg-7") & strings.length<8){
                commandSender.sendMessage(new TextComponent("This Command requires 8 args. " + getDescription()));
            }else{
                if(function.contains("<arg-6>") & strings.length<7){
                    commandSender.sendMessage(new TextComponent("This Command requires 7 args. " + getDescription()));
                }else{
                    if(function.contains("<arg-5>") & strings.length<6){
                        commandSender.sendMessage(new TextComponent("This Command requires 6 args. " + getDescription()));
                    }else{
                        if(function.contains("<arg-4>") & strings.length<5){
                            commandSender.sendMessage(new TextComponent("This Command requires 5 args. " + getDescription()));
                        }else{
                            if(function.contains("<arg-3>") & strings.length<4){
                                commandSender.sendMessage(new TextComponent("This Command requires 4 args. " + getDescription()));
                            }else{
                                if(function.contains("<arg-2>") & strings.length<3){
                                    commandSender.sendMessage(new TextComponent("This Command requires 3 args. " + getDescription()));
                                }else{
                                    if(function.contains("<arg-1>") & strings.length<2){
                                        commandSender.sendMessage(new TextComponent("This Command requires 2 args. " + getDescription()));
                                    }else{
                                        if(function.contains("<arg-0>") & strings.length<1){
                                            commandSender.sendMessage(new TextComponent("This Command requires 1 arg. " + getDescription()));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        function = function
                .replace("<colour-0>", ChatColor.COLOR_CHAR+"0")
                .replace("<colour-1>",ChatColor.COLOR_CHAR+"1")
                .replace("<colour-2>",ChatColor.COLOR_CHAR+"2")
                .replace("<colour-3>",ChatColor.COLOR_CHAR+"3")
                .replace("<colour-4>",ChatColor.COLOR_CHAR+"4")
                .replace("<colour-5>",ChatColor.COLOR_CHAR+"5")
                .replace("<colour-6>",ChatColor.COLOR_CHAR+"6")
                .replace("<colour-7>",ChatColor.COLOR_CHAR+"7")
                .replace("<colour-8>",ChatColor.COLOR_CHAR+"8")
                .replace("<colour-9>",ChatColor.COLOR_CHAR+"9")
                .replace("<colour-a>",ChatColor.COLOR_CHAR+"a")
                .replace("<colour-b>",ChatColor.COLOR_CHAR+"b")
                .replace("<colour-c>",ChatColor.COLOR_CHAR+"c")
                .replace("<colour-d>",ChatColor.COLOR_CHAR+"d")
                .replace("<colour-e>",ChatColor.COLOR_CHAR+"e")
                .replace("<colour-f>",ChatColor.COLOR_CHAR+"f")
                .replace("<colour-k>",ChatColor.COLOR_CHAR+"k")
                .replace("<colour-m>",ChatColor.COLOR_CHAR+"m")
                .replace("<colour-n>",ChatColor.COLOR_CHAR+"n")
                .replace("<colour-o>",ChatColor.COLOR_CHAR+"o")
                .replace("<colour-r>",ChatColor.COLOR_CHAR+"r")
                .replace("<colour-l>",ChatColor.COLOR_CHAR+"l");

        function = function.replace("<name>", commandSender.getName());

        String argall = "";

        for(String y : strings){
            argall = argall + " " + y;
        }

        for(int r = 0;r < strings.length;r = r + 1){

            function = function.replace("<arg-"+r+">",strings[r]);

        }

        function = function.replace("<arg-all>", argall);


        String output = function.split("<run>")[0];

        String runnable = "";

        if(function.split("<run>").length > 1){
            runnable = function.split("<run>")[1];
        }

        if(runnable.length() > 0){
            for (ProxiedPlayer player : FunCommands.proxy.getPlayers()) {
                if (player == (ProxiedPlayer) commandSender) {
                    player.sendMessage(new TextComponent(runnable));
                }
            }
        }

        if(output.startsWith("<self>")){
            output = output.replace("<self>", "");
            commandSender.sendMessage(new TextComponent(output));
        }else{
            FunCommands.proxy.broadcast(new TextComponent(output));
        }
    }
}
