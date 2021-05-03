package com.gmail.Annarkwin.Platinum.MMO.Commands.Region;

import org.bukkit.command.CommandSender;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;

public class RegionHere implements Subcommand {

	private String description = "List current region and parent regions";
	private MainCommand main;
	private String name = "here";
	private String permission = "platinum.region.here";
	private boolean playeronly = true;
	private String usage = "/region here";

	public RegionHere(MainCommand maincommand) {
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
		
	}
}
