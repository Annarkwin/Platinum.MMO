package com.gmail.Annarkwin.Platinum.MMO.Commands.Portal;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Portal;
import com.gmail.Annarkwin.Platinum.MMO.Selection;
import com.gmail.Annarkwin.Platinum.MMO.DataLibrary.SelectionManager;

public class PortalNew extends PlatinumCommand
{

	public PortalNew( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Selection sel = SelectionManager.getSelection(p.getUniqueId());

		if (!sel.isComplete())
		{

			p.sendMessage("§4[Error]:§f No selection");
			return true;

		}

		if (MMO.portal_manager.isOverlappingPortal(sel.getArea()))
		{

			p.sendMessage("§4[Error]:§f Overlapping portals");
			return true;

		}

		if (!(args.length > 1))
		{

			p.sendMessage("§4[Error]:§f Enter a name");
			return true;

		}

		if (MMO.portal_manager.getPortal(args[1]) != null)
		{

			p.sendMessage("§4[Error]:§f That name is in use");
			return true;

		}

		if (!(args.length > 2))
		{

			p.sendMessage("§4[Error]:§f Enter a warp point");
			return true;

		}

		if (MMO.warp_manager.getWarp(args[2]) == null)
		{

			p.sendMessage("§4[Error]:§f Warp point not found");
			return true;

		}

		MMO.portal_manager.addPortal(new Portal(sel.getArea(), args[1], MMO.warp_manager.getWarp(args[2])));
		p.sendMessage("§2[Info]:§f Portal created");

		return true;

	}

}
