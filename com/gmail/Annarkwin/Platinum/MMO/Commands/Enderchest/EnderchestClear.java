package com.gmail.Annarkwin.Platinum.MMO.Commands.Enderchest;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;

public class EnderchestClear implements Subcommand {

	private String description = "Clear a player's enderchest";
	private MainCommand main;
	private String name = "clear";
	private String permission = "platinum.enderchest.clear";
	private boolean playeronly = true;
	private String usage = "/enderchest clear";

	public EnderchestClear(MainCommand maincommand) {
		main = maincommand;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public MainCommand getMainCommand() {
		return main;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPermission() {
		return permission;
	}

	@Override
	public String getUsage() {
		return usage;
	}

	@Override
	public boolean isPlayerOnly() {
		return playeronly;
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		if (args.length < 2 && p.hasPermission("platinum.enderchest.clear")) {
			p.getEnderChest().clear();
			p.sendMessage("§2[Info]:§f Your enderchest has been cleared");
		} else if (args.length > 1 && p.hasPermission("platinum.enderchest.admin")) {
			Player target = Bukkit.getPlayer(args[1]);
			if (target != null) {
				target.getEnderChest().clear();
				p.sendMessage("§2[Info]:§f You have cleared " + target.getName() + "'s enderchest");
				target.sendMessage("§2[Info]:§f Your enderchest has been cleared");
			} else p.sendMessage("§4[Error]:§f You must specify a valid online player");
		} else p.sendMessage("§4[Error]:§f You don't have permission for that command");
			
	}
}
