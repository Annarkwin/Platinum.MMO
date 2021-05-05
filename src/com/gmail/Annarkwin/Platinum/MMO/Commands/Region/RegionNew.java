package com.gmail.Annarkwin.Platinum.MMO.Commands.Region;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;
import com.gmail.Annarkwin.Platinum.MMO.MMO;
import com.gmail.Annarkwin.Platinum.MMO.Selection;
import com.gmail.Annarkwin.Platinum.MMO.DataLibrary.SelectionManager;
import com.gmail.Annarkwin.Platinum.MMO.Exceptions.RegionIntersectException;

public class RegionNew implements Subcommand
{

	private String description = "Create a new region with your selection";
	private MainCommand main;
	private String name = "new";
	private String permission = "platinum.region.new";
	private boolean playeronly = true;
	private String usage = "/region new";

	public RegionNew( MainCommand maincommand )
	{

		main = maincommand;

	}

	@Override
	public String getDescription()
	{

		return description;

	}

	@Override
	public MainCommand getMainCommand()
	{

		return main;

	}

	@Override
	public String getName()
	{

		return name;

	}

	@Override
	public String getPermission()
	{

		return permission;

	}

	@Override
	public String getUsage()
	{

		return usage;

	}

	@Override
	public boolean isPlayerOnly()
	{

		return playeronly;

	}

	@Override
	public void run( CommandSender sender, String[] args )
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

	}

}
