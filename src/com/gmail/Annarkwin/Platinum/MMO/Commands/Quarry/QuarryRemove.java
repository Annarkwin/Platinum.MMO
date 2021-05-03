package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarryRemove implements Subcommand {

	private String description = "Remove a quarry";
	private MainCommand main;
	private String name = "remove";
	private String permission = "platinum.quarry.remove";
	private boolean playeronly = true;
	private String usage = "/quarry remove <quarry>";

	public QuarryRemove(MainCommand maincommand) {
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
		Quarry q = MMO.quarry_manager.getQuarry(p.getLocation());
		if (args.length > 1) {
			if (MMO.quarry_manager.getQuarry(args[1]) == null) {
				p.sendMessage("§4[Error]:§f No quarry with that name exists");
				return;
			}
			MMO.quarry_manager.removeQuarry(args[1]);
		} else {
			if (q == null) {
				p.sendMessage("§4[Error]:§f No quarry found");
				return;
			}
			MMO.quarry_manager.removeQuarry(q);
		}
		p.sendMessage("§2[Info]:§f Quarry removed");
	}
}
