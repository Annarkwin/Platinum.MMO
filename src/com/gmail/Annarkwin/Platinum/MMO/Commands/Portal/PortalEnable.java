package com.gmail.Annarkwin.Platinum.MMO.Commands.Portal;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Portal;

public class PortalEnable implements Subcommand {

	private String description = "Enable a portal";
	private MainCommand main;
	private String name = "enable";
	private String permission = "platinum.portal.enable";
	private boolean playeronly = true;
	private String usage = "/portal enable <portal>";

	public PortalEnable(MainCommand maincommand) {
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
		if (parg.isEnabled()) {
			p.sendMessage("§4[Error]:§f That portal is already enabled");
			return;
		}
		p.sendMessage("§2[Info]:§f Portal usability has been set to " + parg.toggleEnabled());
	}
}
