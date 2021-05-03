package com.gmail.Annarkwin.Platinum.MMO.Commands.Warp;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Warp;

public class WarpNew implements Subcommand {

	private String description = "Create a new warp at your location";
	private MainCommand main;
	private String name = "new";
	private String permission = "platinum.warp.new";
	private boolean playeronly = true;
	private String usage = "/warp new <name>";

	public WarpNew(MainCommand maincommand) {
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
		if (!(args.length > 1)) {
			p.sendMessage("§4[Error]:§f Enter a name");
			return;
		}
		if (MMO.warp_manager.getWarp(args[1]) != null) {
			p.sendMessage("§4[Error]:§f That name is in use");
			return;
		}
		MMO.warp_manager.addWarp(new Warp(args[1], p.getLocation()));
		p.sendMessage("§2[Info]:§f Warp created");
	}
}
