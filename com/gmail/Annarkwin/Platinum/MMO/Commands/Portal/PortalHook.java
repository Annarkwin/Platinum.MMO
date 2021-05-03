package com.gmail.Annarkwin.Platinum.MMO.Commands.Portal;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Portal;

public class PortalHook implements Subcommand {

	private String description = "Set permission hook";
	private MainCommand main;
	private String name = "hook";
	private String permission = "platinum.portal.hook";
	private boolean playeronly = true;
	private String usage = "/portal hook <portal> <perm>";

	public PortalHook(MainCommand maincommand) {
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
		Portal parg;
		if (args.length < 2) {
			p.sendMessage("§4[Error]:§f Enter the name of the portal");
			return;
		}
		if ((parg = MMO.portal_manager.getPortal(args[1])) == null) {
			p.sendMessage("§4[Error]:§f No portal with that name found");
			return;
		}
		if (args.length < 3) {
			p.sendMessage("§4[Error]:§f Enter the hook of the portal");
			return;
		}
		parg.setHook(args[2]);
		p.sendMessage("§2[Info]:§f Portal hooked");
	}
}
