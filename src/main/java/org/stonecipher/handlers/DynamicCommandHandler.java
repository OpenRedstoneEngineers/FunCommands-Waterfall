package org.stonecipher.handlers;

import net.md_5.bungee.api.plugin.Plugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.stonecipher.FunCommands;
import org.stonecipher.commands.GenericCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DynamicCommandHandler {

    private static List<GenericCommand> Commands = null;

    public static List<String> CommandNames = null;

    private static final File CommandFile =  new File(FunCommands.pluginFolder + File.separator + "Commands.json");

    public static void readCommands() throws IOException {
        Commands = new ArrayList<>();
        CommandNames = new ArrayList<>();


        FunCommands.logger.info("Reading FunCommands from Commands.json!");

        FileReader fileReader = null;

        JSONParser parser = new JSONParser();

        try {

            fileReader = new FileReader(CommandFile);

        }catch (FileNotFoundException e) {

            FunCommands.logger.severe("Error opening Commands.json Do you have one? "+ e.toString());

        }

        JSONArray jsonObject = null;

        try {

            jsonObject = (JSONArray) parser.parse(fileReader);

        } catch (Exception e) {

             FunCommands.logger.warning("Can't read Commands.json. You sure its formated right?");

        }

        Iterator<JSONObject> iterator = jsonObject.iterator();

        int  temp2 = 0;

        while (iterator.hasNext()) {

            JSONObject temp = iterator.next();

            GenericCommand command = new GenericCommand((String) temp.get("Command"), "" /*"FunCommands."+(String) temp.get("Command")*/, (String) temp.get("Description"), (String) temp.get("Function"));

            Commands.add(command);
            CommandNames.add((String) temp.get("Command"));

            temp2++;

        }
        FunCommands.logger.info(temp2 + " Commands loaded!");
        try {
            fileReader.close();
        } catch (IOException e) {
            FunCommands.logger.severe("Can't close Commands.json... That's not right!");
        }
    }

    public static void registerCommands(Plugin plugin){

        for(GenericCommand command : Commands){
            plugin.getProxy().getPluginManager().registerCommand(plugin, command);
        }

    }
    public static void unRegisterCommands(Plugin plugin){

        for(GenericCommand command : Commands){
            plugin.getProxy().getPluginManager().unregisterCommand(command);
        }

    }
}
