package com.gmail.Annarkwin.Platinum.MMO.Commands.Test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTest implements CommandExecutor {
	
	//TODO Try elevator using player gravity toggle
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§4[Error]:§f This command is for players");
			return true;
		}
//		
		return true;
	}
}
