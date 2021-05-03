package com.gmail.Annarkwin.Platinum.MMO.Commands.Enderchest;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;

public class EnderchestDrop implements Subcommand {

	private String description = "Drop a player's enderchest contents";
	private MainCommand main;
	private String name = "drop";
	private String permission = "platinum.enderchest.drop";
	private boolean playeronly = true;
	private String usage = "/enderchest drop (player)";

	public EnderchestDrop(MainCommand maincommand) {
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
		if (args.length < 2 && p.hasPermission("platinum.enderchest.drop")) {
			Block b = p.getTargetBlockExact(5);
			
			if (b != null) {
				for (int i = 0; i < p.getEnderChest().getContents().length; i++) {
					if (p.getEnderChest().getItem(i) != null) {
						b.getWorld().dropItem(b.getRelative(BlockFace.UP).getLocation(), p.getEnderChest().getItem(i));
						p.getEnderChest().clear(i);
					}
				}
				p.sendMessage("§2[Info]:§f Your enderchest has been dropped");
			} else p.sendMessage("§4[Error]:§f You must look at the drop location");;
			
		} else if (args.length > 1 && p.hasPermission("platinum.enderchest.admin")) {
			Player target = Bukkit.getPlayer(args[1]);
			
			if (target != null) {
				Block b = p.getTargetBlockExact(5);
				
				if (b != null) {
					for (int i = 0; i < target.getEnderChest().getContents().length; i++) {
						if (target.getEnderChest().getItem(i) != null) {
							b.getWorld().dropItem(b.getRelative(BlockFace.UP).getLocation(), target.getEnderChest().getItem(i));
							target.getEnderChest().clear(i);
						}
					}
					p.sendMessage("§2[Info]:§f You have dropped " + target.getName() + "'s enderchest contents");
					target.sendMessage("§2[Info]:§f Your enderchest has been dropped by an admin");
				} else p.sendMessage("§4[Error]:§f You must look at the drop location");;
				
			} else p.sendMessage("§4[Error]:§f You must specify a valid online player");
			
		} else p.sendMessage("§4[Error]:§f You don't have permission to do that");
			
	}
}
