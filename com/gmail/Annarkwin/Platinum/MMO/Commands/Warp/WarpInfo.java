package com.gmail.Annarkwin.Platinum.MMO.Commands.Warp;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Warp;

public class WarpInfo implements Subcommand {

	private String description = "Show warp info";
	private MainCommand main;
	private String name = "info";
	private String permission = "platinum.warp.info";
	private boolean playeronly = true;
	private String usage = "/warp info <warp>";

	public WarpInfo(MainCommand maincommand) {
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
		if (args.length < 2) {
			p.sendMessage("§4[Error]:§f Enter the name of the warp");
			return;
		}
		if ((warg = MMO.warp_manager.getWarp(args[1])) == null) {
			p.sendMessage("§4[Error]:§f No warp with that name found");
			return;
		}
		p.sendMessage("Warp Info");
		p.sendMessage(" Warp Name: " + warg.getName());
		p.sendMessage(" Hook: " + warg.getHook());
	}
}
