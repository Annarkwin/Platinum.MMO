package com.gmail.Annarkwin.Platinum.MMO.Commands.Quarry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Quarry;

public class QuarrySetTP implements Subcommand {

	private String description = "Set the tp location of a quarry";
	private MainCommand main;
	private String name = "settp";
	private String permission = "platinum.quarry.settp";
	private boolean playeronly = true;
	private String usage = "/quarry settp <quarry>";

	public QuarrySetTP(MainCommand maincommand) {
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
		Quarry qa;
		if (args.length < 2) {
			p.sendMessage("§4[Error]:§f Enter the name of the quarry");
			return;
		}
		if ((qa = MMO.quarry_manager.getQuarry(args[1])) == null) {
			p.sendMessage("§4[Error]:§f No quarry with that name found");
			return;
		}
		if (MMO.quarry_manager.getQuarry(p.getLocation()) != null) {
			p.sendMessage("§4[Error]:§f Stand outside of quarries");
			return;
		}
		qa.setWarpLocation(p.getLocation());
		p.sendMessage("§2[Info]:§f Quarry warp location set");
	}
}
