/*
 *  Created by Filip P. on 2/14/15 9:07 PM.
 */

package me.pauzen.alphacore.commands.childcommands;

import me.pauzen.alphacore.commands.Command;
import me.pauzen.alphacore.commands.CommandListener;
import me.pauzen.alphacore.messages.ChatMessage;
import me.pauzen.alphacore.players.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HealCommand extends Command {
    
    @Override
    public String getName() {
        return "heal";
    }

    @Override
    public CommandListener defaultListener() {
        return new CommandListener(false, "core.heal") {
            @Override
            public void onRun() {
                Player target = args.length == 0 ? (Player) commandSender : Bukkit.getPlayer(args[0]);
                CorePlayer.get(target).healFully();
                ChatMessage.HEALED.sendMessage(commandSender);
            }
        };
    }
}
