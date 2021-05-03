package com.gmail.Annarkwin.Platinum.MMO.Commands.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;

public class InventoryCopy implements Subcommand {

	private String description = "Copy a player's inventory to your own";
	private MainCommand main;
	private String name = "copy";
	private String permission = "platinum.inventory.copy";
	private boolean playeronly = true;
	private String usage = "/inventory copy <player>";

	public InventoryCopy(MainCommand maincommand) {
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
		if (args.length > 1 && p.hasPermission("platinum.inventory.admin")) {
			Player target = Bukkit.getPlayer(args[1]);
			
			if (target != null) {
				for (int i = 0; i < target.getInventory().getContents().length; i++) {
					p.getInventory().setItem(i, target.getInventory().getItem(i));
				}
				p.sendMessage("§2[Info]:§f You have copied " + target.getName() + "'s inventory");
				
			} else p.sendMessage("§4[Error]:§f You must specify a valid online player");
			
		} else p.sendMessage("§4[Error]:§f You don't have permission for that command");
	}
}
