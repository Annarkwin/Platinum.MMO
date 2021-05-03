package com.gmail.Annarkwin.Platinum.MMO.Commands.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;

public class InventoryClear implements Subcommand {

	private String description = "Clear a player's inventory";
	private MainCommand main;
	private String name = "clear";
	private String permission = "platinum.inventory.clear";
	private boolean playeronly = true;
	private String usage = "/inventory clear";

	public InventoryClear(MainCommand maincommand) {
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
		if (args.length < 2 && p.hasPermission("platinum.inventory.clear")) {
			p.getInventory().clear();
			p.sendMessage("§2[Info]:§f Your inventory has been cleared");
		} else if (args.length > 1 && p.hasPermission("platinum.inventory.admin")) {
			Player target = Bukkit.getPlayer(args[1]);
			if (target != null) {
				target.getInventory().clear();
				p.sendMessage("§2[Info]:§f You have cleared " + target.getName() + "'s inventory");
				target.sendMessage("§2[Info]:§f Your inventory has been cleared");
			} else p.sendMessage("§4[Error]:§f You must specify a valid online player");
		} else p.sendMessage("§4[Error]:§f You don't have permission for that command");
			
	}
}
