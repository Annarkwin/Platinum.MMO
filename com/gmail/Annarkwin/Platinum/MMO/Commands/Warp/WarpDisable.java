package com.gmail.Annarkwin.Platinum.MMO.Commands.Warp;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Warp;

public class WarpDisable implements Subcommand {

	private String description = "Disable a warp";
	private MainCommand main;
	private String name = "disable";
	private String permission = "platinum.warp.disable";
	private boolean playeronly = true;
	private String usage = "/warp disable <warp>";

	public WarpDisable(MainCommand maincommand) {
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
		Warp parg;
		if (args.length < 2) {
			p.sendMessage("�4[Error]:�f Enter the name of the warp");
			return;
		}
		if ((parg = MMO.warp_manager.getWarp(args[1])) == null) {
			p.sendMessage("�4[Error]:�f No warp with that name found");
			return;
		}
		if (!parg.isEnabled()) {
			p.sendMessage("�4[Error]:�f That warp is already disabled");
			return;
		}
		p.sendMessage("�2[Info]:�f Warp usability has been set to " + parg.toggleEnabled());
	}
}
