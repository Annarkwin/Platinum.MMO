package com.gmail.Annarkwin.Platinum.MMO.Commands.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;

public class InventoryDrop implements Subcommand {

	private String description = "Drop a player's inventory contents";
	private MainCommand main;
	private String name = "drop";
	private String permission = "platinum.inventory.drop";
	private boolean playeronly = true;
	private String usage = "/inventory drop (player)";

	public InventoryDrop(MainCommand maincommand) {
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
		if (args.length < 2 && p.hasPermission("platinum.inventory.drop")) {
			Block b = p.getTargetBlockExact(5);
			
			if (b != null) {
				for (int i = 0; i < p.getInventory().getContents().length; i++) {
					if (p.getInventory().getItem(i) != null) {
						b.getWorld().dropItem(b.getRelative(BlockFace.UP).getLocation(), p.getInventory().getItem(i));
						p.getInventory().clear(i);
					}
				}
				p.sendMessage("§2[Info]:§f Your inventory has been dropped");
			} else p.sendMessage("§4[Error]:§f You must look at the drop location");;
			
		} else if (args.length > 1 && p.hasPermission("platinum.inventory.admin")) {
			Player target = Bukkit.getPlayer(args[1]);
			
			if (target != null) {
				Block b = p.getTargetBlockExact(5);
				
				if (b != null) {
					for (int i = 0; i < target.getInventory().getContents().length; i++) {
						if (target.getInventory().getItem(i) != null) {
							b.getWorld().dropItem(b.getRelative(BlockFace.UP).getLocation(), target.getInventory().getItem(i));
							target.getInventory().clear(i);
						}
					}
					p.sendMessage("§2[Info]:§f You have dropped " + target.getName() + "'s inventory contents");
					target.sendMessage("§2[Info]:§f Your inventory has been dropped by an admin");
				} else p.sendMessage("§4[Error]:§f You must look at the drop location");;
				
			} else p.sendMessage("§4[Error]:§f You must specify a valid online player");
			
		} else p.sendMessage("§4[Error]:§f You don't have permission for that command");
			
	}
}
