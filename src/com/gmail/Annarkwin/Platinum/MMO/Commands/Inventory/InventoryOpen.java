package com.gmail.Annarkwin.Platinum.MMO.Commands.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.MainCommand;
import com.gmail.Annarkwin.Platinum.API.Subcommand;

public class InventoryOpen implements Subcommand
{

	private String description = "Open a player's inventory";
	private MainCommand main;
	private String name = "open";
	private String permission = "platinum.inventory.open";
	private boolean playeronly = true;
	private String usage = "/inventory open (player)";

	public InventoryOpen( MainCommand maincommand )
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

		if (args.length < 2)
		{

			p.sendMessage("§4[Error]§f You can already open your own inventory");

		}
		else if (args.length > 1 && p.hasPermission("platinum.inventory.admin"))
		{

			Player target = Bukkit.getPlayer(args[1]);

			if (target != null)
			{

				p.openInventory(target.getInventory());

			}
			else
				p.sendMessage("§4[Error]:§f You must specify a valid online player");

		}
		else
			p.sendMessage("§4[Error]:§f You don't have permission for that command");

	}

}
