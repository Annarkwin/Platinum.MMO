package com.gmail.Annarkwin.Platinum.MMO.Commands.Portal;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Portal;
import com.gmail.Annarkwin.Platinum.MMO.Selection;
import com.gmail.Annarkwin.Platinum.MMO.DataLibrary.SelectionManager;

public class PortalNew implements Subcommand {

	private String description = "Set a portal area";
	private MainCommand main;
	private String name = "new";
	private String permission = "platinum.portal.new";
	private boolean playeronly = true;
	private String usage = "/portal new <name> <warp>";

	public PortalNew(MainCommand maincommand) {
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
		Selection sel = SelectionManager.getSelection(p.getUniqueId());
		if (!sel.isComplete()) {
			p.sendMessage("§4[Error]:§f No selection");
			return;
		}
		if (MMO.portal_manager.isOverlappingPortal(sel.getArea())) {
			p.sendMessage("§4[Error]:§f Overlapping portals");
			return;
		}
		if (!(args.length > 1)) {
			p.sendMessage("§4[Error]:§f Enter a name");
			return;
		}
		if (MMO.portal_manager.getPortal(args[1]) != null) {
			p.sendMessage("§4[Error]:§f That name is in use");
			return;
		}
		if (!(args.length > 2)) {
			p.sendMessage("§4[Error]:§f Enter a warp point");
			return;
		}
		if (MMO.warp_manager.getWarp(args[2]) == null) {
			p.sendMessage("§4[Error]:§f Warp point not found");
			return;
		}
		MMO.portal_manager.addPortal(new Portal(sel.getArea(), args[1], MMO.warp_manager.getWarp(args[2])));
		p.sendMessage("§2[Info]:§f Portal created");
	}
}
