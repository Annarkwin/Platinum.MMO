package com.gmail.Annarkwin.Platinum.MMO.Commands.Portal;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Portal;

public class PortalInfo implements Subcommand {

	private String description = "Show details for a portal";
	private MainCommand main;
	private String name = "info";
	private String permission = "platinum.portal.info";
	private boolean playeronly = true;
	private String usage = "/portal info <portal>";

	public PortalInfo(MainCommand maincommand) {
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
		p.sendMessage("Portal Info");
		p.sendMessage(" Portal Name: " + parg.getName());
		p.sendMessage(" Warp Name: " + parg.getWarp().getName());
		p.sendMessage(" Enabled: " + parg.isEnabled());
		p.sendMessage(" Hook: " + parg.getHook());
	}
}
