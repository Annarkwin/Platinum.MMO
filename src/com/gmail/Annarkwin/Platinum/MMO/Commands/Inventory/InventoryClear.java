package com.gmail.Annarkwin.Platinum.MMO.Commands.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.Annarkwin.Platinum.API.PlatinumCommand;

public class InventoryClear extends PlatinumCommand
{

	public InventoryClear( String name, String permission, boolean player, String description, String usage )
	{

		super(name, permission, player, description, usage);
		// TODO Auto-generated constructor stub

	}

	@Override
	public boolean run( CommandSender sender, String cmdname, String[] args )
	{

		Player p = (Player) sender;

		if (args.length < 2 && p.hasPermission("platinum.inventory.clear"))
		{

			p.getInventory().clear();
			p.sendMessage("§2[Info]:§f Your inventory has been cleared");

		}
		else if (args.length > 1 && p.hasPermission("platinum.inventory.admin"))
		{

			Player target = Bukkit.getPlayer(args[1]);

			if (target != null)
			{

				target.getInventory().clear();
				p.sendMessage("§2[Info]:§f You have cleared " + target.getName() + "'s inventory");
				target.sendMessage("§2[Info]:§f Your inventory has been cleared");

			}
			else
				p.sendMessage("§4[Error]:§f You must specify a valid online player");

		}
		else
			p.sendMessage("§4[Error]:§f You don't have permission for that command");

		return true;

	}

}
