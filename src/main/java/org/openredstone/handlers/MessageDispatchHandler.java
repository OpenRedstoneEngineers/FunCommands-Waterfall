package org.openredstone.handlers;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.openredstone.FunCommands;

public class MessageDispatchHandler {
    public static void sendData(ProxiedPlayer proxiedPlayer, String data) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(FunCommands.subChannel);
        out.writeUTF(data);
        proxiedPlayer.getServer().sendData(FunCommands.channel, out.toByteArray());
    }
}
