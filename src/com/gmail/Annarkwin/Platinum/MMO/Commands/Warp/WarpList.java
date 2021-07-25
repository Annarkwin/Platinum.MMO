package com.gmail.Annarkwin.Platinum.MMO.Commands.Warp;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Warp;

public class WarpList extends PlatinumCommand
{

	public WarpList( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;

		String message = "";
		p.sendMessage("§2[Info]:§f Allowed warps:");

		for (Warp wrp : MMO.warp_manager.getWarps())
		{

			if (wrp.getHook().equalsIgnoreCase("") || p.hasPermission(wrp.getHook()))
			{

				message = " - " + wrp.getName();

				if (!wrp.isEnabled())
					message = "§4" + message;

				p.sendMessage(message);

			}

		}

		p.sendMessage("---");
		return true;

	}

}
