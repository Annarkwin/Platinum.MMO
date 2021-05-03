package com.gmail.Annarkwin.Platinum.MMO.Commands.Warp;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Warp;

public class WarpRemove implements Subcommand {

	private String description = "Remove a warp and its portals";
	private MainCommand main;
	private String name = "remove";
	private String permission = "platinum.warp.remove";
	private boolean playeronly = true;
	private String usage = "/warp remove <warp>";

	public WarpRemove(MainCommand maincommand) {
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
		Warp warg;
		if (!(args.length > 1)) {
			p.sendMessage("§4[Error]:§f Enter a name");
			return;
		}
		if ((warg = MMO.warp_manager.getWarp(args[1])) == null) {
			p.sendMessage("§4[Error]:§f No warp with that name exists");
			return;
		}
		MMO.warp_manager.removeWarp(warg);
		p.sendMessage("§2[Info]:§f Warp removed");
	}
}
