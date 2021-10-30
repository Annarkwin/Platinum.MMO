package com.gmail.Annarkwin.Platinum.MMO.Commands.Region;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Selection;
import com.gmail.Annarkwin.Platinum.MMO.DataLibrary.SelectionManager;
import com.gmail.Annarkwin.Platinum.MMO.Exceptions.RegionIntersectException;

public class RegionNew extends PlatinumCommand
{

	public RegionNew( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;
		Selection s = SelectionManager.getSelection(p.getUniqueId());

		if (s == null || s.getArea() == null)
		{

			p.sendMessage("§4[Error]:§f You don't have a selection");

		}
		else
		{

			try
			{

				MMO.region_manager.addRegion(s.getArea().maximizeHeight(), p);
				p.sendMessage("§2[Info]: §fNew region created");

			}
			catch (RegionIntersectException e)
			{

				p.sendMessage("§4[Error]:§f Your selection intersects another region");

			}

		}

		return true;

	}

}
