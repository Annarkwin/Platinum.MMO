package com.gmail.Annarkwin.Platinum.MMO.Commands.Region;

import org.bukkit.command.CommandSender;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.Region;

public class RegionAllow implements Subcommand {

	private String description = "Allow a player to the current region";
	private MainCommand main;
	private String name = "allow";
	private String permission = "platinum.region.allow";
	private boolean playeronly = true;
	private String usage = "/region allow <player> (number) (asplayer)";

	public RegionAllow(MainCommand maincommand) {
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
		Region r;
		
		//Player target only
		if (args.length == 2) {
			
		}
		//Player and region number
		else if (args.length == 3) {
			
		}
		//Player, region number, emulate as if you were chosen player
		else if (args.length == 4) {
			
		}
	}
}
